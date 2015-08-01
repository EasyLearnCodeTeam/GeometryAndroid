package com.easyleancode.geometry.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyleancode.geometry.R;
import com.easyleancode.geometry.models.ElementRelation;
import com.easyleancode.geometry.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ElementAdapter extends BaseAdapter {
    private List<ElementRelation> elements;
    private Context context;

    private OnAddElementListener addElementListener;

    public ElementAdapter(Context context, List<ElementRelation> elements, OnAddElementListener addElementListener) {
        this.context = context;
        this.elements = new ArrayList<>();
        if (elements != null) {
            this.elements.addAll(elements);
        }
        this.elements.add(new ElementRelation("", context.getString(R.string.add), ""));
        this.addElementListener = addElementListener;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_element_two, parent, false);
        final ElementRelation element = elements.get(position);
        final ViewHolder viewHolder = new ViewHolder(view);
        if (position == getCount() - 1) {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.mipmap.app_icon));
            viewHolder.txtElementValue.setVisibility(View.GONE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ElementRelation elementRelation = new ElementRelation();
                    elements.add(elementRelation);
                    if (addElementListener != null) {
                        addElementListener.onAddElement(getCount(), getView(getCount() - 2, null, null));
                    }
                }
            });
        } else {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.mipmap.app_icon));
            viewHolder.txtElementValue.setText(element.getValue());
            viewHolder.txtElementValue.setVisibility(View.VISIBLE);
        }
        viewHolder.txtElementType.setText(element.getRelationship());
        return view;
    }

    class ViewHolder {
        @Bind(R.id.img_element)
        ImageView imageElement;
        @Bind(R.id.txt_element_type)
        TextView txtElementType;
        @Bind(R.id.txt_element_value)
        EditText txtElementValue;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnAddElementListener {
        void onAddElement(int count, View view);
    }
}
