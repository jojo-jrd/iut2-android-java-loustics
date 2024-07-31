package com.example.josseraj_ecole_des_loustics.activities.francais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.francais.ActivityFrancaisConjugaisonEtGrammaireFacileAffichage;
import com.example.josseraj_ecole_des_loustics.activities.francais.ActivityFrancaisGrammaireAffichageMoyen;

public class ActivityFrancaisGrammaireChoix extends AppCompatActivity {

    // Création de l'activité grammaire choix
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_francais_grammaire_choix);
    }

    // Affichage de l'activité voulue en fonction du niveau choisi
    public void afficherGrammaire(View view) {
        RadioButton b = findViewById(R.id.facileGrammaire);
        Intent i;
        if(b.isChecked()){
            i = new Intent(this, ActivityFrancaisConjugaisonEtGrammaireFacileAffichage.class);
            i.putExtra("mode","Français - Grammaire");
        }else{
            i = new Intent(this, ActivityFrancaisGrammaireAffichageMoyen.class);
        }
        startActivity(i);
    }
}