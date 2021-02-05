package de.hybris.electronics.services.pages.address;

import de.hybris.platform.core.model.user.AddressModel;

import java.util.List;

public interface WechatAddressService {
    List<AddressModel> getAddressList();

    List<AddressModel> getAddressById(String userId);
}
