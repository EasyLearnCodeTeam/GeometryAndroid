package com.easyleancode.geometry.surface;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.easyleancode.geometry.models.GeoLine;
import com.easyleancode.geometry.models.GeoPoint;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SurfaceRender implements GLSurfaceView.Renderer {
    GeoLine geoLine;

    public SurfaceRender() {
        geoLine = new GeoLine(new GeoPoint(0.3f, 0.1f, 0.2f), new GeoPoint(0.2f, 0.1f, 0.1f));
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        // Setting for surface view
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) {
            height = 1;
        }
        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers using clear-values set earlier
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )
        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen ( NEW )
        geoLine.draw(gl);                   // Draw triangle ( NEW )

        // Translate right, relative to the previous translation ( NEW )
//        gl.glTranslatef(3.0f, 0.0f, 0.0f);
//        quad.draw(gl);
    }
}
