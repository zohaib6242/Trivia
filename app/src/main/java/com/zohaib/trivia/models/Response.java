package com.zohaib.trivia.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    int response_code;

    @SerializedName("results")
    public List<Question> questionList;
}
