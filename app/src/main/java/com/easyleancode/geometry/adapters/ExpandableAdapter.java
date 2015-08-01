package com.easyleancode.geometry.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.easyleancode.geometry.R;
import com.easyleancode.geometry.apps.MainActivity;
import com.easyleancode.geometry.models.ElementRelation;
import com.easyleancode.geometry.models.Shape;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpandableAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Object> collections;
    private Context context;
    private int addedPosition = -1;

    private static final int SHAPE_TYPE = 1;
    private static final int ELEMENT_TYPE = 2;


    public ExpandableAdapter(Context context, List<Object> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == SHAPE_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_element_shape, viewGroup, false);
            return new ShapeViewHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_element_three, viewGroup, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Object object = collections.get(position);
        if (object instanceof Shape) {
            final Shape shape = (Shape) object;
            final ShapeViewHolder shapeViewHolder = (ShapeViewHolder) viewHolder;
            shapeViewHolder.shapeType.setText(shape.getName());
            String[] shapes = context.getResources().getStringArray(R.array.shapes);
            final ArrayAdapter<String> shapeTypeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, shapes);
            shapeViewHolder.shapeType.setAdapter(shapeTypeAdapter);
            if (position == addedPosition) {

            }
            shapeViewHolder.shapeType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shapeViewHolder.shapeType.showDropDown();
                }
            });
            shapeViewHolder.shapeType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int adapterPosition, long id) {
                    shape.setName(shapeTypeAdapter.getItem(adapterPosition));
                    ElementAdapter elementAdapter = new ElementAdapter(context, shape.getElements(), new ElementAdapter.OnAddElementListener() {
                        @Override
                        public void onAddElement(int count, View view) {
                            shapeViewHolder.elementLayout.addView(view, count - 2);
                        }
                    });
                    final int adapterCount = elementAdapter.getCount();
                    shapeViewHolder.elementLayout.removeAllViews();
                    for (int i = 0; i < adapterCount; i++) {
                        View item = elementAdapter.getView(i, null, null);
                        shapeViewHolder.elementLayout.addView(item);
                    }
                }
            });
            shapeViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    collections.remove(position);
                    addedPosition = -1;
                    shapeViewHolder.elementLayout.removeAllViews();
                    if (getItemCount() == 0) {
                        ((MainActivity) context).onCollectionEmpty();
                    }
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            });
        } else {
            ElementRelation elementRelation = (ElementRelation) object;
            ElementViewHolder elementViewHolder = (ElementViewHolder) viewHolder;
            // TODO
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = collections.get(position);
        if (object instanceof Shape) {
            return SHAPE_TYPE;
        }
        return ELEMENT_TYPE;
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public void setCollections(List<Object> collections) {
        this.collections = collections;
        notifyDataSetChanged();
    }

    public void setAddedPosition(int position) {
        addedPosition = position;
    }

    class ShapeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_shape)
        AutoCompleteTextView shapeType;
        @Bind(R.id.btn_delete)
        ImageView btnDelete;
        @Bind(R.id.element_layout)
        LinearLayout elementLayout;


        public ShapeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ElementViewHolder extends RecyclerView.ViewHolder {

        public ElementViewHolder(View itemView) {
            super(itemView);
        }
    }
}
