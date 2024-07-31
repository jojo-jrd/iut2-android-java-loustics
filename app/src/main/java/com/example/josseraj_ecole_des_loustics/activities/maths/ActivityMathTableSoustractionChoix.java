package com.example.josseraj_ecole_des_loustics.activities.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.maths.ActivityMathTableSoustractionAffichage;

public class ActivityMathTableSoustractionChoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table_soustraction_choix);
    }


    public void afficherTable(View view) {
        Intent tableS = new Intent(this, ActivityMathTableSoustractionAffichage.class);

        // Récupération des boutons
        RadioButton facile = findViewById(R.id.facileSoustraction);
        RadioButton moyen = findViewById(R.id.moyenSoustraction);

        int difficulteChoisie;

        // Récupération de la difficulté choisie par l'utilisateur
        if(facile.isChecked()){
            difficulteChoisie = 1;
        }else if(moyen.isChecked()){
            difficulteChoisie = 2;
        }else{
            difficulteChoisie = 3;
        }

        // Envoie de la difficulté à l'activité d'affichage
        tableS.putExtra("difficulteChoisie",difficulteChoisie);

        // Lancement de l'activité
        startActivity(tableS);
    }
}