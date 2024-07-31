package com.example.josseraj_ecole_des_loustics.activities.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.maths.ActivityMathTableAdditionAffichage;

public class ActivityMathTableAdditionChoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table_addition_choix);
    }

    public void afficherTable(View view) {
        Intent tableA = new Intent(this, ActivityMathTableAdditionAffichage.class);
        // Récupération des radioButton
        RadioButton facile = findViewById(R.id.facileAddition);
        RadioButton moyen = findViewById(R.id.moyenAddition);

        int difficulteChoisie;
        // Récupération de la difficulté choisie
        if(facile.isChecked()){
            difficulteChoisie = 1;
        }else if(moyen.isChecked()){
            difficulteChoisie = 2;
        }else{
            difficulteChoisie = 3;
        }

        // Envoie de la difficulté
        tableA.putExtra("difficulteChoisie",difficulteChoisie);

        // Affichage de l'exercice d'addition
        startActivity(tableA);
    }
}