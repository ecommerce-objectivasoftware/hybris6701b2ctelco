package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class GrouponList implements Serializable {

    private int id;

    private String picUrl;

    private String name;

    private int grouponMember;

    private String expireTime;

    private String brief;

    private double retailPrice;

    private double grouponPrice;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
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
    public void setGrouponMember(int grouponMember){
        this.grouponMember = grouponMember;
    }
    public int getGrouponMember(){
        return this.grouponMember;
    }
    public void setExpireTime(String expireTime){
        this.expireTime = expireTime;
    }
    public String getExpireTime(){
        return this.expireTime;
    }
    public void setBrief(String brief){
        this.brief = brief;
    }
    public String getBrief(){
        return this.brief;
    }

    public double getRetailPrice()
    {
        return retailPrice;
    }

    public void setRetailPrice(final double retailPrice)
    {
        this.retailPrice = retailPrice;
    }

    public double getGrouponPrice()
    {
        return grouponPrice;
    }

    public void setGrouponPrice(final double grouponPrice)
    {
        this.grouponPrice = grouponPrice;
    }
}
