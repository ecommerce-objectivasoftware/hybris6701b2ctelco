package de.hybris.electronics.facades.pages.plp.populators;

import de.hybris.electronics.dto.plp.WechatCategoryBodyData;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.converters.Populator;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.populators
 * @ClassName: WechatCategoryPopulator
 * @Description:
 * @date 2/4/2021 1:55 PM
 */
public class WechatCategoryPopulator implements Populator<CategoryModel, WechatCategoryBodyData> {

    @Resource
    private ConfigurationService configurationService;
    @Override
    public void populate(CategoryModel categoryModel, WechatCategoryBodyData wechatCategoryBodyData) throws ConversionException {
        wechatCategoryBodyData.setId(categoryModel.getCode());
        wechatCategoryBodyData.setName(categoryModel.getName());
        wechatCategoryBodyData.setTitle(categoryModel.getDescription());
        wechatCategoryBodyData.setFrontName(categoryModel.getName());
        if(Objects.nonNull(categoryModel.getPicture()))
        {
            final String siteUrl = configurationService.getConfiguration().getString("media.electronics.https");
            wechatCategoryBodyData.setPicUrl(siteUrl.concat(categoryModel.getPicture().getURL()));
        }
    }
}
