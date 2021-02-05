package de.hybris.electronics.facades.pages.plp.populators;

import de.hybris.electronics.dto.plp.WechatProductDetailData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.plp.populators
 * @ClassName: WechatProductDetailPopulator
 * @Description:
 * @date 2/4/2021 8:45 PM
 */
public class WechatProductDetailPopulator implements Populator<ProductModel, WechatProductDetailData>  {

    @Resource
    private ConfigurationService configurationService;
    @Resource
    private PriceDataFactory priceDataFactory;
    @Resource
    private CommercePriceService commercePriceService;

    @Override
    public void populate(ProductModel productModel, WechatProductDetailData wechatProductDetailData) throws ConversionException {
        wechatProductDetailData.setId(productModel.getCode());
        wechatProductDetailData.setName(productModel.getName());
        wechatProductDetailData.setRetailPrice(getRetailPrice(productModel));
        String baseUrl = configurationService.getConfiguration().getString("media.electronics.https");
        wechatProductDetailData.setPicUrl(baseUrl.concat(productModel.getPicture().getURL()));
    }

    private Double getRetailPrice(ProductModel productModel)
    {
        Double retailPrice = 0D;
        final PriceDataType priceType;
        final PriceInformation info;
        if (CollectionUtils.isEmpty(productModel.getVariants()))
        {
            priceType = PriceDataType.BUY;
            info = commercePriceService.getWebPriceForProduct(productModel);
        }
        else
        {
            priceType = PriceDataType.FROM;
            info = commercePriceService.getFromPriceForProduct(productModel);
        }

        if (info != null)
        {
            final PriceData priceData = priceDataFactory.create(priceType, BigDecimal.valueOf(info.getPriceValue().getValue()),
                    info.getPriceValue().getCurrencyIso());
            retailPrice = priceData.getValue().setScale(2).doubleValue();
        }
        return retailPrice;
    }
}
