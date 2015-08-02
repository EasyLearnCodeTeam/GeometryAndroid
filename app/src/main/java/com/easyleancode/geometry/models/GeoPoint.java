package com.easyleancode.geometry.models;

import javax.microedition.khronos.opengles.GL10;

public class GeoPoint extends GeoBase {
    public float x;
    public float y;
    public float z;

    public GeoPoint(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
        indices = new byte[]{0};
        initBuffer(null, indices, this);
        super.draw(gl, GL10.GL_POINTS);
    }
}
