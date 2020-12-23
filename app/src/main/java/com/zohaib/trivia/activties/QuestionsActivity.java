package com.zohaib.trivia.activties;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.zohaib.trivia.R;
import com.zohaib.trivia.adapters.AnswerAdapter;
import com.zohaib.trivia.callbacks.AnswerCallback;
import com.zohaib.trivia.constants.Category;
import com.zohaib.trivia.constants.Difficulty;
import com.zohaib.trivia.helper.IntentHelper;
import com.zohaib.trivia.helper.Utils;
import com.zohaib.trivia.models.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionsActivity extends AppCompatActivity implements AnswerCallback {

    private TextView tvQuestion ,  tvCategoryName , tvTimer , tvLives;
    private RecyclerView answerListView;
    private List<Question> questionList;
    private int nextQuestionIndex = 0;
    private Question currentQuestion;
    private List<String> answerList = new ArrayList<>();
    private AnswerAdapter answerAdapter;

    private boolean isQuickMode = false;
    private int remainingLives = 3;
    private CountDownTimer currentTimer;

    private HashMap<Category , Integer> scores = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_mode_quiz);

        isQuickMode = getIntent().getBooleanExtra("KEY_QUICK_MODE" , false);
        questionList = (List<Question>) IntentHelper.getInstance().getObject("questionsList");

        tvTimer = findViewById(R.id.tv_timer);
        tvLives = findViewById(R.id.tv_lifes);

        if (isQuickMode){
            tvTimer.setVisibility(View.VISIBLE);
            tvLives.setVisibility(View.VISIBLE);
            tvLives.setText("Remaining Lives : " + remainingLives);
        }

        tvQuestion = findViewById(R.id.tv_question);
        tvCategoryName = findViewById(R.id.tv_ct_name);
        answerListView = findViewById(R.id.rv_answers);

        answerListView.setLayoutManager(new LinearLayoutManager(this));
        answerAdapter = new AnswerAdapter(answerList , this::onAnswerClicked);
        answerListView.setAdapter(answerAdapter);

        askQuestion();
    }

    private void askQuestion(){
        if (nextQuestionIndex < questionList.size()){
            currentQuestion = questionList.get(nextQuestionIndex++);
            updateUI();
        }
        else
        {
            showScores();
        }

    }

    private void updateUI() {
        tvQuestion.setText(currentQuestion.question);
        tvCategoryName.setText(currentQuestion.category.displayName());
        answerList.clear();
        answerList.add(currentQuestion.correct_answer);
        for (String ans : currentQuestion.incorrect_answers){
            answerList.add(ans);
        }
        Collections.shuffle(answerList);
        answerAdapter.notifyDataSetChanged();

        if (isQuickMode){
            tvTimer.setText("Remaining Time : " + 1000 + " sec");
            currentTimer = new CountDownTimer(5000 , 1000) {
                @Override
                public void onTick(long milies) {
                    tvTimer.setText("Remaining Time : " + milies/1000 + " sec");
                }

                @Override
                public void onFinish() {
                    checkLive();
                }
            }.start();
        }
    }

    private void checkLive(){
        if (remainingLives == 0 ){
            showScores();
        }
        else{
            remainingLives--;
            tvLives.setText("Remaining Lives : " + remainingLives);
            askQuestion();
        }
    }

    @Override
    public void onAnswerClicked(String answer) {

        boolean isCorrect = currentQuestion.correct_answer.equals(answer);

        Utils.showToast(this , "Answer is : " + isCorrect);

        if (isQuickMode && !isCorrect){
            checkLive();
        }
        else
        {
            calculateScore();
            askQuestion();
        }

        if (currentTimer != null){
            currentTimer.cancel();
            currentTimer = null;
        }
    }

    private void calculateScore(){
        int categoryScore = 0;
        if(scores.containsKey(currentQuestion.category)){
            categoryScore = scores.get(currentQuestion.category);
        }
        switch (currentQuestion.difficulty){
            case Easy:{
                scores.put(currentQuestion.category , categoryScore + 1);
                break;
            }
            case Medium:{
                scores.put(currentQuestion.category , categoryScore + 2);
                break;
            }
            case Hard:{
                scores.put(currentQuestion.category , categoryScore + 3);
                break;
            }
        }
    }

    private void showScores(){
        IntentHelper.getInstance().insertObject("SCORES" , scores);
        startActivity(new Intent(this , ScoreActivity.class));
        finish();
    }
}