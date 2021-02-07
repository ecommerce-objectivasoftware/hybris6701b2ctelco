package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class WeChatHomepageResponseData implements Serializable {

    private HomepageRootData data;

    private int errno;

    public void setData(HomepageRootData data) {
        this.data = data;
    }

    public HomepageRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }

}
