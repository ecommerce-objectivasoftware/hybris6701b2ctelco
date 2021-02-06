package de.hybris.electronics.daos.pages.address.impl;

import de.hybris.electronics.daos.pages.address.WechatAddressDao;
import de.hybris.platform.core.PK;
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
    private static final String GET_ADDRESS_BY_ID = GET_ADDRESS_LIST + " Where {"+AddressModel.PK+"} =?id";
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public AddressModel getAddressById(String id) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_ADDRESS_BY_ID);
        query.addQueryParameter("id", id);
        return flexibleSearchService.<AddressModel> search(query).getResult().get(0);

    }

    @Override
    public List<AddressModel> getAddressList() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_ADDRESS_LIST);
        query.setCount(3);
        return flexibleSearchService.<AddressModel> search(query).getResult();

    }
}
