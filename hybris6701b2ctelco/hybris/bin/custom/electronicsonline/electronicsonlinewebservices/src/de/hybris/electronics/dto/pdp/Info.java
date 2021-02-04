package de.hybris.electronics.dto.pdp;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {

    private String id;

    private double retailPrice;

    private double counterPrice;

    private List<String> gallery;

    private String name;

    private String detail;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getRetailPrice() {
        return this.retailPrice;
    }

    public void setCounterPrice(double counterPrice) {
        this.counterPrice = counterPrice;
    }

    public double getCounterPrice() {
        return this.counterPrice;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public List<String> getGallery() {
        return this.gallery;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return this.detail;
    }
}
