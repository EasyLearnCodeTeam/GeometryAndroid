package com.easyleancode.geometry.models;

public class GeoAngle extends GeoBase {
    public GeoPoint anglePoint;
    public GeoPoint startPoint;
    public GeoPoint endPoint;
    public float degree;

    public GeoAngle(GeoPoint anglePoint, GeoPoint startPoint, float degree) {

    }

    public GeoAngle(GeoPoint anglePoint, float degree, GeoPoint endPoint) {

    }

    public GeoAngle(GeoPoint anglePoint, GeoPoint start, GeoPoint end) {

    }
}
