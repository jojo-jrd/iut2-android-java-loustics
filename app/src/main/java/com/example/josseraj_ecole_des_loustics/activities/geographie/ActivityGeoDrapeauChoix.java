package com.example.josseraj_ecole_des_loustics.activities.geographie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.josseraj_ecole_des_loustics.R;

public class ActivityGeoDrapeauChoix extends AppCompatActivity {

    // Création de l'activité de choix du mode drapeau/pays
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_drapeau_choix);
    }

    // Affiche l'activité drapeau lors de la validation de l'utilisateur
    public void afficherDrapeaux(View view) {
        Intent i = new Intent(this,ActivityGeoDrapeauAffichage.class);
        startActivity(i);
    }
}