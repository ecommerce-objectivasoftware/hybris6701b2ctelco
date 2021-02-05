package de.hybris.electronics.dto.cart.details;

import java.io.Serializable;

public class WeChatCartDetailsResponseData implements Serializable {

    private WeChatCartDetailsRootData data;

    private int errno;

    public void setData(WeChatCartDetailsRootData data) {
        this.data = data;
    }

    public WeChatCartDetailsRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }
}
