package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class Channel implements Serializable {


    private int id;

    private String iconUrl;

    private String name;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }
    public String getIconUrl(){
        return this.iconUrl;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
