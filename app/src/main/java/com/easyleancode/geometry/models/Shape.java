package com.easyleancode.geometry.models;

import java.io.Serializable;
import java.util.List;

public class Shape implements Serializable {
    private ShapeType type;
    private String name;
    private List<ElementRelation> elements;
    // isCollapse = null: have no element
    private Boolean isCollapsed;

    public Shape(String name, List<ElementRelation> elements) {
        this.setName(name);
        this.setElements(elements);
        this.setIsCollapsed(elements == null ? null : false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElementRelation> getElements() {
        return elements;
    }

    public void setElements(List<ElementRelation> elements) {
        this.elements = elements;
    }

    public Boolean isCollapsed() {
        return isCollapsed;
    }

    public void setIsCollapsed(Boolean isCollapsed) {
        this.isCollapsed = isCollapsed;
    }
}
