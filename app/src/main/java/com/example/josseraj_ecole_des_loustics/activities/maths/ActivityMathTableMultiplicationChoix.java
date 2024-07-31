package com.example.josseraj_ecole_des_loustics.activities.maths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.josseraj_ecole_des_loustics.R;

public class ActivityMathTableMultiplicationChoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table_multiplication_choix);
        NumberPicker numberPicker = findViewById(R.id.table);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
    }

    public void afficherTable(View view) {
        Intent tableM = new Intent(this, ActivityMathTableMultiplicationAffichage.class);

        // Recupération des boutons
        NumberPicker numberPicker = findViewById(R.id.table);
        RadioButton facile = findViewById(R.id.facile);
        RadioButton moyen = findViewById(R.id.moyen);
        RadioButton difficile = findViewById(R.id.difficile);

        int table = numberPicker.getValue();
        int difficulteChoisie;
        // Récupération de la difficulté
        if(facile.isChecked()){
            difficulteChoisie = 1;
        }else if(moyen.isChecked()){
            difficulteChoisie = 2;
        }else if(difficile.isChecked()){
            difficulteChoisie = 3;
        }else{
            difficulteChoisie = 4;
            table = -1;
        }
        // Envoie des données à l'activité d'affichage
        tableM.putExtra("tableVoulue",table);
        tableM.putExtra("difficulteChoisie",difficulteChoisie);

        // lancement de l'activité
        startActivity(tableM);
    }

    // Lorsqu'il y a un clic sur le bouton très difficile
    public void enleverNumberPicker(View view) {

        // On cache le bloc de séléction de la table
        NumberPicker nP = findViewById(R.id.table);
        nP.setVisibility(View.INVISIBLE);
        TextView tV = findViewById(R.id.texteEnnonceMultiplication);
        tV.setVisibility(View.INVISIBLE);
    }

    // Lorsqu'il y a un clic sur tous les boutons sauf  très difficile
    public void afficherNumberPicker(View view) {

        // On affiche le bloc de séléction de la table
        NumberPicker nP = findViewById(R.id.table);
        nP.setVisibility(View.VISIBLE);
        TextView tV = findViewById(R.id.texteEnnonceMultiplication);
        tV.setVisibility(View.VISIBLE);
    }
}