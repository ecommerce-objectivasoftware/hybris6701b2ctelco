package de.hybris.electronics.facades.pages.plp;

import de.hybris.electronics.dto.plp.WechatProductDetailData;

import java.util.List;

public interface WechatProductFacade {
    List<WechatProductDetailData> getProductList();
}
