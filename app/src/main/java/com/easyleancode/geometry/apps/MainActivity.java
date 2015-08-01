package com.easyleancode.geometry.apps;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.easyleancode.geometry.R;
import com.easyleancode.geometry.adapters.ExpandableAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.image_help)
    RelativeLayout imageHelp;
    @Bind(R.id.close_help)
    ImageView closeHelp;
    @Bind(R.id.recycle_shape)
    RecyclerView recyclerShape;
    @Bind(R.id.btn_add)
    FloatingActionButton btnAdd;
    @Bind(R.id.popup_menu)
    LinearLayout popupMenu;
    @Bind(R.id.angle)
    FloatingActionButton btnAngle;

    LinearLayoutManager layoutManager;
    ExpandableAdapter expandableAdapter;
    List<Object> collections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        initRecycleView();
    }

    private void setGeometry() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.geometry_view, new DrawingFragment())
                .disallowAddToBackStack().commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycleView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerShape.setHasFixedSize(true);
        recyclerShape.setLayoutManager(layoutManager);
        setExpandableAdapter(new ArrayList<>());
    }

    private void setExpandableAdapter(List<Object> collections) {
        if (expandableAdapter == null) {
            expandableAdapter = new ExpandableAdapter(this, collections);
            recyclerShape.setAdapter(expandableAdapter);
        } else {
            expandableAdapter.setCollections(collections);
        }
    }

    @OnClick(R.id.close_help)
    protected void closeHelpNote() {
        imageHelp.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_add)
    protected void handlerPopupMenu() {
        if (popupMenu.getVisibility() == View.VISIBLE) {
            popupMenu.setVisibility(View.GONE);
            btnAdd.setBackgroundTintList(getColor(R.color.main_red));
        } else {
            popupMenu.setVisibility(View.VISIBLE);
            btnAdd.setBackgroundTintList(getColor(R.color.main_green));
        }
    }

    private ColorStateList getColor(int resId) {
        return ColorStateList.valueOf(getResources().getColor(resId));
    }
}
