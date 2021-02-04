package de.hybris.electronics.daos.pages.plp.impl;

import de.hybris.electronics.daos.pages.plp.WechatCategoryDao;
import de.hybris.platform.acceleratorcms.constants.GeneratedAcceleratorCmsConstants;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.core.daos.pages.impl
 * @ClassName: DefaultWechatCategoryDao
 * @Description:
 * @date 2/4/2021 1:40 PM
 */
public class DefaultWechatCategoryDao implements WechatCategoryDao {

    private static final String GET_CATEGORY_LIST = "select {" + CategoryModel.PK + "} from {" + CategoryModel._TYPECODE
            +"} where {" + CategoryModel.PICTURE +"} IS NOT NULL";

    private static final String GET_CATEGORY_BY_ID = "select {" + CategoryModel.PK + "} from {" + CategoryModel._TYPECODE
            +"} where {"+ CategoryModel.CODE + "} = ?categoryId";
    @Resource
    private FlexibleSearchService flexibleSearchService;
    @Override
    public List<CategoryModel> getCategoryList() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_CATEGORY_LIST);
        query.setCount(20);
        return flexibleSearchService.<CategoryModel> search(query).getResult();
    }

    @Override
    public CategoryModel getCategoryById(String categoryId) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_CATEGORY_BY_ID);
        query.addQueryParameter("categoryId", categoryId);
        return flexibleSearchService.<CategoryModel> search(query).getResult().get(0);

    }
}
