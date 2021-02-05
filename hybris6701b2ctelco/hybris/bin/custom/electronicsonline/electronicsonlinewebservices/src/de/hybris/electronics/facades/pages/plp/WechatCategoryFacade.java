package de.hybris.electronics.facades.pages.plp;

import de.hybris.electronics.dto.plp.WechatCategoryBodyData;

import java.util.List;

public interface WechatCategoryFacade {

    List<WechatCategoryBodyData> getCategoryList();

    List<WechatCategoryBodyData> getSubCategoryById(String categoryId);

    List<WechatCategoryBodyData> getSuperCategoryById(String categoryId);

    List<WechatCategoryBodyData> getCategoryByIds(String[] ids);

    WechatCategoryBodyData getCategoryById(String categoryId);

}
