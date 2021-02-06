package de.hybris.electronics.dto.order.list;

import java.io.Serializable;
import java.util.List;

public class OrderListRootData implements Serializable {

    private List<OrderListData> list;

    private int pages;

    public void setList(List<OrderListData> list) {
        this.list = list;
    }

    public List<OrderListData> getList() {
        return this.list;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPages() {
        return this.pages;
    }

}
