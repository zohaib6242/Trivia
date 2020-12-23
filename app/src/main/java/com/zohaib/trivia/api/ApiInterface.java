package com.zohaib.trivia.api;

import com.zohaib.trivia.models.Question;
import com.zohaib.trivia.models.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php")
    Call<Response> questionListByAnyCategory(@Query("amount") int amount ,@Query("difficulty") String difficulty , @Query("type") String type);

    @GET("api.php")
    Call<Response> questionListByCategory(@Query("amount") int amount ,@Query("category") int categoryId , @Query("difficulty") String difficulty , @Query("type") String type);

    @GET("api.php")
    Call<Response> questionList(@Query("amount") int amount );
}
