package de.hybris.electronics.services.pages.address;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;

import java.util.List;

public interface WechatAddressService {
    List<AddressModel> getAddressList();

    AddressModel getAddressById(String id);

    void addAddressForCart(CartModel sessionCart, String addressId) throws Exception;
}
