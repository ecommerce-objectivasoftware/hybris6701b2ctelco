package de.hybris.electronics.dto.pdp;

import java.io.Serializable;
import java.util.List;

public class SpecificationList implements Serializable {

    private String name;

    private List<ValueList> valueList;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setValueList(List<ValueList> valueList) {
        this.valueList = valueList;
    }

    public List<ValueList> getValueList() {
        return this.valueList;
    }

}
