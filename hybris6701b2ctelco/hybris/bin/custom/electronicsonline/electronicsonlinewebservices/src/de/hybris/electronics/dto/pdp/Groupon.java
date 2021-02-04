package de.hybris.electronics.dto.pdp;

import java.io.Serializable;

public class Groupon implements Serializable {

    private String id;

    private boolean checked;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return this.checked;
    }
}
