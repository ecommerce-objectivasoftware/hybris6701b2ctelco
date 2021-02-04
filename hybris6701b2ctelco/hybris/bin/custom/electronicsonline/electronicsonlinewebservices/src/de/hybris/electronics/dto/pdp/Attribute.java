package de.hybris.electronics.dto.pdp;

import java.io.Serializable;

public class Attribute implements Serializable {

    private String name;

    private String attribute;

    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
