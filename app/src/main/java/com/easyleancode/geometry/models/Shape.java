package com.easyleancode.geometry.models;

import com.easyleancode.geometry.utils.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Serializable {
    private String type;
    private String name;
    private List<ElementRelation> elements;

    public Shape() {
        this("", "", null);
    }

    public Shape(String type, String name, List<ElementRelation> elements) {
        this.setType(type);
        this.setName(name);
        this.setElements(elements);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if (Constant.PYRAMID.equalsIgnoreCase(this.type)) {
            elements = new ArrayList<>();
            elements.add(new ElementRelation(this.name, Constant.PYRAMID_TOP, ""));
            elements.add(new ElementRelation(this.name, Constant.PYRAMID_BOTTOM, ""));
        } else if (Constant.PRISMATIC.equalsIgnoreCase(this.type)) {
            // TODO: set value for prismatic instinct hardcode
            elements = new ArrayList<>();
            elements.add(new ElementRelation(this.name, Constant.PYRAMID_TOP, ""));
            elements.add(new ElementRelation(this.name, Constant.PYRAMID_BOTTOM, ""));
        }
    }
}
