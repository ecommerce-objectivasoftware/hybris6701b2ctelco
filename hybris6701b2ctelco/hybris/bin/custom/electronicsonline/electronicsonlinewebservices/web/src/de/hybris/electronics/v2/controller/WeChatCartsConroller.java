package de.hybris.electronics.v2.controller;

import de.hybris.electronics.dto.cart.WeChatMiniCartResponseData;
import de.hybris.electronics.dto.cart.WeChatMiniCartRootData;
import de.hybris.electronics.order.data.CartDataList;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdAndUserIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/{baseSiteId}/users/{userId}/wechat-cart")
@CacheControl(directive = CacheControlDirective.NO_CACHE)
@Api(tags = "WeChat Carts")
public class WeChatCartsConroller extends BaseCommerceController {

    @Resource(name = "saveCartFacade")
    private SaveCartFacade saveCartFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get a cart for WeChat customer.", notes = "Get a cart for WeChat customer.")
    @ApiBaseSiteIdAndUserIdParam
    public WeChatMiniCartResponseData getACartForWeChat(
            @ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields,
            @ApiParam(value = "Optional parameter. If the parameter is provided and its value is true, only saved carts are returned.") @RequestParam(required = false, defaultValue = "false") final boolean savedCartsOnly,
            @ApiParam(value = "Optional pagination parameter in case of savedCartsOnly == true. Default value 0.") @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
            @ApiParam(value = "Optional {@link PaginationData} parameter in case of savedCartsOnly == true. Default value 20.") @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
            @ApiParam(value = "Optional sort criterion in case of savedCartsOnly == true. No default value.") @RequestParam(required = false) final String sort) {
        if (getUserFacade().isAnonymousUser()) {
            throw new AccessDeniedException("Access is denied");
        }

        final CartDataList cartDataList = new CartDataList();

        final PageableData pageableData = new PageableData();
        pageableData.setCurrentPage(currentPage);
        pageableData.setPageSize(pageSize);
        pageableData.setSort(sort);
        final List<CartData> allCarts = new ArrayList<>(
                saveCartFacade.getSavedCartsForCurrentUser(pageableData, null).getResults());
        if (!savedCartsOnly) {
            allCarts.addAll(getCartFacade().getCartsForCurrentUser());
        }
        cartDataList.setCarts(allCarts);

        WeChatMiniCartRootData weChatMiniCartRootData = new WeChatMiniCartRootData();

        Optional<CartData> cartOptional = allCarts.stream().sorted(Comparator.comparing(CartData::getCode).reversed()).findFirst();
        CartData cart = cartOptional.orElseGet(this::getSessionCart);
        long qty = cart.getEntries().stream().mapToLong(OrderEntryData::getQuantity).sum();
        weChatMiniCartRootData.setCartId(cart.getCode());
        weChatMiniCartRootData.setTotalQty(String.valueOf(qty));

        WeChatMiniCartResponseData weChatMiniCartResponseData = new WeChatMiniCartResponseData();
        weChatMiniCartResponseData.setData(weChatMiniCartRootData);
        weChatMiniCartResponseData.setErrno(0);
        return weChatMiniCartResponseData;
    }
}
