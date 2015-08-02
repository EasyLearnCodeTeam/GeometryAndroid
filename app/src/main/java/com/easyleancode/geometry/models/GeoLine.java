package com.easyleancode.geometry.models;

import javax.microedition.khronos.opengles.GL10;

public class GeoLine extends GeoBase {
    public GeoPoint startPoint;
    public GeoPoint endPoint;
    public float[] colors;

    public GeoLine(GeoPoint startPoint, GeoPoint endPoint, float[] colors) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.colors = colors;
        indices = new byte[]{0, 1};
        initBuffer(colors, indices, startPoint, endPoint);
    }

    public void draw(GL10 gl) {
        super.draw(gl, GL10.GL_LINES);
    }

    public void drawDash(GL10 gl) {
        GeoPoint[] points = generateMiddlePoints(startPoint, endPoint);
        indices = new byte[points.length];
        for (byte i = 0; i < points.length; i++) {
            indices[i] = i;
        }
        initBuffer(colors, indices, points);
        super.draw(gl, GL10.GL_LINES);
    }
}
