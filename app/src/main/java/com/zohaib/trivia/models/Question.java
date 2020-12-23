package com.zohaib.trivia.models;

import com.google.gson.annotations.SerializedName;
import com.zohaib.trivia.constants.Category;
import com.zohaib.trivia.constants.Difficulty;
import com.zohaib.trivia.constants.QuestionType;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public long _id;

    @Ignore
    @SerializedName("category")
    public Category category;

    @Ignore
    @SerializedName("type")
    public QuestionType questionType;

    @Ignore
    @SerializedName("difficulty")
    public Difficulty difficulty;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "correct_answer")
    public String correct_answer;

    @Ignore
    @ColumnInfo(name = "incorrect_answers")
    public List<String> incorrect_answers;

    public Question() {
    }

    public Question(Category category, QuestionType questionType, Difficulty difficulty, String question, String correct_answer, List<String> incorrect_answers) {
        this.category = category;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
}
