package com.example.josseraj_ecole_des_loustics.activities.maths;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.classes.operations.Soustraction;
import com.example.josseraj_ecole_des_loustics.classes.tables.TableSoustraction;

import java.util.ArrayList;

public class ActivityMathTableSoustractionAffichage extends AppCompatActivity {

    private TableSoustraction tableSoustraction;
    private int difficulté; // 1 = facile ; 2 = moyen ; 3 = difficile
    public final static int REQUEST_RETOUR_SOUSTRACTION = 1;
    private int erreurs;

    // Attributs qui permettent la correction
    private ArrayList<Soustraction> mauvaisesSoustractions;
    private ArrayList<Integer> mauvaisesReponses;
    private int indiceSoustraction;
    private float erreurs_corrigee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_table_soustraction_affichage);

         // Récupération et sélection des données
        this.difficulté = getIntent().getIntExtra("difficulteChoisie",0);
        tableSoustraction = new TableSoustraction(difficulté);
        erreurs = 0;
        mauvaisesSoustractions = new ArrayList<>();
        mauvaisesReponses = new ArrayList<>();
        indiceSoustraction = 0;

        // Initialisation de l'affichage
        TextView soustraction = findViewById(R.id.calculSoustraction);
        soustraction.setText(tableSoustraction.getSoustractionCourante().getOperande1()+" - "+tableSoustraction.getSoustractionCourante().getOperande2()+ " = ");
    }

    public void afficherResult(View view) {

        // Récupération des objets d'affichage
        TextView soustraction = findViewById(R.id.calculSoustraction);
        EditText resultat = findViewById(R.id.ResultatSoustraction);

        resultat.setHintTextColor(Color.GRAY);

        if(resultat.getText().toString().isEmpty()){ // Si pas de réponse
            Toast.makeText(this,"Veuillez remplir un résultat avant de valider",Toast.LENGTH_LONG).show();
            resultat.setHintTextColor(Color.RED);
        }else{
            int reponse = Integer.valueOf(resultat.getText().toString());
            int resultatCourant = tableSoustraction.getResultatCourant();
            // Si mauvaise réponse
            if(reponse!=resultatCourant){
                mauvaisesSoustractions.add(tableSoustraction.getSoustractionCourante());
                mauvaisesReponses.add(reponse);
                erreurs++;
            }
            tableSoustraction.indiceSuivant(); // Passage à la soustraction suivante
            if(tableSoustraction.finListe()){ // Si fin des soustractions
                Intent intent;
                // Envoie des données à l'activité résultat
                if(erreurs!=0) { // Si pas d'erreur
                    intent = new Intent(this, ActivityMathTableEchec.class);
                    intent.putExtra("erreurs",erreurs);
                }else {
                    intent = new Intent(this, ActivityMathTableReussite.class);
                }

                intent.putExtra("theme", "Mathématiques");
                if(difficulté==1){
                    intent.putExtra("sous_theme","Soustraction - facile");
                    intent.putExtra("note",String.valueOf(5-erreurs)+"/5");
                }else if(difficulté==2){
                    intent.putExtra("sous_theme","Soustraction - moyen");
                    intent.putExtra("note",String.valueOf(7-erreurs)+"/7");
                }else{
                    intent.putExtra("sous_theme","Soustraction - difficile");
                    intent.putExtra("note", String.valueOf(10-erreurs)+"/10");
                }

                // Affichage de l'activité
                if(erreurs!=0) {
                    startActivityForResult(intent,REQUEST_RETOUR_SOUSTRACTION);
                }else {
                    startActivity(intent);
                    finish();
                }
            }else{
                soustraction.setText(tableSoustraction.getSoustractionCourante().getOperande1()+" - "+tableSoustraction.getSoustractionCourante().getOperande2()+" = ");
                resultat.setText("");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_RETOUR_SOUSTRACTION && resultCode==RESULT_OK) {

            // Récupération des objets d'affichage
            TextView soustraction = findViewById(R.id.calculSoustraction);
            EditText resultat = findViewById(R.id.ResultatSoustraction);
            Button valider = findViewById(R.id.validerSoustraction);

            erreurs_corrigee = 0;

            // changement de l'événement onclick du bouton valider
            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifierReponse();
                }
            });

            // Initialisation de l'affichage
            resultat.setTextColor(Color.RED);
            resultat.setText(String.valueOf(mauvaisesReponses.get(indiceSoustraction)));
            resultat.setSelection(String.valueOf(mauvaisesReponses.get(indiceSoustraction)).length());
            soustraction.setText(mauvaisesSoustractions.get(indiceSoustraction).getOperande1()+" - "+mauvaisesSoustractions.get(indiceSoustraction).getOperande2()+" = ");
        }else{
            finish();
        }


    }

    private void modifierReponse() {
        // Récupération des objets d'affichage
        TextView soustraction = findViewById(R.id.calculSoustraction);
        EditText resultat = findViewById(R.id.ResultatSoustraction);

        int reponse = Integer.valueOf(resultat.getText().toString());
        int resultatCourant = mauvaisesSoustractions.get(indiceSoustraction).getResultat();
        // Si mauvaise réponse
        if(reponse!=resultatCourant){
            Toast.makeText(this,"Mauvaise réponse, la réponse était : "+String.valueOf(resultatCourant),Toast.LENGTH_SHORT).show();
        }else{
            erreurs_corrigee++;
        }
        indiceSoustraction++;
        if(mauvaisesSoustractions.size()==indiceSoustraction){ // Si plus de soustraction à corriger

            // Envoie à l'activité echec/réussite, ses résultats et ses données pour l'enregistrer
            Intent intent;

            // Affichage de l'activité en fonction des erreurs
            if(erreurs- (int)erreurs_corrigee==0) {
                intent = new Intent(this, ActivityMathTableReussite.class);
            }else{
                intent = new Intent(this, ActivityMathTableEchecCorrection.class);
                intent.putExtra("erreurs",erreurs- (int)erreurs_corrigee);
            }
            intent.putExtra("theme", "Mathématiques");
            if (difficulté == 1) {
                intent.putExtra("sous_theme", "Soustraction - facile (avec correction)");
                intent.putExtra("note", String.valueOf(5-erreurs + erreurs_corrigee/2) +"/5");
            } else if (difficulté == 2) {
                intent.putExtra("sous_theme", "Soustraction - moyen (avec correction)");
                intent.putExtra("note", String.valueOf(7-erreurs + erreurs_corrigee/2)+"/7");
            } else {
                intent.putExtra("sous_theme", "Soustraction - difficile (avec correction)");
                intent.putExtra("note", String.valueOf(10-erreurs + erreurs_corrigee/2)+"/10");
            }

            // Lancement de l'activité résultat
            startActivity(intent);
            finish();



        }else{
            // Mise à jour de l'affichage
            resultat.setText(String.valueOf(mauvaisesReponses.get(indiceSoustraction)));
            resultat.setSelection(String.valueOf(mauvaisesReponses.get(indiceSoustraction)).length());
            soustraction.setText(mauvaisesSoustractions.get(indiceSoustraction).getOperande1()+" - "+mauvaisesSoustractions.get(indiceSoustraction).getOperande2()+" = ");
        }
    }

}