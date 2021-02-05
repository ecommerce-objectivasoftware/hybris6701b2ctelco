package de.hybris.electronics.daos.pages.plp.impl;

import de.hybris.electronics.daos.pages.plp.WechatProductDao;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.daos.pages.plp.impl
 * @ClassName: DefaultWechatProductDao
 * @Description:
 * @date 2/4/2021 7:58 PM
 */
public class DefaultWechatProductDao implements WechatProductDao {
    private static final String GET_PRODUCT_LIST = "select {" + ProductModel.PK + "} from {" + ProductModel._TYPECODE
            +"} where {" + ProductModel.PICTURE +"} IS NOT NULL";
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<ProductModel> getProductList() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_PRODUCT_LIST);
        query.setCount(8);
        return flexibleSearchService.<ProductModel> search(query).getResult();
    }
}
