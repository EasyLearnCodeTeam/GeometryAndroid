package com.easyleancode.geometry.models;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GeoBase implements Serializable {
    protected FloatBuffer vertexBuffer;  // Buffer for vertex-array
    protected ByteBuffer indexBuffer;    // Buffer for index-array
    protected byte[] indices = {0, 1, 2};

    protected void initBuffer(float[] verticals) {
        // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
        ByteBuffer vbb = ByteBuffer.allocateDirect(verticals.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(verticals);
        vertexBuffer.position(0);

        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    protected void initBuffer(GeoPoint... points) {
        initBuffer(pointToArray(points));
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
    public void draw(GL10 gl) {
        // Enable vertex-array and define the buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        // Draw the primitives via index-array
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
