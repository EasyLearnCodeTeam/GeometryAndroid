package com.easyleancode.geometry.models;

import java.io.Serializable;

public class ElementTwo implements Serializable {
    private String type;
    private String value;

    public ElementTwo(String type, String value) {
        this.setType(type);
        this.setValue(value);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
