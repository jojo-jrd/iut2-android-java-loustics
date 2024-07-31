package com.example.josseraj_ecole_des_loustics.bd.drapeau;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.josseraj_ecole_des_loustics.R;
@Entity(tableName = "drapeau")
public class Drapeau {

    @PrimaryKey @NonNull
    private String pays;

    private int drapeau;

    //=====================
    // GETTERS
    //=====================

    public int getDrapeau() { return drapeau;}

    public String getPays() {return pays;}

    //=====================
    // SETTERS
    //=====================

    public void setDrapeau(int drapeau) { this.drapeau = drapeau; }

    public void setPays(String pays) { this.pays = pays;}
}
