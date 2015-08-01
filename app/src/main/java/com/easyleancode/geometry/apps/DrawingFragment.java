package com.easyleancode.geometry.apps;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyleancode.geometry.surface.SurfaceRender;

public class DrawingFragment extends Fragment {

    GLSurfaceView glSurfaceView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return glSurfaceView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new GLSurfaceView(getActivity());
        try {
            glSurfaceView.setRenderer(new SurfaceRender());
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
