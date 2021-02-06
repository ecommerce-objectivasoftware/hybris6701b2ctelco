package de.hybris.electronics.dto.order;

public class OrderRootData {

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
