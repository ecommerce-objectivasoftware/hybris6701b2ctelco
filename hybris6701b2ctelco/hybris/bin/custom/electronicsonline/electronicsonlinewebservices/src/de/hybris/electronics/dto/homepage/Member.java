package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class Member implements Serializable {
    private String icon;

    public void setIcon(String icon){
        this.icon = icon;
    }
    public String getIcon(){
        return this.icon;
    }
}
