package com.zohaib.trivia.activties;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zohaib.trivia.R;
import com.zohaib.trivia.api.Api;
import com.zohaib.trivia.api.NetworkConstants;
import com.zohaib.trivia.constants.Category;
import com.zohaib.trivia.constants.Difficulty;
import com.zohaib.trivia.constants.QuestionType;
import com.zohaib.trivia.constants.Selection;
import com.zohaib.trivia.helper.IntentHelper;
import com.zohaib.trivia.helper.Utils;
import com.zohaib.trivia.models.Response;

import java.util.ArrayList;
import java.util.List;

public class OptionActivity extends AppCompatActivity {

    private Spinner spDifficultyLevel , spQuestionType;
    private List<String> difficultyLevels , questionTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        spDifficultyLevel = findViewById(R.id.sp_difficulty_level);
        spQuestionType = findViewById(R.id.sp_question_type);

        initSpinnerData();

        findViewById(R.id.load_questions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showToast(getBaseContext() , "Selected Difficulty Level: " + Selection.difficulty.displayName() +
                        "\n" + "Selected Question Type: " + Selection.questionType.displayName());
                fetchQuestions();
            }
        });
    }

    private void fetchQuestions() {

        Utils.showProgress(this);
        int categoryId = Category.getId(Selection.category);
        String difficulty = Selection.difficulty.displayName();
        String questionType = Selection.questionType.displayName();

        Callback<Response> callable = new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Utils.hideProgress();
                if(response.body().questionList != null && response.body().questionList.size() > 0) {
                    IntentHelper.getInstance().insertObject("questionsList", response.body().questionList);
                    Intent intent = new Intent(getBaseContext(), QuestionsActivity.class);
                    intent.putExtra("KEY_QUICK_MODE" , false);
                    startActivity(intent);
                }
                else {
                    Utils.showToast(getBaseContext() , "Selected Section Un-available");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Utils.hideProgress();
                Toast.makeText(getBaseContext() ,
                        t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        };

        if (Selection.category == Category.Any){
            Api.getClient().questionListByAnyCategory(NetworkConstants.ITEM_THRESHOLD, difficulty,questionType).enqueue(callable);
        }else {
            Api.getClient().questionListByCategory(NetworkConstants.ITEM_THRESHOLD,categoryId , difficulty,questionType).enqueue(callable);
        }
    }

    private void initSpinnerData() {
        difficultyLevels = new ArrayList<>();
        for (Difficulty difficulty : Difficulty.values()){
            difficultyLevels.add(difficulty.displayName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDifficultyLevel.setAdapter(adapter);

        questionTypes = new ArrayList<>();
        for (QuestionType questionType : QuestionType.values()){
            questionTypes.add(questionType.displayName());
        }
        ArrayAdapter<String> adapterQuestionType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, questionTypes);
        adapterQuestionType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuestionType.setAdapter(adapterQuestionType);

        spDifficultyLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selection.difficulty = Difficulty.fromDisplayName(difficultyLevels.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spQuestionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selection.questionType = QuestionType.fromDisplayName(questionTypes.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}