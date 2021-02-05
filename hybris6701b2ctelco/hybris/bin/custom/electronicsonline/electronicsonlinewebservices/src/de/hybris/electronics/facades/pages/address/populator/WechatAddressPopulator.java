package de.hybris.electronics.facades.pages.address.populator;

import de.hybris.electronics.dto.address.WechatAddressBodyData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author HarrisBai
 * @PackageName: de.hybris.electronics.facades.pages.address.populator
 * @ClassName: WechatAddressPopulator
 * @Description:
 * @date 2/5/2021 3:51 PM
 */
public class WechatAddressPopulator implements Populator<AddressModel, WechatAddressBodyData> {
    @Override
    public void populate(AddressModel addressModel, WechatAddressBodyData wechatAddressBodyData) throws ConversionException {
        wechatAddressBodyData.setId(Long.parseLong(addressModel.getPk().toString()));
        wechatAddressBodyData.setName(addressModel.getFirstname().concat(addressModel.getLastname()));
        wechatAddressBodyData.setTel(addressModel.getCellphone());
        wechatAddressBodyData.setAddressDetail(addressModel.getLine1());
    }
}
