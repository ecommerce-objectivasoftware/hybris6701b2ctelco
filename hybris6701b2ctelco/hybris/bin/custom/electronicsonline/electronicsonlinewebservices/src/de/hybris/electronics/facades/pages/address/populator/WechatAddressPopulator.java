package de.hybris.electronics.facades.pages.address.populator;

import de.hybris.electronics.dto.address.WechatAddressBodyData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.address.populator
 * @ClassName: WechatAddressPopulator
 * @Description:
 * @date 2/5/2021 3:51 PM
 */
public class WechatAddressPopulator implements Populator<AddressModel, WechatAddressBodyData> {

    @Resource
    private UserFacade userFacade;
    @Override
    public void populate(AddressModel addressModel, WechatAddressBodyData wechatAddressBodyData) throws ConversionException {
        wechatAddressBodyData.setName(addressModel.getFirstname().concat(addressModel.getLastname()));
        wechatAddressBodyData.setAddressDetail(addressModel.getLine1());
        wechatAddressBodyData.setTel(addressModel.getPhone1());
        AddressData addressData = userFacade.getDefaultAddress();
        final String id = addressModel.getPk().toString();
        wechatAddressBodyData.setId(Long.parseLong(id));
        wechatAddressBodyData.setIsDefault(id.equals(addressData.getId()));
    }
}
