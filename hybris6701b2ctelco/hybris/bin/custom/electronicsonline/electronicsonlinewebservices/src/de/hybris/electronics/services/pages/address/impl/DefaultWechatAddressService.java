package de.hybris.electronics.services.pages.address.impl;

import de.hybris.electronics.daos.pages.address.WechatAddressDao;
import de.hybris.electronics.services.pages.address.WechatAddressService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.services.pages.address
 * @ClassName: DefaultWechatAddressService
 * @Description:
 * @date 2/5/2021 3:53 PM
 */
public class DefaultWechatAddressService implements WechatAddressService {
    @Resource
    private WechatAddressDao wechatAddressDao;
    @Resource
    private ModelService modelService;

    @Override
    public List<AddressModel> getAddressList() {
        return wechatAddressDao.getAddressList();
    }

    @Override
    public AddressModel getAddressById(String id) {
        return wechatAddressDao.getAddressById(id);
    }

    @Override
    public void addAddressForCart(CartModel sessionCart, String addressId) throws Exception {
        final AddressModel addressModel = wechatAddressDao.getAddressById(addressId);
        if(Objects.nonNull(addressModel))
        {
            sessionCart.setDeliveryAddress(addressModel);
            modelService.save(sessionCart);
        }
        else
        {
            throw new Exception("No Address Found!");
        }
    }
}
