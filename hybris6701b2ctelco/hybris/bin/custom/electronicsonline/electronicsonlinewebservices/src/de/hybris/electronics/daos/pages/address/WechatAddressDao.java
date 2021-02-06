package de.hybris.electronics.daos.pages.address;

import de.hybris.platform.core.model.user.AddressModel;

import java.util.List;

public interface WechatAddressDao {
    AddressModel getAddressById(String id);

    List<AddressModel> getAddressList();
}
