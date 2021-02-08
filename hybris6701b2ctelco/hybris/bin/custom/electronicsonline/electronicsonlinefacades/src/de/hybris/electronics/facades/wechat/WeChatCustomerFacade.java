package de.hybris.electronics.facades.wechat;

import de.hybris.platform.core.model.user.UserModel;

public interface WeChatCustomerFacade {

    UserModel getUserByPhoneNumber(String phoneNumber);

}
