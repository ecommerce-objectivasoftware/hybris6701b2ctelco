package de.hybris.electronics.dto.cart.details;

import java.io.Serializable;

public class CartTotal implements Serializable {

    private String goodsCount;

    private String goodsAmount;

    private String checkedGoodsCount;

    private String checkedGoodsAmount;

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsAmount() {
        return this.goodsAmount;
    }

    public void setCheckedGoodsCount(String checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public String getCheckedGoodsCount() {
        return this.checkedGoodsCount;
    }

    public void setCheckedGoodsAmount(String checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public String getCheckedGoodsAmount() {
        return this.checkedGoodsAmount;
    }

}
