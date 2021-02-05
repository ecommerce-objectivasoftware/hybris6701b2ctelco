package de.hybris.electronics.services.pages.plp.impl;

import de.hybris.electronics.daos.pages.plp.WechatProductDao;
import de.hybris.electronics.services.pages.plp.WechatProductService;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.services.pages.plp.impl
 * @ClassName: DefaultWechatProductService
 * @Description:
 * @date 2/4/2021 7:57 PM
 */
public class DefaultWechatProductService implements WechatProductService {

    @Resource
    private WechatProductDao wechatProductDao;
    @Override
    public List<ProductModel> getProductList() {
        return wechatProductDao.getProductList();
    }
}
