package de.hybris.electronics.dto.pdp;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {

    private int count;

    private List<Data> data;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
}
