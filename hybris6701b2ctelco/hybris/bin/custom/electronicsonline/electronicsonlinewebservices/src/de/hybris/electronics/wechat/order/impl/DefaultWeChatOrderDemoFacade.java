package de.hybris.electronics.wechat.order.impl;

import de.hybris.electronics.wechat.order.WeChatOrderDemoFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.RegionData;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Objects;

public class DefaultWeChatOrderDemoFacade implements WeChatOrderDemoFacade {

    private CheckoutFacade checkoutFacade;
    private CartFacade cartFacade;
    private UserService userService;
    private I18NFacade i18NFacade;
    private CustomerNameStrategy customerNameStrategy;

    @Override
    public void setDeliveryMode() {

        CartData cart = getCartFacade().getSessionCart();
        if (Objects.nonNull(cart.getDeliveryMode())) {
            return;
        }

        if (Objects.isNull(cart.getDeliveryAddress())) {
            getCheckoutFacade().setDeliveryAddress(fillInAddress());
        }

        getCheckoutFacade().setDeliveryModeIfAvailable();

    }

    @Override
    public void setPaymentInfo() {

        CartData cart = getCartFacade().getSessionCart();
        if (Objects.nonNull(cart.getPaymentInfo())) {
            return;
        }
        getCheckoutFacade().createPaymentSubscription(fillInPaymentData(cart));
        getCheckoutFacade().setPaymentInfoIfAvailable();
    }

    private CCPaymentInfoData fillInPaymentData(CartData cart) {

        CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
        paymentInfoData.setId("");
        paymentInfoData.setCardType("visa");
        paymentInfoData.setAccountHolderName(getUserService().getCurrentUser().getName());
        paymentInfoData.setCardNumber("1111111111111111");
        paymentInfoData.setStartMonth("05");
        paymentInfoData.setStartYear("2020");
        paymentInfoData.setExpiryMonth("05");
        paymentInfoData.setExpiryYear("2023");
        paymentInfoData.setSaved(true);
        paymentInfoData.setIssueNumber("0000");
        paymentInfoData.setBillingAddress(getBillingAddress(cart));
        paymentInfoData.setDefaultPaymentInfo(true);
        return paymentInfoData;
    }

    private AddressData getBillingAddress(CartData cart) {

        AddressData addressData;
        if (Objects.nonNull(cart.getDeliveryAddress())) {
            addressData = cart.getDeliveryAddress();
            addressData.setBillingAddress(true);
            addressData.setShippingAddress(true);
        } else {
            addressData = fillInAddress();
        }

        return addressData;
    }

    private AddressData fillInAddress() {

        final AddressData addressData = new AddressData();
        String[] names = getCustomerNameStrategy().splitName(getUserService().getCurrentUser().getName());
        addressData.setId(null);
        addressData.setTitleCode("mr");
        addressData.setFirstName(names[0]);
        addressData.setLastName(names[1]);
        addressData.setLine1("Zhong shan road");
        addressData.setLine2("No. 25");
        addressData.setTown("Guangzhou");
        addressData.setPostalCode("712000");
        addressData.setBillingAddress(true);
        addressData.setShippingAddress(true);
        addressData.setPhone("147258369");

        final CountryData countryData = getI18NFacade().getCountryForIsocode("CN");
        addressData.setCountry(countryData);

        final RegionData regionData = getI18NFacade().getRegion("CN", "CN-44");
        addressData.setRegion(regionData);


        return addressData;
    }

    private CartData getSessionCart() {
        return cartFacade.getSessionCart();
    }

    public CheckoutFacade getCheckoutFacade() {
        return checkoutFacade;
    }

    public void setCheckoutFacade(CheckoutFacade checkoutFacade) {
        this.checkoutFacade = checkoutFacade;
    }

    public CartFacade getCartFacade() {
        return cartFacade;
    }

    public void setCartFacade(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    public I18NFacade getI18NFacade() {
        return i18NFacade;
    }

    public void setI18NFacade(I18NFacade i18NFacade) {
        this.i18NFacade = i18NFacade;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CustomerNameStrategy getCustomerNameStrategy() {
        return customerNameStrategy;
    }

    public void setCustomerNameStrategy(CustomerNameStrategy customerNameStrategy) {
        this.customerNameStrategy = customerNameStrategy;
    }
}
