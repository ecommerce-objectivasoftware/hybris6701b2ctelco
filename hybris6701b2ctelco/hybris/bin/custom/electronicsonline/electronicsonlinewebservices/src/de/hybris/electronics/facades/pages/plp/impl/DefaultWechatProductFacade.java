package de.hybris.electronics.facades.pages.plp.impl;

import de.hybris.electronics.dto.plp.WechatProductDetailData;
import de.hybris.electronics.facades.pages.plp.WechatProductFacade;
import de.hybris.electronics.services.pages.plp.WechatProductService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.plp.impl
 * @ClassName: DefaultWechatProductFacade
 * @Description:
 * @date 2/4/2021 7:56 PM
 */
public class DefaultWechatProductFacade implements WechatProductFacade {
    @Resource
    private WechatProductService wechatProductService;

    @Resource
    private Converter<ProductModel, WechatProductDetailData> wechatProductDetailDataConverter;

    @Override
    public List<WechatProductDetailData> getProductList() {
        List<ProductModel> productModelList = wechatProductService.getProductList();
        return wechatProductDetailDataConverter.convertAll(productModelList);
    }
}
