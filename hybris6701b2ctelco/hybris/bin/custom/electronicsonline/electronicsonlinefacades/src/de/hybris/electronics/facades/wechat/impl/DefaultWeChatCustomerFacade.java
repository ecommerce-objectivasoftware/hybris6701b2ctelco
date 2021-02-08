package de.hybris.electronics.facades.wechat.impl;

import de.hybris.electronics.facades.wechat.WeChatCustomerFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

public class DefaultWeChatCustomerFacade extends DefaultCustomerFacade implements WeChatCustomerFacade {

    private FlexibleSearchService flexibleSearchService;

    private static final String FIND_CUSTOMER_BY_PHONE = "SELECT {pk} FROM {" + CustomerModel._TYPECODE + "} WHERE {" + CustomerModel.MOBILEPHONE + "}=?phone";

    @Override
    protected void setUidForRegister(final RegisterData registerData, final CustomerModel customer) {
        customer.setUid(registerData.getLogin().toLowerCase());
        customer.setOriginalUid(registerData.getLogin());
        customer.setMobilePhone(registerData.getMobilePhone());
    }

    @Override
    public UserModel getUserByPhoneNumber(String phoneNumber) {

        FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_CUSTOMER_BY_PHONE);

        query.addQueryParameter("phone", phoneNumber);

        SearchResult<CustomerModel> recordList = getFlexibleSearchService().search(query);

        List<CustomerModel> customerList = recordList.getResult();

        return customerList.stream().findFirst().orElse(null);
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
