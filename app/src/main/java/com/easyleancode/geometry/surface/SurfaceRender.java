package com.easyleancode.geometry.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.easyleancode.geometry.models.ELGeo;
import com.easyleancode.geometry.models.GeoBase;
import com.easyleancode.geometry.models.GeoLine;
import com.easyleancode.geometry.models.GeoPoint;
import com.easyleancode.geometry.utils.Utils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SurfaceRender implements GLSurfaceView.Renderer {
    Context context;
    ELGeo elGeo;

    public SurfaceRender(Context context, ELGeo elGeo) {
        this.context = context;
        this.elGeo = elGeo;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        // Setting for surface view
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
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

        gl.glLoadIdentity();                 // Reset model-view matrix
        gl.glTranslatef(-1.5f, -0.5f, -3.0f); // Translate left and into the screen
        GeoPoint pA = new GeoPoint(1.0f, 0.4f, 0f);
        GeoPoint pB = new GeoPoint(1.6f, 0f, 0f);
        GeoPoint pC = new GeoPoint(2.0f, 0.4f, 0f);
        GeoPoint pS = new GeoPoint(1.6f, 1.0f, 0f);
        GeoLine geoLine = new GeoLine(pA, pB, GeoBase.BLACK);
        geoLine.draw(gl);
        geoLine = new GeoLine(pB, pC, GeoBase.BLACK);
        geoLine.draw(gl);
        geoLine = new GeoLine(pS, pA, GeoBase.BLACK);
        geoLine.draw(gl);
        geoLine = new GeoLine(pS, pB, GeoBase.BLACK);
        geoLine.draw(gl);
        geoLine = new GeoLine(pS, pC, GeoBase.BLACK);
        geoLine.draw(gl);
        geoLine = new GeoLine(pA, pC, GeoBase.BLACK);
        geoLine.drawDash(gl);
        // Test
        GeoPoint pAB = new GeoPoint((pA.x + pB.x) / 2, (pA.y + pB.y) / 2, (pA.z + pB.z) / 2);
        geoLine = new GeoLine(pAB, pS, GeoBase.BLACK);
        geoLine.draw(gl);
        Utils.draw(context, gl, "S");
        // TODO: Add real agrithm
    }
}
