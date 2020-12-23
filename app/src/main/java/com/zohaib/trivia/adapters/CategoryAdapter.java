package com.zohaib.trivia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zohaib.trivia.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<String> categoriesList;
    LayoutInflater layoutInflater;

    public CategoryAdapter(Context ctx, List<String> categoriesList) {
        this.categoriesList = categoriesList;
        this.layoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public String getItem(int i) {
        return categoriesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = layoutInflater.inflate(R.layout.row_category, null);
        TextView tvName = itemView.findViewById(R.id.tv_ct_name);
        tvName.setText(categoriesList.get(i));
        return itemView;
    }
}
