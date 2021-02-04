package de.hybris.electronics.services.pages.plp;

import de.hybris.platform.category.model.CategoryModel;

import java.util.List;

public interface WechatCategoryService {

    List<CategoryModel> getCategoryList();

    CategoryModel getCategoryById(String categoryId);
}
