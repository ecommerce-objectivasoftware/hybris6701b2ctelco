package de.hybris.electronics.dto.pdp;

import java.io.Serializable;

public class Brand implements Serializable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
