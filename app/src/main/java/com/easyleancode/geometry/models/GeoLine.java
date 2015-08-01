package com.easyleancode.geometry.models;

public class GeoLine extends GeoBase {
    public GeoPoint startPoint;
    public GeoPoint endPoint;

    public GeoLine(GeoPoint startPoint, GeoPoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        initBuffer(startPoint, endPoint);
    }
}
