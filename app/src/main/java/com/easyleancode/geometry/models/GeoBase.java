package com.easyleancode.geometry.models;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GeoBase implements Serializable {
    public static float[] BLACK = new float[]{0f, 0f, 0f, 1f};
    public static float[] WHITE = new float[]{1f, 1f, 1f, 1f};
    public static float[] RED = new float[]{1f, 0f, 0f, 1f};
    public static float[] GREEN = new float[]{0f, 1f, 0f, 1f};
    public static float[] BLUE = new float[]{0f, 0f, 1f, 1f};
    protected FloatBuffer vertexBuffer;
    protected ByteBuffer indexBuffer;
    protected FloatBuffer colorBuffer;

    protected byte[] indices;

    protected void initBuffer(float[] colors, byte[] indices, float[] verticals) {
        // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
        ByteBuffer vbb = ByteBuffer.allocateDirect(verticals.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(verticals);
        vertexBuffer.position(0);

        if (colors != null && colors.length > 0) {
            ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
            cbb.order(ByteOrder.nativeOrder());
            colorBuffer = cbb.asFloatBuffer();
            colorBuffer.put(colors);
            colorBuffer.position(0);
        }
        this.indices = indices;
        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    protected GeoPoint[] generateMiddlePoints(GeoPoint start, GeoPoint end) {
        GeoPoint range = new GeoPoint((end.x - start.x) / 15, (end.y - start.y) / 15, (end.z - start.z) / 15);
        GeoPoint[] points = new GeoPoint[16];
        for (int i = 0; i < 16; i++) {
            points[i] = new GeoPoint(start.x + i * range.x, start.y + i * range.y, start.z + i * range.z);
        }
        return points;
    }

    protected void initBuffer(float[] colors, byte[] indices, GeoPoint... points) {
        initBuffer(colors, indices, pointToArray(points));
    }

    protected float[] pointToArray(GeoPoint... points) {
        float[] array = new float[points.length * 3]; // 3D
        for (int i = 0; i < points.length; i++) {
            array[3 * i] = points[i].x;
            array[3 * i + 1] = points[i].y;
            array[3 * i + 2] = points[i].z;
        }
        return array;
    }

    // Render this shape
    public void draw(GL10 gl, int mode) {
        // Enable vertex-array and define the buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        // Draw the primitives via index-array
        gl.glDrawElements(mode, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
