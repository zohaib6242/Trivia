package com.zohaib.trivia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zohaib.trivia.R;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private List<String> scoreList;
    private LayoutInflater layoutInflater;

    public ScoreAdapter(Context ctx, List<String> scoreList) {
        this.scoreList = scoreList;
        this.layoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public String getItem(int i) {
        return scoreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = layoutInflater.inflate(R.layout.row_score, null);
        TextView tvName = itemView.findViewById(R.id.tv_score);
        tvName.setText(scoreList.get(i));
        return itemView;
    }
}
