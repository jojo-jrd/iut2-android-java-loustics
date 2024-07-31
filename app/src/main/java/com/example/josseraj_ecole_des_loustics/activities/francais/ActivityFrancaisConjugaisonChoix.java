package com.example.josseraj_ecole_des_loustics.activities.francais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.josseraj_ecole_des_loustics.R;

public class ActivityFrancaisConjugaisonChoix extends AppCompatActivity {

    // Création de l'activité conjugaison choix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_francais_conjugaison_choix);
    }

    // Affichage de la page d'exercice de conjugaison
    public void afficherConjugaison(View view) {
        Intent conjug = new Intent(this, ActivityFrancaisConjugaisonEtGrammaireFacileAffichage.class);
        conjug.putExtra("mode","Français - Conjugaison");
        startActivity(conjug);
    }
}