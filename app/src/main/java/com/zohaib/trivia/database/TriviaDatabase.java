package com.zohaib.trivia.database;

import com.zohaib.trivia.models.Question;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 1)
public abstract class TriviaDatabase extends RoomDatabase {
    public abstract TriviaDao taskDao();

}
