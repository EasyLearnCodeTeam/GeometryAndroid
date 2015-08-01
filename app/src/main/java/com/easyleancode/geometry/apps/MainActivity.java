package com.easyleancode.geometry.apps;

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

import com.easyleancode.geometry.R;
import com.easyleancode.geometry.adapters.ExpandableAdapter;
import com.easyleancode.geometry.models.Shape;
import com.easyleancode.geometry.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.image_teacher)
    ImageView imageTeacher;
    @Bind(R.id.recycle_shape)
    RecyclerView recyclerShape;
    @Bind(R.id.btn_add)
    FloatingActionButton btnAdd;
    @Bind(R.id.popup_menu)
    LinearLayout popupMenu;

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
        collections = new ArrayList<>();
        setExpandableAdapter(collections);
    }

    private void setExpandableAdapter(List<Object> collections) {
        if (expandableAdapter == null) {
            expandableAdapter = new ExpandableAdapter(this, collections);
            recyclerShape.setAdapter(expandableAdapter);
        } else {
            expandableAdapter.setCollections(collections);
        }
        imageTeacher.setVisibility(collections.isEmpty() ? View.VISIBLE : View.GONE);
    }

    public void onCollectionEmpty() {
        imageTeacher.setVisibility(View.VISIBLE);
        collections.clear();
    }

    @OnClick({R.id.btn_add, R.id.popup_menu})
    protected void handlerPopupMenu() {
        if (popupMenu.getVisibility() == View.VISIBLE) {
            popupMenu.setVisibility(View.GONE);
            btnAdd.setBackgroundTintList(Utils.getColor(this, R.color.main_red));
            btnAdd.setImageDrawable(Utils.getDrawable(this, R.drawable.plus_float_button));
        } else {
            popupMenu.setVisibility(View.VISIBLE);
            btnAdd.setBackgroundTintList(Utils.getColor(this, R.color.main_green));
            btnAdd.setImageDrawable(Utils.getDrawable(this, R.drawable.sub_float_button));
        }
    }

    @OnClick({R.id.angle, R.id.surface, R.id.line, R.id.segment, R.id.shape})
    protected void addElement(View view) {
        switch (view.getId()) {
            case R.id.angle:
                break;
            case R.id.surface:
                break;
            case R.id.line:
                break;
            case R.id.segment:
                break;
            case R.id.shape:
                collections.add(new Shape());
                if (expandableAdapter != null) {
                    expandableAdapter.setAddedPosition(collections.size() - 1);
                }
                break;
        }
        setExpandableAdapter(collections);
        handlerPopupMenu();
    }
}
