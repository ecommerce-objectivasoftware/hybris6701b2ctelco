package de.hybris.electronics.services.pages.address.impl;

import de.hybris.electronics.daos.pages.address.WechatAddressDao;
import de.hybris.electronics.services.pages.address.WechatAddressService;
import de.hybris.platform.core.model.user.AddressModel;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<AddressModel> getAddressList() {
        return wechatAddressDao.getAddressList();
    }

    @Override
    public List<AddressModel> getAddressById(String userId) {
        return wechatAddressDao.getAddressById(userId);
    }
}
