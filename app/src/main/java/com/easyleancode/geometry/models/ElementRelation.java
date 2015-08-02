package com.easyleancode.geometry.models;

import java.io.Serializable;

public class ElementRelation implements Serializable {
    public static final String ANGLE="ANGLE";
    public static final String POINT="POINT";
    public static final String SURFACE="SURFACE";
    public static final String SEGMENT="SEGMENT";
    // Ex: AB vuong goc BC tai D : (AB,VG,BC,D)
    // AB = CD = a : (AB,=,a) + (CD,=,a)
    // I la trung diem BC : (BC,TD,I)
    // TG ABC co tam la O : (ABC,T,O)
    // Hinh chop S.ABCD co day la ABCD : (S.ABCD,D,ABCD)
    private String owner;
    private String relationship;
    private String target;
    private String value;
    private String type;

    public ElementRelation(String owner, String relationship, String target, String value) {
        this.setOwner(owner);
        this.setRelationship(relationship);
        this.setTarget(target);
        this.setValue(value);
    }

    public ElementRelation(String owner, String relationship, String target) {
        this(owner, relationship, target, "");
    }

    public ElementRelation(String type) {
        this.type = type;
    }

    public ElementRelation() {
        this("", "", "");
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
