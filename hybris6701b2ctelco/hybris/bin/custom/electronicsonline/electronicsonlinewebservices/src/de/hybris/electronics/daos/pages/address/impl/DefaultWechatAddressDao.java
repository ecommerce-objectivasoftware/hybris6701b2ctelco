package de.hybris.electronics.daos.pages.address.impl;

import de.hybris.electronics.daos.pages.address.WechatAddressDao;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.daos.pages.address.impl
 * @ClassName: DefaultWechatAddressDao
 * @Description:
 * @date 2/5/2021 3:57 PM
 */
public class DefaultWechatAddressDao implements WechatAddressDao {

    private static final String GET_ADDRESS_LIST = "select {"+AddressModel.PK + "} from {"
            + AddressModel._TYPECODE + "}";
    private static final String GET_ADDRESS_BY_ID = "";
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<AddressModel> getAddressById(String userId) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_ADDRESS_BY_ID);
        query.addQueryParameter("userId", userId);
        return flexibleSearchService.<AddressModel> search(query).getResult();

    }

    @Override
    public List<AddressModel> getAddressList() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_ADDRESS_LIST);
        query.setCount(3);
        return flexibleSearchService.<AddressModel> search(query).getResult();

    }
}
