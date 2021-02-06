package de.hybris.electronics.dto.order;

import java.io.Serializable;

public class OrderRootData implements Serializable {

    private String orderId;
    private String grouponLinkId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGrouponLinkId() {
        return grouponLinkId;
    }

    public void setGrouponLinkId(String grouponLinkId) {
        this.grouponLinkId = grouponLinkId;
    }
}
