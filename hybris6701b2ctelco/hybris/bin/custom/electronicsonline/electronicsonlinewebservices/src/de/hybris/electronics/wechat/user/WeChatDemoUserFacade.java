package de.hybris.electronics.wechat.user;

import de.hybris.electronics.dto.user.WeChatLoginUserData;

public interface WeChatDemoUserFacade {

    WeChatLoginUserData weChatUserLogin(String userUid, String password);

}
