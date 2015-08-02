package com.easyleancode.geometry.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        viewHolder.txtElementType.setText("".equals(element.getRelationship()) ? Constant.PYRAMID_TOP : element.getRelationship());

        if (position == getCount() - 1) {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.drawable.plus_card));
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
            viewHolder.txtElementCustom.setVisibility(View.GONE);
        } else {
            viewHolder.imageElement.setImageDrawable(Utils.getDrawable(context, R.drawable.sub_card));
            viewHolder.txtElementCustom.setText(element.getTarget());
            viewHolder.txtElementValue.setText(element.getValue());
            viewHolder.txtElementValue.setVisibility(View.VISIBLE);
            viewHolder.txtLayout.setVisibility(View.VISIBLE);
            if (position == 0 || position == 1) {
                viewHolder.txtLayout.setVisibility(View.GONE);
                viewHolder.txtElementCustom.setVisibility(View.GONE);
                viewHolder.btnDelete.setVisibility(View.GONE);
            } else {
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
        }
        viewHolder.txtElementValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                element.setValue(viewHolder.txtElementValue.getText().toString());
            }
        });
        viewHolder.txtElementCustom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                element.setTarget(viewHolder.txtElementCustom.getText().toString());
            }
        });
        return view;
    }

    public List<ElementRelation> getElements() {
        return elements;
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
        LinearLayout txtLayout;
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
