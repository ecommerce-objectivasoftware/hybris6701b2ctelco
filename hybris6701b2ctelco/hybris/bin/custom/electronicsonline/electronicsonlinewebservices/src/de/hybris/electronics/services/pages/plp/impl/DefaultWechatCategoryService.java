package de.hybris.electronics.services.pages.plp.impl;

import de.hybris.electronics.daos.pages.plp.WechatCategoryDao;
import de.hybris.electronics.services.pages.plp.WechatCategoryService;
import de.hybris.platform.category.model.CategoryModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.core.services.pages.impl
 * @ClassName: DefaultWechatCategoryService
 * @Description:
 * @date 2/4/2021 1:35 PM
 */
public class DefaultWechatCategoryService implements WechatCategoryService {

    @Resource
    private WechatCategoryDao wechatCategoryDao;
    @Override
    public List<CategoryModel> getCategoryList() {
        return wechatCategoryDao.getCategoryList();
    }

    @Override
    public CategoryModel getCategoryById(String categoryId) {
        return wechatCategoryDao.getCategoryById(categoryId);
    }
}
