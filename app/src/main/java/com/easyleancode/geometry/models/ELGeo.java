package com.easyleancode.geometry.models;

import java.io.Serializable;
import java.util.List;

public class ELGeo implements Serializable {
    List<Serializable> collections;

    public ELGeo(List<Serializable> collections) {
        this.collections = collections;
    }
}
