package com.zohaib.trivia.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zohaib.trivia.R;
import com.zohaib.trivia.adapters.CategoryAdapter;
import com.zohaib.trivia.constants.Category;
import com.zohaib.trivia.constants.Selection;
import com.zohaib.trivia.helper.Utils;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private ListView listViewCategory;
    private List<String> categoriesList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        for (Category category : Category.values()) {
            categoriesList.add(category.displayName());
        }

        listViewCategory = findViewById(R.id.lv_category);
        categoryAdapter = new CategoryAdapter(getApplicationContext() , categoriesList );
        listViewCategory.setAdapter(categoryAdapter);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Selection.category = Category.fromDisplayName(categoriesList.get(i));
                Utils.showToast(getBaseContext() ,"Selected : " + Selection.category.displayName());
                startActivity(new Intent(getBaseContext() , OptionActivity.class));
            }
        });
    }
}