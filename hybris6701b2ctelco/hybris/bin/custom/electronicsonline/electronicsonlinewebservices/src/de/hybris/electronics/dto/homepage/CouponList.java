package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class CouponList implements Serializable {

    private int id;

    private String tag;

    private String discount;

    private String min;

    private String name;

    private String desc;

    private String days;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }
    public void setDiscount(String discount){
        this.discount = discount;
    }
    public String getDiscount(){
        return this.discount;
    }
    public void setMin(String min){
        this.min = min;
    }
    public String getMin(){
        return this.min;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setDays(String days){
        this.days = days;
    }
    public String getDays(){
        return this.days;
    }
}
