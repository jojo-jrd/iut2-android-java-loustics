package com.example.josseraj_ecole_des_loustics.activities.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.geographie.ActivityGeoCapitaleChoix;
import com.example.josseraj_ecole_des_loustics.activities.geographie.ActivityGeoDrapeauChoix;

public class ActivityMenuGeographie extends AppCompatActivity {

    // Création de l'activité du menu de géographie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_geographie);
    }

    // Affiche l'activité de choix pour les capitales
    public void afficherCapitale(View view) {
        Intent cap = new Intent(this, ActivityGeoCapitaleChoix.class);
        startActivity(cap);
    }

    // Affiche l'activité de choix pour les drapeaux
    public void afficherDrapeau(View view) {
        Intent drap = new Intent(this, ActivityGeoDrapeauChoix.class);
        startActivity(drap);
    }
}