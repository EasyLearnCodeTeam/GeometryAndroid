package com.easyleancode.geometry.apps;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.easyleancode.geometry.models.ELGeo;
import com.easyleancode.geometry.surface.SurfaceRender;

public class ShapeActivity extends AppCompatActivity {
    public static final String COLLECTION = "COLLECTION";
    GLSurfaceView glSurfaceView;
    ELGeo elGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(this);
        setContentView(glSurfaceView);
        elGeo = (ELGeo) getIntent().getSerializableExtra(COLLECTION);
        try {
            glSurfaceView.setRenderer(new SurfaceRender(this, elGeo));
            glSurfaceView.setEGLContextClientVersion(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            glSurfaceView.onResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            glSurfaceView.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
