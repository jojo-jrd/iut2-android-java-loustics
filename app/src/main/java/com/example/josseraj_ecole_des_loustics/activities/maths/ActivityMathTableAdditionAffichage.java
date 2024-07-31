package com.example.josseraj_ecole_des_loustics.activities.maths;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.classes.operations.Addition;
import com.example.josseraj_ecole_des_loustics.classes.tables.TableAddition;


import java.util.ArrayList;

public class ActivityMathTableAdditionAffichage extends AppCompatActivity {

    // Attributs de l'activité
    private TableAddition tableAddition;
    private int difficulté; // 1 = facile ; 2 = moyen ; 3 = difficile
    public final static int REQUEST_RETOUR1 = 0;
    private int erreurs;


    // Attributs qui permettent la correction
    private ArrayList<Addition> mauvaisesAdditions;
    private ArrayList<Integer> mauvaisesReponses;
    private int indiceAddition;
    private float erreurs_corrigee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table_addition_affichage);

        this.difficulté = getIntent().getIntExtra("difficulteChoisie",0);

        // Initialisation des attributs de l'activité
        tableAddition = new TableAddition(difficulté);
        erreurs = 0;
        mauvaisesAdditions = new ArrayList<>();
        mauvaisesReponses = new ArrayList<>();
        indiceAddition = 0;

        // Initialisation de l'affichage
        TextView addition = findViewById(R.id.calculAddition);
        addition.setText(tableAddition.getAdditionCourante().getOperande1()+" + "+tableAddition.getAdditionCourante().getOperande2()+ " = ");



    }

    public void afficherResult(View view) {
        // Récupération des objets d'affichage
        TextView addition = findViewById(R.id.calculAddition);
        EditText resultat = findViewById(R.id.ResultatAddition);

        resultat.setHintTextColor(Color.GRAY);

        if(resultat.getText().toString().isEmpty()){ // Si y a pas de résultat
            Toast.makeText(this,"Veuillez remplir un résultat avant de valider",Toast.LENGTH_LONG).show();
            resultat.setHintTextColor(Color.RED);
        }else{

            int reponse = Integer.valueOf(resultat.getText().toString());
            int resultatCourant = tableAddition.getResultatCourant();
            // Si mauvaise réponse
            if(reponse != resultatCourant){
                mauvaisesAdditions.add(tableAddition.getAdditionCourante());
                mauvaisesReponses.add(reponse);
                erreurs++;
            }
            tableAddition.indiceSuivant(); // Passage à l'addition suivante
            if(tableAddition.finListe()){ // Si il n'y a plus d'addition
                // Envoie à l'activité echec/réussite, ses résultats et ses données pour l'enregistrer
                Intent intent;
                if(erreurs!=0) { // Si aucune erreur
                    intent = new Intent(this, ActivityMathTableEchec.class);
                    intent.putExtra("erreurs",erreurs);
                }else {
                    intent = new Intent(this, ActivityMathTableReussite.class);
                }
                intent.putExtra("theme", "Mathématiques");

                if(difficulté==1){
                    intent.putExtra("sous_theme","Addition - facile");
                    intent.putExtra("note",String.valueOf(5-erreurs)+"/5");
                }else if(difficulté==2){
                    intent.putExtra("sous_theme","Addition - moyen");
                    intent.putExtra("note",String.valueOf(7-erreurs)+"/7");
                }else{
                    intent.putExtra("sous_theme","Addition - difficile");
                    intent.putExtra("note", String.valueOf(10-erreurs)+"/10");
                }

                // Lancemenent de l'activité
                if(erreurs!=0) {
                    startActivityForResult(intent,REQUEST_RETOUR1);
                }else {
                    startActivity(intent);
                    finish();
                }

            }else{
                // Mise à jour de l'affichage avec la nouvelle addition
                addition.setText(tableAddition.getAdditionCourante().getOperande1()+" + "+tableAddition.getAdditionCourante().getOperande2()+" = ");
                resultat.setText("");
            }
        }
    }

    // Méthode qui gère et lance la possibilité de modifier les additions où l'utilisateur a fait une erreur
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_RETOUR1 && resultCode==RESULT_OK) {
            TextView addition = findViewById(R.id.calculAddition);
            EditText resultat = findViewById(R.id.ResultatAddition);
            Button valider = findViewById(R.id.validerAddition);
            erreurs_corrigee = 0;

            // Change l'action onClick du bouton valider pour changer les résultats faux
            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifierReponse();
                }
            });

            // Initialisation de l'affichage
            resultat.setText(String.valueOf(mauvaisesReponses.get(indiceAddition)));
            resultat.setSelection(String.valueOf(mauvaisesReponses.get(indiceAddition)).length()); // Change le curseur de place
            resultat.setTextColor(Color.RED);
            addition.setText(mauvaisesAdditions.get(indiceAddition).getOperande1()+" + "+mauvaisesAdditions.get(indiceAddition).getOperande2()+" = ");

        }else{
            finish();
        }


    }

    // Méthode appellé à chaque réponse modifiée
    private void modifierReponse() {
        TextView addition = findViewById(R.id.calculAddition);
        EditText resultat = findViewById(R.id.ResultatAddition);

        int resultatCourant2 = mauvaisesAdditions.get(indiceAddition).getResultat();
        int reponse2 = Integer.valueOf(resultat.getText().toString());
        if(reponse2!=resultatCourant2){
            Toast.makeText(this,"Mauvaise réponse, la réponse était : "+String.valueOf(resultatCourant2),Toast.LENGTH_SHORT).show();
        }else{
            erreurs_corrigee++;
        }
        indiceAddition++; // Passage à l'addition suivante
        if(mauvaisesAdditions.size()==indiceAddition){
            // Envoie à l'activité echec/réussite, ses résultats et ses données pour l'enregistrer
            Intent intent;
            if(erreurs- (int)erreurs_corrigee==0) {
                intent = new Intent(this, ActivityMathTableReussite.class);
            }else{
                intent = new Intent(this, ActivityMathTableEchecCorrection.class);
                intent.putExtra("erreurs",erreurs- (int)erreurs_corrigee);
            }
            intent.putExtra("theme", "Mathématiques");
            if (difficulté == 1) {
                intent.putExtra("sous_theme", "Addition - facile (avec correction)");
                intent.putExtra("note", String.valueOf(5-erreurs + erreurs_corrigee/2) +"/5");
            } else if (difficulté == 2) {
                intent.putExtra("sous_theme", "Addition - moyen (avec correction)");
                intent.putExtra("note", String.valueOf(7-erreurs + erreurs_corrigee/2)+"/7");
            } else {
                intent.putExtra("sous_theme", "Addition - difficile (avec correction)");
                intent.putExtra("note", String.valueOf(10-erreurs + erreurs_corrigee/2)+"/10");
            }

            // Lancement de l'activité
            startActivity(intent);
            finish();

        }else{
            // Mise à jour de l'affichage
            resultat.setText(String.valueOf(mauvaisesReponses.get(indiceAddition)));
            resultat.setSelection(String.valueOf(mauvaisesReponses.get(indiceAddition)).length());
            addition.setText(mauvaisesAdditions.get(indiceAddition).getOperande1()+" + "+mauvaisesAdditions.get(indiceAddition).getOperande2()+" = ");
        }
    }
}