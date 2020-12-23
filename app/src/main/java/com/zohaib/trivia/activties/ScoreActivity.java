package com.zohaib.trivia.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zohaib.trivia.R;
import com.zohaib.trivia.adapters.ScoreAdapter;
import com.zohaib.trivia.constants.Category;
import com.zohaib.trivia.helper.IntentHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTotalScores;
    private List<String> scoreList = new ArrayList<>();
    private ListView scoreView;
    private HashMap<Category, Integer> scores = new HashMap<>();
    private int totalScores = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        findViewById(R.id.btn_exit).setOnClickListener(this::onClick);
        findViewById(R.id.btn_play_again).setOnClickListener(this::onClick);

        tvTotalScores = findViewById(R.id.tv_total_score);
        scoreView = findViewById(R.id.list_scores);
        scores = (HashMap<Category, Integer>) IntentHelper.getInstance().getObject("SCORES");
        for (Category category : scores.keySet()) {
            totalScores = totalScores + scores.get(category);
            scoreList.add(category.displayName() + " : " + scores.get(category));
        }
        scoreView.setAdapter(new ScoreAdapter(this , scoreList));
        tvTotalScores.setText("TOTAL EARNED SCORES : " + totalScores);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit:{
                finishAffinity();
                break;
            }
            case R.id.btn_play_again:{
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            }
        }
    }
}