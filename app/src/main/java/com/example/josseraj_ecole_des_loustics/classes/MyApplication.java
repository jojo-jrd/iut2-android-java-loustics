package com.example.josseraj_ecole_des_loustics.classes;

import android.app.Application;

import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;

public class MyApplication extends Application {

    private Compte compteCourant;

    public Compte getCompteCourant() {
        return compteCourant;
    }

    public void setCompteCourant(Compte compteCourant) {
        this.compteCourant = compteCourant;
    }

}
