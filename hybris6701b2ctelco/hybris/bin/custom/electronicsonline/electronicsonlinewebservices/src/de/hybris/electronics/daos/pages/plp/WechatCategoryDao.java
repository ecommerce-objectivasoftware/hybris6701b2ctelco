package de.hybris.electronics.daos.pages.plp;

import de.hybris.platform.category.model.CategoryModel;

import java.util.List;

public interface WechatCategoryDao {
    List<CategoryModel> getCategoryList();

    CategoryModel getCategoryById(String categoryId);
}
