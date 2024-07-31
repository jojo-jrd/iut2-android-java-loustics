package com.example.josseraj_ecole_des_loustics.activities.francais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.question.Question;
import com.example.josseraj_ecole_des_loustics.classes.selections.SelectionQuestion;

import java.util.ArrayList;
import java.util.List;

public class ActivityFrancaisConjugaisonEtGrammaireFacileAffichage extends AppCompatActivity {

    // Attributs de l'activité
    private DatabaseLoustics db;
    private SelectionQuestion questions;
    private int erreurs;
    private String mode;

    // Création de l'activité conjugaison et grammaire affichage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des attributs
        db = DatabaseLoustics.getInstance(getApplicationContext());
        erreurs=0;
        mode = getIntent().getStringExtra("mode"); // Français - Grammaire ou Français - Conjugaison

        setContentView(R.layout.activity_francais_conjugaison_et_grammaire_facile_affichage);

        // Récupération des questions de la BDD et création de l'objet pour la sélection des questions
        getConjugaisonouGrammaire();

    }

    // Méthode asynchrone qui récupère les questions de la BDD
    // et créé l'objet pour la sélection des questions
    private void getConjugaisonouGrammaire() {

        class GetConjugaisonouGrammaire extends AsyncTask<Void, Void, List<Question>> {

            @Override
            protected List<Question> doInBackground(Void... voids) {
                // Récupération des questions en fonction du mode
                List<Question> ListQuestions = db.getAppDatabase()
                        .questionDao()
                        .getQuestionTheme(mode);
                return ListQuestions;
            }

            @Override
            protected void onPostExecute(List<Question> cAll) {
                super.onPostExecute(cAll);

                // Création de l'objet pour des questions aléatoires
                questions = new SelectionQuestion((ArrayList<Question>) cAll);

                // Initialisation de l'affichage avec l'ennoncé et les réponses
                TextView recherche = findViewById(R.id.ennonceQuestion);
                recherche.setText(questions.getEnnonceCourant());

                ArrayList<String> reponses = questions.getReponsesCourante();

                RadioButton b1 = findViewById(R.id.reponseQuestion1);
                RadioButton b2 = findViewById(R.id.reponseQuestion2);
                RadioButton b3 = findViewById(R.id.reponseQuestion3);
                b1.setText(reponses.get(0));
                b2.setText(reponses.get(1));
                b3.setText(reponses.get(2));

                b1.setChecked(true);

            }
        }

        GetConjugaisonouGrammaire gc = new GetConjugaisonouGrammaire();
        gc.execute();
    }

    // Méthode appelée lors de la validation de son choix par l'utilisateur
    // Elle met à jour l'affichage et gére les erreurs
    public void validationConjugaison(View view) {
        RadioButton b1 = findViewById(R.id.reponseQuestion1);
        RadioButton b2 = findViewById(R.id.reponseQuestion2);
        RadioButton b3 = findViewById(R.id.reponseQuestion3);

        // Récupération du choix de l'utilisateur
        String choix;
        if(b1.isChecked()){
            choix = b1.getText().toString().toUpperCase();;
        }else if(b2.isChecked()){
            choix = b2.getText().toString().toUpperCase();;
        }else{
            choix = b3.getText().toString().toUpperCase();
        }

        // Si mauvaise réponse
        if(!choix.equals(questions.getResultatCourant().toUpperCase())){
            erreurs++;
            Toast.makeText(this,"Mauvaise réponse, la réponse était : "+questions.getResultatCourant(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Bonne réponse",Toast.LENGTH_SHORT).show();
        }
        questions.indiceSuivant(); // Passage à la question suivante
        if(questions.finListe()){ // Si plus de question
            Intent resultat = new Intent(this, ActivityFrancaisResultat.class);

            // Envoie des données à l'activité resultat
            resultat.putExtra("theme","Français");
            if(mode.equals("Français - Conjugaison")){
                resultat.putExtra("sous_theme","Conjugaison");
            }else{
                resultat.putExtra("sous_theme","Grammaire - Facile");
            }

            resultat.putExtra("erreurs",erreurs);
            resultat.putExtra("note",String.valueOf(5-erreurs)+"/5");

            startActivity(resultat); // Lancement de l'activité résultat
            finish(); // On enlève de la pile l'activité courante
        }else{
            // Mise à jour de l'affichage avec la question suivante
            TextView recherche = findViewById(R.id.ennonceQuestion);
            recherche.setText(questions.getEnnonceCourant());
            ArrayList<String> reponses = questions.getReponsesCourante();
            b1.setText(reponses.get(0));
            b1.setChecked(true);
            b2.setText(reponses.get(1));
            b3.setText(reponses.get(2));
        }
    }
}