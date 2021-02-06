/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.electronics.v2.controller;

import de.hybris.electronics.dto.order.OrderRootData;
import de.hybris.electronics.dto.order.WeChatOrderResponseData;
import de.hybris.electronics.dto.order.list.GoodsList;
import de.hybris.electronics.dto.order.list.OrderListData;
import de.hybris.electronics.dto.order.list.OrderListRootData;
import de.hybris.electronics.dto.order.list.WeChatOrderListResponseData;
import de.hybris.electronics.wechat.order.WeChatOrderDemoFacade;
import de.hybris.electronics.dto.order.WechatOrderHeaderData;
import de.hybris.electronics.dto.order.WechatOrderWsDTO;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoriesData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercewebservicescommons.dto.order.OrderHistoryListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.OrderWsDTO;
import de.hybris.platform.commercewebservicescommons.strategies.CartLoaderStrategy;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdAndUserIdParam;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import de.hybris.electronics.exceptions.NoCheckoutCartException;
import de.hybris.electronics.exceptions.PaymentAuthorizationException;
import de.hybris.electronics.strategies.OrderCodeIdentificationStrategy;
import de.hybris.electronics.v2.helper.OrdersHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Web Service Controller for the ORDERS resource. Most methods check orders of the user. Methods require authentication
 * and are restricted to https channel.
 */


