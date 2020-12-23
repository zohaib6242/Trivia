package com.zohaib.trivia.database;

import com.zohaib.trivia.models.Question;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TriviaDao {
    @Insert
    void insertAll(Question... question);
}
