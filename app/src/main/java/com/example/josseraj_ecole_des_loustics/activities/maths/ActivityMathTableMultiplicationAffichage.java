package com.example.josseraj_ecole_des_loustics.activities.maths;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.classes.operations.Multiplication;
import com.example.josseraj_ecole_des_loustics.classes.tables.TableMultiplication;

import java.util.ArrayList;


public class ActivityMathTableMultiplicationAffichage extends AppCompatActivity {

    private TableMultiplication multi;
    private ArrayList<EditText> resultat;
    private int table;
    private int erreurs;
    private int difficulté; // 1 = facile ; 2 = moyen ; 3 = difficile ; 4 très difficile

    public final static int REQUEST_RETOUR1 = 0;

    // Attributs de l'activité pour la gestion des erreurs
    private float erreurs_corrigee;
    private boolean correction;
    private ArrayList<EditText> multiplicationFausses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des valeurs
        this.table = getIntent().getIntExtra("tableVoulue",-1);
        this.difficulté = getIntent().getIntExtra("difficulteChoisie",0);
        this.erreurs_corrigee = 0;
        this.erreurs = 0;
        this.correction = false;
        this.multiplicationFausses = new ArrayList<>();
        this.resultat = new ArrayList<EditText>();

        // Création des multiplications en fonction de la difficulté et de la table
        multi = new TableMultiplication(table,difficulté);




        setContentView(R.layout.activity_math_table_multiplication_affichage);

        // Récupération du layout principal
        LinearLayout linear = findViewById(R.id.LL);

        // Affichage des multiplications générées aléatoirement
        for (Multiplication m : multi.getMultiplications()){
            LinearLayout l = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul,null);

            TextView cal = l.findViewById(R.id.template_calcul);
            cal.setText(m.getOperande1() + " x "+ m.getOperande2()+" = ");

            EditText res = l.findViewById(R.id.template_resultat);

            resultat.add(res);

            linear.addView(l);
        }



    }

    public void afficherResult(View view) {
        ArrayList<Integer> vraieResultat = multi.getResultats();
        int i = 0;
        boolean impossible = false;
        ArrayList<EditText> textImpossible = new ArrayList<>();

        for(EditText e : resultat){ // Parcourt de toutes les réponses
            e.setHintTextColor(Color.BLACK);
            if(e.getText().toString().isEmpty()){ // Si pas de textes
                impossible = true;
                textImpossible.add(e);
            }else if(!correction && Integer.valueOf(e.getText().toString()) != vraieResultat.get(i)){ // Si une erreur et pas de correction en cours
                erreurs++;
            }else if(correction && Integer.valueOf(e.getText().toString()) == vraieResultat.get(i) && multiplicationFausses.contains(e)){ // Si bonne correction
                erreurs_corrigee += 1;
            }
            i++;
        }
        if(impossible){
            for (EditText e : textImpossible){
                e.setHintTextColor(Color.RED);
            }
            erreurs = 0;
            Toast.makeText(this,"Veuillez remplir tous les champs textes",Toast.LENGTH_LONG).show();

        }else {
            // Envoie vers l'activité en fonction des informations récoltés lors de cette exercice
            Intent intent;
            // Choix de l'activité affiché
            if (erreurs != 0 && correction) { // des erreurs et correction
                intent = new Intent(this, ActivityMathTableEchecCorrection.class);
            } else if (erreurs != 0) { // des erreurs et pas de correction
                intent = new Intent(this, ActivityMathTableEchec.class);
            } else { // pas d'erreurs
                intent = new Intent(this, ActivityMathTableReussite.class);
            }

            intent.putExtra("theme", "Mathématiques");
            // Envoie des données
            if (difficulté == 1) { // FACILE
                if (correction) {
                    intent.putExtra("sous_theme", "Multiplication - facile (avec correction)");
                    intent.putExtra("note", String.valueOf(5 - erreurs + erreurs_corrigee / 2) + "/5");  // Pour un affichage en Float avec les erreurs
                } else {
                    intent.putExtra("sous_theme", "Multiplication - facile");
                    intent.putExtra("note", String.valueOf(5 - erreurs) + "/5"); // Pour un meilleur affichage en INT
                }

            } else if (difficulté == 2) { // MOYEN
                if (correction) {
                    intent.putExtra("sous_theme", "Multiplication - moyen (avec correction)");
                    intent.putExtra("note", String.valueOf(5 - erreurs + erreurs_corrigee / 2) + "/5");
                } else {
                    intent.putExtra("sous_theme", "Multiplication - moyen");
                    intent.putExtra("note", String.valueOf(5 - erreurs) + "/5");
                }
            } else {
                if (difficulté == 3) { // DIFFICILE
                    if (correction) {
                        intent.putExtra("sous_theme", "Multiplication - difficile (avec correction)");
                    } else {
                        intent.putExtra("sous_theme", "Multiplication - difficile");
                    }
                    intent.putExtra("sous_theme", "Multiplication - difficile");
                } else { // TRES DIFFICILE
                    if (correction) {
                        intent.putExtra("sous_theme", "Multiplication - très difficile (avec correction)");
                    } else {
                        intent.putExtra("sous_theme", "Multiplication - très difficile");
                    }
                }

                // COMMUN AUX MODES DIFFICILE ET TRES DIFFICILE
                if(correction){
                    intent.putExtra("note", String.valueOf(10 - erreurs + erreurs_corrigee / 2) + "/10");
                }else{
                    intent.putExtra("note", String.valueOf(10 - erreurs) + "/10");
                }

            }
            if (erreurs != 0) {
                intent.putExtra("erreurs", erreurs- (int)erreurs_corrigee);
                startActivityForResult(intent, REQUEST_RETOUR1);
            } else {
                startActivity(intent);
            }
        }
//
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_RETOUR1 && resultCode==RESULT_OK){
            ArrayList<Integer> vraieResultat = multi.getResultats();
            correction = true;

            int i = 0;
            // Colorie les cases où le résultat est faux
            for(EditText e : resultat){
                if(Integer.valueOf(e.getText().toString()) != vraieResultat.get(i)){
                    e.setTextColor(Color.RED);
                    multiplicationFausses.add(e);
                }else{
                    e.setTextColor(Color.BLACK);
                }
                i++;
            }

        }



    }

}