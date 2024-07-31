package com.example.josseraj_ecole_des_loustics.bd.question;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question where theme=:t")
    List<Question> getQuestionTheme(String t);

    @Insert
    long insert(Question question);

    @Insert
    long[] insertAll(Question... questions);

    @Delete
    void delete(Question question);

    @Update
    void update(Question question);
}
