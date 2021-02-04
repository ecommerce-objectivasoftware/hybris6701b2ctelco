package de.hybris.electronics.dto.pdp;

import java.io.Serializable;

public class WeChatProductData implements Serializable {

    private RootData data;

    private int errno;

    public void setData(RootData data) {
        this.data = data;
    }

    public RootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }
}
