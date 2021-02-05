package de.hybris.electronics.dto.cart;

public class WeChatAddToCartResponseData {

    private long data;

    private int errno;

    public void setData(long data) {
        this.data = data;
    }

    public long getData() {
        return this.data;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return this.errno;
    }

}
