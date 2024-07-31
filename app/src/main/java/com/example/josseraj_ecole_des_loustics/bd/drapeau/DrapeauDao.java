package com.example.josseraj_ecole_des_loustics.bd.drapeau;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.josseraj_ecole_des_loustics.bd.drapeau.Drapeau;

import java.util.List;

@Dao
public interface DrapeauDao {

    @Query("SELECT * FROM drapeau")
    List<Drapeau> getAll();

    @Insert
    long insert(Drapeau d);

    @Insert
    long[] insertAll(Drapeau... d);

    @Delete
    void delete(Drapeau d);

    @Update
    void update(Drapeau d);
}
