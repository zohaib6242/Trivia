package com.zohaib.trivia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohaib.trivia.R;
import com.zohaib.trivia.callbacks.AnswerCallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> answerList;
    private AnswerCallback answerCallback;

    public AnswerAdapter(List<String> answerList, AnswerCallback answerCallback) {
        this.answerList = answerList;
        this.answerCallback = answerCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.row_answer, parent, false);
        return new AnswerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {
        AnswerViewHolder answerViewHolder = (AnswerViewHolder) holder;
        int serial = position + 1;
        answerViewHolder.tvSerial.setText(""+serial);
        answerViewHolder.tvAnswer.setText(answerList.get(position));
        answerViewHolder.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerCallback != null){
                    answerCallback.onAnswerClicked(answerList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    private class AnswerViewHolder extends RecyclerView.ViewHolder{

        TextView tvSerial , tvAnswer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSerial = itemView.findViewById(R.id.tv_serial);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
        }
    }
}
