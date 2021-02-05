package de.hybris.electronics.dto.user;

import java.io.Serializable;

public class WeChatLoginUserData implements Serializable {

    private UserRootData data;

    private int errno = 1;

    public void setData(UserRootData data) {
        this.data = data;
    }

    public UserRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }
}
