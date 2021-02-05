package de.hybris.electronics.daos.pages.plp;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

public interface WechatProductDao {
    List<ProductModel> getProductList();
}
