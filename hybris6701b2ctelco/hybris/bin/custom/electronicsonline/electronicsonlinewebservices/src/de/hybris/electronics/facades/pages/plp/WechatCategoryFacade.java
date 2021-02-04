package de.hybris.electronics.facades.pages.plp;

import de.hybris.electronics.dto.plp.WechatCategoryBodyData;

import java.util.List;

public interface WechatCategoryFacade {

    List<WechatCategoryBodyData> getCategoryList();

    List<WechatCategoryBodyData> getCategoryById(String categoryId);
}