package com.example.josseraj_ecole_des_loustics.bd.exercice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;

import java.util.List;

@Dao
public interface ExerciceDao {

    @Query("SELECT * FROM exercice")
    List<Exercice> getAll();

    @Query("SELECT * FROM exercice where pseudo=:p order by date desc")
    List<Exercice> getExerciceFrom(String p);

    @Query("SELECT count(*)>0 FROM exercice where pseudo=:p and theme=:t and sous_theme=:st")
    boolean getExerciceFromTheme(String p, String t, String st);

    @Insert
    long insert(Exercice exercice);

    @Insert
    long[] insertAll(Exercice... exercices);

    @Delete
    void delete(Exercice exercice);

    @Update
    void update(Exercice exercice);
}
