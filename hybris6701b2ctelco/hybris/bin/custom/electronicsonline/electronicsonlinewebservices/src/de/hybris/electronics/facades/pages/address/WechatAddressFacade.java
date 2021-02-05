package de.hybris.electronics.facades.pages.address;

import de.hybris.electronics.dto.address.WechatAddressBodyData;

import java.util.List;

public interface WechatAddressFacade {
    List<WechatAddressBodyData> getAddressList();

    List<WechatAddressBodyData> getAddressById(String userId);
}