@Controller
@RequestMapping(value = "/{baseSiteId}")
@Api(tags = "Orders")
public class OrdersController extends BaseCommerceController
{
	private static final Logger LOG = Logger.getLogger(OrdersController.class);

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;
	@Resource(name = "orderCodeIdentificationStrategy")
	private OrderCodeIdentificationStrategy orderCodeIdentificationStrategy;
	@Resource(name = "cartLoaderStrategy")
	private CartLoaderStrategy cartLoaderStrategy;
	@Resource(name = "ordersHelper")
	private OrdersHelper ordersHelper;
	@Resource(name = "weChatOrderDemoFacade")
	private WeChatOrderDemoFacade weChatOrderDemoFacade;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "baseStoreService")
	private BaseStoreService baseStoreService;
	@Resource(name = "customerAccountService")
	private CustomerAccountService customerAccountService;
	@Resource(name = "configurationService")
	private ConfigurationService configurationService;
	@Resource(name = "productConverter")
	private Converter<ProductModel, ProductData> productConverter;

	@Secured("ROLE_TRUSTED_CLIENT")
	@RequestMapping(value = "/orders/{code}", method = RequestMethod.GET)
	@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 120)
	@Cacheable(value = "orderCache", key = "T(de.hybris.platform.commercewebservicescommons.cache.CommerceCacheKeyGenerator).generateKey(false,true,'getOrder',#code,#fields)")
	@ResponseBody
	@ApiOperation(value = "Get a order", notes = "Returns details of a specific order based on the order GUID (Globally Unique Identifier) or the order CODE. The response contains detailed order information.", authorizations =
	{ @Authorization(value = "oauth2_client_credentials") })
	@ApiBaseSiteIdParam
	public OrderWsDTO getOrder(
			@ApiParam(value = "Order GUID (Globally Unique Identifier) or order CODE", required = true) @PathVariable final String code,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		OrderData orderData;
		if (orderCodeIdentificationStrategy.isID(code))
		{
			orderData = orderFacade.getOrderDetailsForGUID(code);
		}
		else
		{
			orderData = orderFacade.getOrderDetailsForCodeWithoutUser(code);
		}

		return getDataMapper().map(orderData, OrderWsDTO.class, fields);
	}


	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/users/{userId}/orders/{code}", method = RequestMethod.GET)
	@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 120)
	@Cacheable(value = "orderCache", key = "T(de.hybris.platform.commercewebservicescommons.cache.CommerceCacheKeyGenerator).generateKey(true,true,'getOrderForUserByCode',#code,#fields)")
	@ResponseBody
	@ApiOperation(value = "Get a order", notes = "Returns specific order details based on a specific order code. The response contains detailed order information.")
	@ApiBaseSiteIdAndUserIdParam
	public OrderWsDTO getOrderForUserByCode(
			@ApiParam(value = "Order GUID (Globally Unique Identifier) or order CODE", required = true) @PathVariable final String code,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final OrderData orderData = orderFacade.getOrderDetailsForCode(code);
		return getDataMapper().map(orderData, OrderWsDTO.class, fields);
	}



	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 120)
	@RequestMapping(value = "/users/{userId}/orders", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "Get order history for user", notes = "Returns order history data for all orders placed by a specified user for a specified base store. The response can display the results across multiple pages, if required.")
	@ApiBaseSiteIdAndUserIdParam
	public OrderHistoryListWsDTO getOrdersForUser(
			@ApiParam(value = "Filters only certain order statuses. For example, statuses=CANCELLED,CHECKED_VALID would only return orders with status CANCELLED or CHECKED_VALID.") @RequestParam(required = false) final String statuses,
			@ApiParam(value = "The current result page requested.") @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@ApiParam(value = "The number of results returned per page.") @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@ApiParam(value = "Sorting method applied to the return results.") @RequestParam(required = false) final String sort,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields,
			final HttpServletResponse response)
	{
		validateStatusesEnumValue(statuses);

		final OrderHistoryListWsDTO orderHistoryList = ordersHelper.searchOrderHistory(statuses, currentPage, pageSize, sort,
				addPaginationField(fields));

		// X-Total-Count header
		setTotalCountHeader(response, orderHistoryList.getPagination());

		return orderHistoryList;
	}


	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/users/{userId}/orders", method = RequestMethod.HEAD)
	@ResponseBody
	@ApiOperation(value = "Get total number of orders", notes = "In the response header, the \"x-total-count\" indicates the total number of orders placed by a specified user for a specified base store.")
	@ApiBaseSiteIdAndUserIdParam
	public void getCountOrdersForUser(
			@ApiParam(value = "Filters only certain order statuses. For example, statuses=CANCELLED,CHECKED_VALID would only return orders with status CANCELLED or CHECKED_VALID.") @RequestParam(required = false) final String statuses,
			final HttpServletResponse response)
	{
		final OrderHistoriesData orderHistoriesData = ordersHelper.searchOrderHistory(statuses, 0, 1, null);

		setTotalCountHeader(response, orderHistoriesData.getPagination());
	}


	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/users/{userId}/orders", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Post a order", notes = "Authorizes the cart and places the order. The response contains the new order data.")
	@ApiBaseSiteIdAndUserIdParam
	@SuppressWarnings("squid:S1160")
	public OrderWsDTO placeOrder(
			@ApiParam(value = "Cart code for logged in user, cart GUID for guest checkout", required = true) @RequestParam(required = true) final String cartId, //NOSONAR
			@ApiParam(value = "CCV security code.") @RequestParam(required = false) final String securityCode,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
			throws PaymentAuthorizationException, InvalidCartException, WebserviceValidationException, NoCheckoutCartException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.info("placeOrder");
		}

		cartLoaderStrategy.loadCart(cartId);

		validateCartForPlaceOrder();

		//authorize
		if (!getCheckoutFacade().authorizePayment(securityCode))
		{
			throw new PaymentAuthorizationException();
		}

		//placeorder
		final OrderData orderData = getCheckoutFacade().placeOrder();
		return getDataMapper().map(orderData, OrderWsDTO.class, fields);
	}

	@Secured(
			{ "ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/users/{userId}/wechat/place-order", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "WeChat place a order", notes = "Authorizes the cart and places the order. The response contains the new order data.")
	@ApiBaseSiteIdAndUserIdParam
	public WeChatOrderResponseData weChatPlaceOrder(
			@ApiParam(value = "Cart code for logged in user, cart GUID for guest checkout", required = true) @RequestParam(required = true) final String cartId, //NOSONAR
			@ApiParam(value = "CCV security code.") @RequestParam(required = false) final String securityCode,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
			throws PaymentAuthorizationException, InvalidCartException, WebserviceValidationException, NoCheckoutCartException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.info("placeOrder");
		}

		cartLoaderStrategy.loadCart(cartId);

		weChatOrderDemoFacade.setDeliveryMode();
		weChatOrderDemoFacade.setPaymentInfo();

		validateCartForPlaceOrder();

		//authorize
		if (!getCheckoutFacade().authorizePayment(securityCode))
		{
			throw new PaymentAuthorizationException();
		}

		//placeorder
		final OrderData orderData = getCheckoutFacade().placeOrder();
		OrderRootData orderRootData = new OrderRootData();
		orderRootData.setOrderId(orderData.getCode());
		orderRootData.setGrouponLinkId(orderData.getCode());
		WeChatOrderResponseData weChatOrderResponseData = new WeChatOrderResponseData();
		weChatOrderResponseData.setData(orderRootData);
		weChatOrderResponseData.setErrno(0);
		return weChatOrderResponseData;
	}

	@Secured(
			{"ROLE_CUSTOMERGROUP", "ROLE_CLIENT", "ROLE_CUSTOMERMANAGERGROUP", "ROLE_TRUSTED_CLIENT"})
	@RequestMapping(value = "/{userId}/order/prepay", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "pre pay a order", notes = "Authorizes the cart and places the order. The response contains the new order data.")
	@ApiBaseSiteIdAndUserIdParam
	public WechatOrderWsDTO prepayOrder(
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
			throws WebserviceValidationException {
		WechatOrderWsDTO wechatOrderWsDTO = new WechatOrderWsDTO();
		wechatOrderWsDTO.setErrno(0);
		WechatOrderHeaderData wechatOrderHeaderData = new WechatOrderHeaderData();
		wechatOrderHeaderData.setTimeStamp("1612348193000");
		wechatOrderHeaderData.setNonceStr("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
		wechatOrderHeaderData.setPackageValue("prepay_id=wx2017033010242291fcfe0db70013231072");
		wechatOrderHeaderData.setSignType("MD5");
		wechatOrderHeaderData.setPaySign("MD5(appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111) = 22D9B4E54AB1950F51E0649E8810ACD6");
		wechatOrderWsDTO.setData(wechatOrderHeaderData);
		return wechatOrderWsDTO;
	}

	@Secured({"ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP"})
	@CacheControl(directive = CacheControlDirective.PUBLIC, maxAge = 120)
	@RequestMapping(value = "/users/{userId}/wechat-orders", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "WeChat get order history for user", notes = "Returns order history data for all orders placed by a specified user for a specified base store. The response can display the results across multiple pages, if required.")
	@ApiBaseSiteIdAndUserIdParam
	public WeChatOrderListResponseData getOrdersForWeChatUser(
			@ApiParam(value = "Filters only certain order statuses. For example, statuses=CANCELLED,CHECKED_VALID would only return orders with status CANCELLED or CHECKED_VALID.") @RequestParam(required = false) final String statuses,
			@ApiParam(value = "The current result page requested.") @RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@ApiParam(value = "The number of results returned per page.") @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@ApiParam(value = "Sorting method applied to the return results.") @RequestParam(required = false) final String sort,
			@ApiParam(value = "Response configuration. This is the list of fields that should be returned in the response body.", allowableValues = "BASIC, DEFAULT, FULL") @RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields,
			final HttpServletResponse response) {
		validateStatusesEnumValue(statuses);

		final CustomerModel currentCustomer = (CustomerModel) userService.getCurrentUser();
		final BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();
		final PageableData pageableData = createPageableData(currentPage, pageSize, sort);
		final SearchPageData<OrderModel> orderResults = customerAccountService.getOrderList(currentCustomer, currentBaseStore,
				null, pageableData);

		OrderListRootData orderListRootData = new OrderListRootData();

		List<OrderListData> list = new ArrayList<>();
		orderResults.getResults().stream().sorted(Comparator.comparing(OrderModel::getCreationtime).reversed()).forEach(order -> {

			OrderListData orderListData = new OrderListData();
			orderListData.setActualPrice(String.valueOf(order.getTotalPrice().doubleValue()));
			List<GoodsList> goodsList = new ArrayList<>();
			order.getEntries().forEach(entry -> {
				GoodsList goods = new GoodsList();
				goods.setGoodsName(entry.getProduct().getName());
				goods.setId(entry.getProduct().getCode());
				goods.setNumber(String.valueOf(entry.getQuantity()));

				ProductData productData = productConverter.convert(entry.getProduct());
				if (CollectionUtils.isNotEmpty(productData.getImages())) {
					goods.setPicUrl(String.format("%s%s", getSiteUrl(), productData.getImages().iterator().next().getUrl()));
				}
				goodsList.add(goods);
			});
			orderListData.setGoodsList(goodsList);
			orderListData.setId(order.getCode());
			orderListData.setOrderSn(order.getCode());
			orderListData.setOrderStatusText(order.getStatusDisplay());
			list.add(orderListData);
		});
		orderListRootData.setList(list);
		// X-Total-Count header
		setTotalCountHeader(response, orderResults.getPagination());

		orderListRootData.setPages(orderResults.getPagination().getPageSize());
		WeChatOrderListResponseData weChatOrderListResponseData = new WeChatOrderListResponseData();
		weChatOrderListResponseData.setData(orderListRootData);
		weChatOrderListResponseData.setErrno(0);
		return weChatOrderListResponseData;
	}

	protected PageableData createPageableData(final int currentPage, final int pageSize, final String sort) {
		final PageableData pageable = new PageableData();
		pageable.setCurrentPage(currentPage);
		pageable.setPageSize(pageSize);
		pageable.setSort(sort);
		return pageable;
	}

	private String getSiteUrl() {
		return configurationService.getConfiguration().getString("demo.image.url", "https://electronics.local:9002/electronicsonlinestorefront");
	}
}
