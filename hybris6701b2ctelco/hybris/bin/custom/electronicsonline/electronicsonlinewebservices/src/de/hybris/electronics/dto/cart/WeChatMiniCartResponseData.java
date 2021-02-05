package de.hybris.electronics.dto.cart;

import java.io.Serializable;

public class WeChatMiniCartResponseData implements Serializable {

    private WeChatMiniCartRootData data;

    private int errno;

    public void setData(WeChatMiniCartRootData data) {
        this.data = data;
    }

    public WeChatMiniCartRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }
}
