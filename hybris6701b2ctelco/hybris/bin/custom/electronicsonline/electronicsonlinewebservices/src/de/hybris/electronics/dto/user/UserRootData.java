package de.hybris.electronics.dto.user;

import java.io.Serializable;

public class UserRootData implements Serializable {

    private UserInfo userInfo;

    private String token;

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
