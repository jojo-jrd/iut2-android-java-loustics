package com.example.josseraj_ecole_des_loustics.activities.geographie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.geographie.ActivityGeoCapitaleAffichage;

public class ActivityGeoCapitaleChoix extends AppCompatActivity {

    // Création de l'activité de choix du mode capitale/pays
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_capitale_choix);
    }

    // Affiche l'activité en fonction du mode choisi par l'utilisateur
    public void afficherCapAffichage(View view) {
        RadioButton b = findViewById(R.id.rechercheCapitale);
        Intent affichageCapitale = new Intent(this, ActivityGeoCapitaleAffichage.class);
        if(b.isChecked()){
            affichageCapitale.putExtra("mode","rC"); // rC = rechercheCapitale
        }else{
            affichageCapitale.putExtra("mode","rP"); // rP = recherchePays
        }
        startActivity(affichageCapitale);
    }
}