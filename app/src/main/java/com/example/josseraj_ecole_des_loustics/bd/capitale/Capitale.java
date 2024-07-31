package com.example.josseraj_ecole_des_loustics.bd.capitale;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "capitale")
public class Capitale {

    @PrimaryKey @NonNull
    private String pays;

    private String capitale;

    //=====================
    // GETTERS
    //=====================

    public String getCapitale() { return capitale;}

    public String getPays() { return pays;}

    //=====================
    // SETTERS
    //=====================

    public void setCapitale(String capitale) { this.capitale = capitale;}

    public void setPays(String pays) { this.pays = pays;}
}
