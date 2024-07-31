package com.example.josseraj_ecole_des_loustics.bd.compte;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;

import java.util.List;

@Dao
public interface CompteDao {

    @Query("SELECT * FROM compte")
    List<Compte> getAll();

    @Query("SELECT * FROM compte where pseudo=:p")
    Compte getCompte(String p);

    @Insert
    long insert(Compte compte);

    @Insert
    long[] insertAll(Compte... comptes);

    @Delete
    void delete(Compte compte);

    @Update
    void update(Compte compte);
}
