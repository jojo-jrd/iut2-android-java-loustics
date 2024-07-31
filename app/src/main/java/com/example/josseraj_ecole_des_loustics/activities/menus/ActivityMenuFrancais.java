package com.example.josseraj_ecole_des_loustics.activities.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.ActivityProfil;
import com.example.josseraj_ecole_des_loustics.activities.francais.ActivityFrancaisConjugaisonChoix;
import com.example.josseraj_ecole_des_loustics.activities.francais.ActivityFrancaisGrammaireChoix;
import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;

import java.util.HashMap;
import java.util.List;

public class ActivityMenuFrancais extends AppCompatActivity {

    // Création de l'activité du menu de francais
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_francais);
    }

    // Affiche l'activité de choix pour la conjugaison
    public void afficherConjugaison(View view) {
        Intent conj = new Intent(this, ActivityFrancaisConjugaisonChoix.class);
        startActivity(conj);
    }

    // Affiche l'activité de choix pour la grammaire
    public void afficherGrammaire(View view) {
        Intent conj = new Intent(this, ActivityFrancaisGrammaireChoix.class);
        startActivity(conj);
    }
}