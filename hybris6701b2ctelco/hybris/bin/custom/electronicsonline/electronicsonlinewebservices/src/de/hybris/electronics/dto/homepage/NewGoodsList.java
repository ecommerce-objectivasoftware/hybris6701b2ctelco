package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class NewGoodsList implements Serializable {

    private String id;

    private String picUrl;

    private String name;

    private String retailPrice;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }
    public String getPicUrl(){
        return this.picUrl;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setRetailPrice(String retailPrice){
        this.retailPrice = retailPrice;
    }
    public String getRetailPrice(){
        return this.retailPrice;
    }
}
