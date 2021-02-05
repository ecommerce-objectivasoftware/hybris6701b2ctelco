package de.hybris.electronics.wechat.user.impl;

import de.hybris.electronics.dto.user.UserInfo;
import de.hybris.electronics.dto.user.UserRootData;
import de.hybris.electronics.dto.user.WeChatLoginUserData;
import de.hybris.electronics.wechat.user.WeChatDemoUserFacade;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

public class DefaultWeChatDemoUserFacade implements WeChatDemoUserFacade {

    private UserService userService;
    private PasswordEncoderService passwordEncoderService;
    private CustomerNameStrategy customerNameStrategy;

    @Override
    public WeChatLoginUserData weChatUserLogin(String userUid, String password) {

        final CustomerModel currentUser = (CustomerModel) getUserService().getCurrentUser();
        WeChatLoginUserData weChatLoginUserData = new WeChatLoginUserData();
        // Validate that the current password is correct
        if (Objects.nonNull(currentUser) && getPasswordEncoderService().isValid(currentUser, password)) {
            weChatLoginUserData.setErrno(0);
            UserRootData userRootData = new UserRootData();
            userRootData.setToken(StringUtils.EMPTY);
            UserInfo userInfo = new UserInfo();
            userInfo.setAvatarUrl("/static/images/avatar.png");
            userInfo.setEmail(currentUser.getUid());
            userInfo.setNickName(getCustomerNameStrategy().splitName(currentUser.getName())[0]);
            userRootData.setUserInfo(userInfo);
            weChatLoginUserData.setData(userRootData);
        }

        return weChatLoginUserData;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PasswordEncoderService getPasswordEncoderService() {
        return passwordEncoderService;
    }

    public void setPasswordEncoderService(PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }

    public CustomerNameStrategy getCustomerNameStrategy() {
        return customerNameStrategy;
    }

    public void setCustomerNameStrategy(CustomerNameStrategy customerNameStrategy) {
        this.customerNameStrategy = customerNameStrategy;
    }
}
