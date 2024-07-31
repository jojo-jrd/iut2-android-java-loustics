package com.example.josseraj_ecole_des_loustics.bd.texte_a_trou;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.josseraj_ecole_des_loustics.bd.question.Question;

import java.util.List;

@Dao
public interface TexteATrouDao {

    @Query("SELECT * FROM texte_a_trou")
    List<TexteATrou> getAll();

    @Query("SELECT * FROM texte_a_trou where theme=:t")
    List<TexteATrou> getTexteATrouTheme(String t);

    @Insert
    long insert(TexteATrou t);

    @Insert
    long[] insertAll(TexteATrou... t);

    @Delete
    void delete(TexteATrou t);

    @Update
    void update(TexteATrou t);
}
