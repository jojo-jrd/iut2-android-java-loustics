package com.example.josseraj_ecole_des_loustics.bd.capitale;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.josseraj_ecole_des_loustics.bd.capitale.Capitale;

import java.util.List;

@Dao
public interface CapitaleDao {

    @Query("SELECT * FROM capitale")
    List<Capitale> getAll();

    @Query("SELECT * FROM capitale where capitale=:p")
    Capitale getPays(String p);

    @Query("SELECT * FROM capitale where pays=:p")
    Capitale getCapitale(String p);

    @Insert
    long insert(Capitale capitale);

    @Insert
    long[] insertAll(Capitale... capitales);

    @Delete
    void delete(Capitale capitale);

    @Update
    void update(Capitale capitale);

}
