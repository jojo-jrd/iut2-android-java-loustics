package com.example.josseraj_ecole_des_loustics.activities.francais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.texte_a_trou.TexteATrou;
import com.example.josseraj_ecole_des_loustics.classes.selections.SelectionTexteATrou;

import java.util.ArrayList;
import java.util.List;

public class ActivityFrancaisGrammaireAffichageMoyen extends AppCompatActivity {

    // Attributs de l'activité
    private DatabaseLoustics db;
    private SelectionTexteATrou textes;
    private int erreurs;
    private int nombreReponse;

    // Création de l'activité grammaire moyen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des attributs
        erreurs = 0;
        nombreReponse = 0;
        db = DatabaseLoustics.getInstance(getApplicationContext());

        setContentView(R.layout.activity_francais_grammaire_affichage_moyen);

        // Récupération des textes à trou de la BDD et création de l'objet pour la sélection des textes à trou
        getGrammaire();
    }

    // Méthode asynchrone qui récupère des textes à trou de la BDD,
    // créée l'objet pour la sélection des textes à trou et
    // initialise l'affichage
    private void getGrammaire() {

        class GetGrammaire extends AsyncTask<Void, Void, List<TexteATrou>> {

            @Override
            protected List<TexteATrou> doInBackground(Void... voids) {
                // Récupération des textes à trou
                List<TexteATrou> ListTextes = db.getAppDatabase()
                        .texteATrouDao()
                        .getTexteATrouTheme("Français - Grammaire");
                return ListTextes;
            }

            @Override
            protected void onPostExecute(List<TexteATrou> cAll) {
                super.onPostExecute(cAll);

                // Création de l'objet pour choisir des textes à trou aléatoirement
                textes = new SelectionTexteATrou((ArrayList<TexteATrou>) cAll);

                // Initialisation de l'affichage
                TextView t1 = findViewById(R.id.texte1Grammaire);
                t1.setText(textes.getTexteATrouCourant().getPartiePhrase1());

                TextView t2 = findViewById(R.id.texte2Grammaire);
                t2.setText(textes.getTexteATrouCourant().getPartiePhrase2());

                nombreReponse++;

                // Si plus d'une réponse attendue
                if(!textes.getResultat2Courant().equals("NULL")){
                    TextView t3 = findViewById(R.id.texte3Grammaire);
                    t3.setText(textes.getTexteATrouCourant().getPartiePhrase3());
                    t3.setVisibility(View.VISIBLE);

                    EditText e2 = findViewById(R.id.reponse2Grammaire);
                    e2.setHint(".....");
                    e2.setVisibility(View.VISIBLE);
                    nombreReponse++;
                }


            }
        }

        GetGrammaire gg = new GetGrammaire();
        gg.execute();
    }

    // Méthode appelée lors de la validation de son choix par l'utilisateur
    // Elle met à jour l'affichage et gére les erreurs
    public void validationGrammaire(View view) {

        // Récupération des éléments de l'affichage
        TextView t1 = findViewById(R.id.texte1Grammaire);
        TextView t2 = findViewById(R.id.texte2Grammaire);
        TextView t3 = findViewById(R.id.texte3Grammaire);
        EditText e1 = findViewById(R.id.reponse1Grammaire);
        EditText e2 = findViewById(R.id.reponse2Grammaire);

        // Si pas de réponse dans un des EditText
        if (e1.getText().toString().isEmpty() || (!textes.getResultat2Courant().equals("NULL") && e2.getText().toString().isEmpty())){
            Toast.makeText(this,"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();
            e1.setHintTextColor(Color.RED);
            e2.setHintTextColor(Color.RED);
        }else{ // Textes saisis OK
            e1.setHintTextColor(Color.GRAY);
            e2.setHintTextColor(Color.GRAY);
            // Si une erreur sur le 1er EditText
            if(!(textes.getResultat1Courant().toLowerCase()).equals(e1.getText().toString().toLowerCase().replaceAll("\\s+",""))){
                erreurs++;
                Toast.makeText(this,"Mauvaise réponse 1 : la bonne réponse était : "+textes.getResultat1Courant(),Toast.LENGTH_SHORT).show();
            }
            // Si une erreur sur le 2e EditText
            if(!textes.getResultat2Courant().equals("NULL") && !(textes.getResultat2Courant().toLowerCase()).equals(e2.getText().toString().toLowerCase().replaceAll("\\s+",""))){
                erreurs++;
                Toast.makeText(this,"Mauvaise réponse 2 : la bonne réponse était : "+textes.getResultat2Courant(),Toast.LENGTH_SHORT).show();
            }
            textes.indiceSuivant(); // Passage au texte suivant
            if(textes.finListe()){ // Si plus de texte

                // Envoie des données à l'activité de résultat
                Intent resultat = new Intent(this, ActivityFrancaisResultat.class);
                resultat.putExtra("theme","Français");
                resultat.putExtra("sous_theme","Grammaire - Moyen");
                resultat.putExtra("erreurs",erreurs);
                resultat.putExtra("note",String.valueOf(nombreReponse-erreurs)+"/"+String.valueOf(nombreReponse));

                startActivity(resultat); // Lancement de l'activité résultat
                finish(); // On enlève de la pile l'activité courante
            }else{
                // Reinitialisation de l'interface
                e1.setText("");
                e2.setText("");
                t1.setText(textes.getTexteATrouCourant().getPartiePhrase1());
                t2.setText(textes.getTexteATrouCourant().getPartiePhrase2());

                if(!textes.getResultat2Courant().equals("NULL")){ //  Si présence de 2 réponses
                    e2.setHint(".....");
                    e2.setVisibility(View.VISIBLE);
                    t3.setVisibility(View.VISIBLE);
                    nombreReponse +=2;

                    // Affichage des nouveaux textes
                    t3.setText(textes.getTexteATrouCourant().getPartiePhrase3());

                }else{ // Présence d'une seule réponse
                    t3.setVisibility(View.INVISIBLE);
                    t3.setText("");
                    e2.setHint("");
                    e2.setText("");
                    e2.setVisibility(View.INVISIBLE);
                    nombreReponse++;

                }
            }
        }
    }
}