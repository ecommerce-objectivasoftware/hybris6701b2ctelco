package de.hybris.electronics.dto.cart;

import java.io.Serializable;

public class WeChatMiniCartRootData implements Serializable {

    private String cartId;
    private String totalQty;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }
}
