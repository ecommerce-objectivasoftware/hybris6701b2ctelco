package de.hybris.electronics.facades.pages.address;

import de.hybris.electronics.dto.address.WechatAddressBodyData;

import java.util.List;

public interface WechatAddressFacade {
    List<WechatAddressBodyData> getAddressList();

    List<WechatAddressBodyData> getAddressListByUserId(String userId);

    WechatAddressBodyData getAddressByAddressId(String addressId);

    void addAddressForCart(String addressId) throws Exception;
}
