package com.example.josseraj_ecole_des_loustics.activities.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.maths.ActivityMathTableAdditionChoix;
import com.example.josseraj_ecole_des_loustics.activities.maths.ActivityMathTableMultiplicationChoix;
import com.example.josseraj_ecole_des_loustics.activities.maths.ActivityMathTableSoustractionChoix;

public class ActivityMenuMaths extends AppCompatActivity {

    // Création de l'activité du menu de Mathématique
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maths);
    }

    // Affiche l'activité de choix pour les multiplications
    public void afficherMultiplication(View view) {
        Intent multi = new Intent(this, ActivityMathTableMultiplicationChoix.class);
        startActivity(multi);
    }

    // Affiche l'activité de choix pour les additions
    public void afficherAddition(View view) {
        Intent addi = new Intent(this, ActivityMathTableAdditionChoix.class);
        startActivity(addi);
    }

    // Affiche l'activité de choix pour les soustractions
    public void afficherSoustraction(View view) {
        Intent soust = new Intent(this, ActivityMathTableSoustractionChoix.class);
        startActivity(soust);
    }
}