package de.hybris.electronics.dto.order.list;

import java.io.Serializable;
import java.util.List;

public class OrderListData implements Serializable {

    private String id;

    private String orderSn;

    private String orderStatusText;

    private String actualPrice;

    private List<GoodsList> goodsList;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return this.orderSn;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public String getOrderStatusText() {
        return this.orderStatusText;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getActualPrice() {
        return this.actualPrice;
    }

    public void setGoodsList(List<GoodsList> goodsList) {
        this.goodsList = goodsList;
    }

    public List<GoodsList> getGoodsList() {
        return this.goodsList;
    }
}
