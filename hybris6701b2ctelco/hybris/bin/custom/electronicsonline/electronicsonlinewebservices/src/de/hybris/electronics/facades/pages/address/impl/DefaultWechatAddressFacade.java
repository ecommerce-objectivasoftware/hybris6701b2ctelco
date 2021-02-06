package de.hybris.electronics.facades.pages.address.impl;

import de.hybris.electronics.dto.address.WechatAddressBodyData;
import de.hybris.electronics.facades.pages.address.WechatAddressFacade;
import de.hybris.electronics.services.pages.address.WechatAddressService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.address
 * @ClassName: DefaultWechatAddressFacade
 * @Description:
 * @date 2/5/2021 3:48 PM
 */
public class DefaultWechatAddressFacade implements WechatAddressFacade {

    @Resource
    private WechatAddressService wechatAddressService;

    @Resource
    private Converter<AddressModel, WechatAddressBodyData> wechatAddressBodyDataConverter;
    @Resource
    private UserService userService;
    @Resource
    private CartService cartService;

    @Override
    public List<WechatAddressBodyData> getAddressList() {
        final List<AddressModel> addressModelList = wechatAddressService.getAddressList();
        return wechatAddressBodyDataConverter.convertAll(addressModelList);
    }

    @Override
    public List<WechatAddressBodyData> getAddressListByUserId(String userId) {
        final UserModel userModel = userService.getUserForUID(userId);
        Collection<AddressModel> addressModelList =Collections.EMPTY_LIST;
        if(Objects.nonNull(userModel))
        {
            addressModelList = userModel.getAddresses();
        }
        return wechatAddressBodyDataConverter.convertAll(addressModelList);
    }

    @Override
    public WechatAddressBodyData getAddressByAddressId(String addressId) {
        final AddressModel addressModel = wechatAddressService.getAddressById(addressId);
        return wechatAddressBodyDataConverter.convert(addressModel);

    }

    @Override
    public void addAddressForCart(String addressId) throws Exception {
        final CartModel sessionCart = cartService.getSessionCart();
        if(Objects.nonNull(sessionCart))
        {
            wechatAddressService.addAddressForCart(sessionCart, addressId);
        }
        else {
            throw new Exception("No Session Cart Found!");
        }
    }
}
