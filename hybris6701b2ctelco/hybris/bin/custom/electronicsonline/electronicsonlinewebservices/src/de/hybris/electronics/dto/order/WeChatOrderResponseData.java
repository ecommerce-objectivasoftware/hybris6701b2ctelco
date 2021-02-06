package de.hybris.electronics.dto.order;

import java.io.Serializable;

public class WeChatOrderResponseData implements Serializable {

    private OrderRootData data;

    private int errno;

    public void setData(OrderRootData data) {
        this.data = data;
    }

    public OrderRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }

}
