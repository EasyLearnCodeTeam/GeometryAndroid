package com.easyleancode.geometry.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyleancode.geometry.R;
import com.easyleancode.geometry.models.ElementRelation;
import com.easyleancode.geometry.utils.Constant;
import com.easyleancode.geometry.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ElementAdapter extends BaseAdapter {
    private List<ElementRelation> elements;
    private Context context;

    private OnChangeElementListener onChangeElementListener;

    public ElementAdapter(Context context, List<ElementRelation> elements, OnChangeElementListener onChangeElementListener) {
        this.context = context;
        this.elements = new ArrayList<>();
        if (elements != null) {
            this.elements.addAll(elements);
        }
        if (!context.getString(R.string.add).equalsIgnoreCase(this.elements.get(this.elements.size() - 1).getRelationship())) {
            this.elements.add(new ElementRelation("", context.getString(R.string.add), ""));
        }
        this.onChangeElementListener = onChangeElementListener;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public ElementRelation getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_element_two, parent, false);
        final ElementRelation element = elements.get(position);
        final ViewHolder viewHolder = new ViewHolder(view);
        if (position == getCount() - 1) {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.mipmap.app_icon));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentCount = getCount();
                    elements.add(currentCount - 1, new ElementRelation());
                    if (onChangeElementListener != null) {
                        onChangeElementListener.onAddElement(getView(currentCount - 1, null, null), currentCount, getElements());
                    }
                }
            });
            viewHolder.txtLayout.setVisibility(View.GONE);
        } else {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.mipmap.app_icon));
            viewHolder.txtElementValue.setText(element.getValue());
            viewHolder.txtElementValue.setVisibility(View.VISIBLE);
            if (Constant.PYRAMID_TOP.equalsIgnoreCase(element.getRelationship())
                    || Constant.PYRAMID_BOTTOM.equalsIgnoreCase(element.getRelationship())) {
                viewHolder.txtElementType.setVisibility(View.VISIBLE);
                viewHolder.txtElementCustom.setVisibility(View.GONE);
                viewHolder.btnDelete.setVisibility(View.GONE);
            } else {
                viewHolder.txtElementType.setVisibility(View.GONE);
                viewHolder.txtElementCustom.setVisibility(View.VISIBLE);
                viewHolder.btnDelete.setVisibility(View.VISIBLE);
                viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        elements.remove(position);
                        if (onChangeElementListener != null) {
                            onChangeElementListener.onRemoveElement(position, getElements());
                        }
                    }
                });
            }
            viewHolder.txtLayout.setVisibility(View.VISIBLE);

        }
        viewHolder.txtElementType.setText(element.getRelationship());
        return view;
    }

    public List<ElementRelation> getElements() {
        List<ElementRelation> list = new ArrayList<>();
        list.addAll(elements);
        // Remove button `Add`
        list.remove(list.size() - 1);
        return list;
    }

    class ViewHolder {
        @Bind(R.id.img_element)
        ImageView imageElement;
        @Bind(R.id.txt_element_type)
        TextView txtElementType;
        @Bind(R.id.txt_element_custom)
        EditText txtElementCustom;
        @Bind(R.id.txt_element_value)
        EditText txtElementValue;
        @Bind(R.id.txt_layout)
        RelativeLayout txtLayout;
        @Bind(R.id.btn_delete)
        ImageView btnDelete;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnChangeElementListener {
        void onAddElement(View view, int count, List<ElementRelation> elements);

        void onRemoveElement(int position, List<ElementRelation> elements);
    }
}
