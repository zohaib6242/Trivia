package com.zohaib.trivia.activties;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zohaib.trivia.R;
import com.zohaib.trivia.api.Api;
import com.zohaib.trivia.api.NetworkConstants;
import com.zohaib.trivia.helper.IntentHelper;
import com.zohaib.trivia.helper.Utils;
import com.zohaib.trivia.models.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        findViewById(R.id.btn_start_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext() , CategoryActivity.class));
            }
        });

        findViewById(R.id.btn_quick_mode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchQuestions();
            }
        });
    }

    private void fetchQuestions() {

        Utils.showProgress(this);
        Api.getClient().questionList(NetworkConstants.ITEM_THRESHOLD_QUICK_MODE).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Utils.hideProgress();
                if(response.body().questionList != null && response.body().questionList.size() > 0) {
                    IntentHelper.getInstance().insertObject("questionsList", response.body().questionList);

                    Intent intent = new Intent(getBaseContext(), QuestionsActivity.class);
                    intent.putExtra("KEY_QUICK_MODE" , true);
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
        });
    }


}