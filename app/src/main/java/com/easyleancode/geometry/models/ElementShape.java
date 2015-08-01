package com.easyleancode.geometry.models;

import java.io.Serializable;
import java.util.List;

public class ElementShape implements Serializable {
    private String name;
    private List<Object> elements;
    private Class cls;

    public ElementShape(String name, List<Object> elements, Class cls) {
        this.setName(name);
        this.setElements(elements);
        this.setCls(cls);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getElements() {
        return elements;
    }

    public void setElements(List<Object> elements) {
        this.elements = elements;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}
