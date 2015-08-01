package com.easyleancode.geometry.models;

import java.io.Serializable;

public class ElementThree implements Serializable {
    private String owner;
    private String relationship;
    private String target;

    public ElementThree(String owner, String relationship, String target) {
        this.setOwner(owner);
        this.setRelationship(relationship);
        this.setTarget(target);
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
}
