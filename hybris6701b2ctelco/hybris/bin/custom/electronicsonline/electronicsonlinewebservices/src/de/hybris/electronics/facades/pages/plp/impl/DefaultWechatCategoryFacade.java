package de.hybris.electronics.facades.pages.plp.impl;

import de.hybris.electronics.services.pages.plp.WechatCategoryService;
import de.hybris.electronics.dto.plp.WechatCategoryBodyData;
import de.hybris.electronics.facades.pages.plp.WechatCategoryFacade;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.impl
 * @ClassName: DefaultWechatCategoryFacade
 * @Description:
 * @date 2/4/2021 1:06 PM
 */
public class DefaultWechatCategoryFacade implements WechatCategoryFacade {

@Resource
private WechatCategoryService wechatCategoryService;

@Resource
private Converter<CategoryModel, WechatCategoryBodyData> wechatCategoryDataConverter;

    @Override
    public List<WechatCategoryBodyData> getCategoryList()
    {
        final List<CategoryModel> categoryModelList = wechatCategoryService.getCategoryList();
        return wechatCategoryDataConverter.convertAll(categoryModelList);
    }

    @Override
    public List<WechatCategoryBodyData> getCategoryById(String categoryId) {
        final CategoryModel categoryModel = wechatCategoryService.getCategoryById(categoryId);
        final Collection<CategoryModel> subCategoryList = categoryModel.getAllSubcategories();
        if(!CollectionUtils.isEmpty(subCategoryList)) {
            return wechatCategoryDataConverter.convertAll(subCategoryList);
        }
        return Collections.emptyList();

    }
}
