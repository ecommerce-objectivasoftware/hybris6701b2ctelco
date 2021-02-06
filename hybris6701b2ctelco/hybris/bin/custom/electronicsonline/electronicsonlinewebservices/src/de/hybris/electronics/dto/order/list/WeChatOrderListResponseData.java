package de.hybris.electronics.dto.order.list;

import java.io.Serializable;

public class WeChatOrderListResponseData implements Serializable {

    private OrderListRootData data;

    private int errno;

    public void setData(OrderListRootData data) {
        this.data = data;
    }

    public OrderListRootData getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }
}
