package de.hybris.electronics.dto.cart.details;

import java.io.Serializable;

public class CartList implements Serializable {

    private String productId;

    private boolean checked;

    private String picUrl;

    private String goodsName;

    private String number;

    private String price;

    private String specifications;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return this.specifications;
    }
}
