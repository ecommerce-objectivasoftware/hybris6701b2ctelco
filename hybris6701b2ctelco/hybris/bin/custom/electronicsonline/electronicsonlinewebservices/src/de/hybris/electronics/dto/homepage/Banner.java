package de.hybris.electronics.dto.homepage;

import java.io.Serializable;

public class Banner implements Serializable {

    private int id;

    private int link;

    private String url;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLink(int link){
        this.link = link;
    }
    public int getLink(){
        return this.link;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
