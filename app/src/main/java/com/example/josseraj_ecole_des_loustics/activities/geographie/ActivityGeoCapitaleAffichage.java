package com.example.josseraj_ecole_des_loustics.activities.geographie;

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
import com.example.josseraj_ecole_des_loustics.bd.capitale.Capitale;
import com.example.josseraj_ecole_des_loustics.classes.selections.SelectionCapitale;

import java.util.ArrayList;
import java.util.List;

public class ActivityGeoCapitaleAffichage extends AppCompatActivity {

    // Attributs de l'activité
    private DatabaseLoustics db;
    private SelectionCapitale cap;
    private int erreurs;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Récupération des données envoyé par l'activité de choix
        db = DatabaseLoustics.getInstance(getApplicationContext());
        erreurs = 0;
        mode = getIntent().getStringExtra("mode");

        setContentView(R.layout.activity_geo_capitale_affichage);

        // Récupération des capitales de la BDD et création de l'objet pour la sélection des capitales
        getCapitalesPays();

        // Initialisation de l'affichage en fonction du mode choisi
        TextView ennonce = findViewById(R.id.ennonceCapitale);
        EditText choix = findViewById(R.id.reponseCapitalePays);
        choix.setHintTextColor(Color.GRAY);

        if(mode.equals("rC")){ // Recherche Capital
            ennonce.setText("Trouvez la capital de :");
            choix.setHint("Capital");
        }else{ // Recherche Pays
            ennonce.setText("Trouvez le pays de :");
            choix.setHint("Pays");
        }
    }

    // Méthode asynchrone qui récupère les capitales/pays de la BDD
    // et créé l'objet pour la sélection des capitales
    private void getCapitalesPays() {

        class GetCapitalesPays extends AsyncTask<Void, Void, List<Capitale>> {

            @Override
            protected List<Capitale> doInBackground(Void... voids) {
                // Récupération des capitales/pays
                List<Capitale> ListCapitales = db.getAppDatabase()
                        .capitaleDao()
                        .getAll();
                return ListCapitales;
            }

            @Override
            protected void onPostExecute(List<Capitale> cAll) {
                super.onPostExecute(cAll);

                // Création de l'objet avec toutes les capitales et pays
                cap = new SelectionCapitale(getIntent().getStringExtra("mode"), (ArrayList<Capitale>) cAll);

                // Mise  à jour de l'affichage
                TextView recherche = findViewById(R.id.affichageCapitalePays);
                recherche.setText(cap.getCapitalesPaysCourant());

            }
        }

        GetCapitalesPays gc = new GetCapitalesPays();
        gc.execute();
    }

    // Méthode appelée lors de la validation de son choix par l'utilisateur
    // Elle met à jour l'affichage et gère les erreurs
    public void validationCapitalePays(View view) {
        TextView recherche = findViewById(R.id.affichageCapitalePays);
        TextView reponse = findViewById(R.id.reponseCapitalePays);

        reponse.setHintTextColor(Color.GRAY);

        if (reponse.getText().toString().isEmpty()){ // Si pas de réponse
            reponse.setHintTextColor(Color.RED);
            Toast.makeText(this,"Veuillez répondre avant de valider",Toast.LENGTH_SHORT).show();
        }else{
            // Si mauvaise réponse
            if(!reponse.getText().toString().toUpperCase().replaceAll("\\s+","").equals(cap.getResultatCourant().toUpperCase().replaceAll("\\s+",""))){
                erreurs++;
                Toast.makeText(this,"Mauvaise réponse, la réponse était : "+cap.getResultatCourant(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Bonne réponse",Toast.LENGTH_SHORT).show();
            }
            cap.indiceSuivant(); // Passage à la capitale/pays suivant
            if(cap.finListe()){ // Si plu de capitale/pays
                Intent resultat = new Intent(this, ActivityGeoResultat.class);

                // Envoie des données à l'activité de résultat pour enregistrement
                resultat.putExtra("theme","Géographie");
                if(mode.equals("rC")){ // Recherche Capital
                    resultat.putExtra("sous_theme","Capitales");
                }else{ // Recherche Pays
                    resultat.putExtra("sous_theme","Pays");
                }
                resultat.putExtra("erreurs",erreurs);
                resultat.putExtra("note",String.valueOf(7-erreurs)+"/7");

                // lancement de l'activité du résultat
                startActivity(resultat);
                finish();
            }else{ // Mise à jour de l'affichage
                reponse.setText("");
                recherche.setText(cap.getCapitalesPaysCourant());
            }

        }

    }
}