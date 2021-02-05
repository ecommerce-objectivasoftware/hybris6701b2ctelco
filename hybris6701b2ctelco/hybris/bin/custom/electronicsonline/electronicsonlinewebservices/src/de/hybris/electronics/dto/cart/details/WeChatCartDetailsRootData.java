package de.hybris.electronics.dto.cart.details;

import java.io.Serializable;
import java.util.List;

public class WeChatCartDetailsRootData implements Serializable {

    private List<CartList> cartList;

    private CartTotal cartTotal;

    public void setCartList(List<CartList> cartList) {
        this.cartList = cartList;
    }

    public List<CartList> getCartList() {
        return this.cartList;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public CartTotal getCartTotal() {
        return this.cartTotal;
    }
}
