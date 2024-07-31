package com.example.josseraj_ecole_des_loustics.activities.geographie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.drapeau.Drapeau;
import com.example.josseraj_ecole_des_loustics.classes.selections.SelectionDrapeau;

import java.util.ArrayList;
import java.util.List;

public class ActivityGeoDrapeauAffichage extends AppCompatActivity {

    // Attributs de l'activité
    private DatabaseLoustics db;
    private SelectionDrapeau drapeaux;
    private int erreurs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des attributs
        db = DatabaseLoustics.getInstance(getApplicationContext());
        erreurs = 0;

        setContentView(R.layout.activity_geo_drapeau_affichage);

        getDrapeaux();
    }

    // Méthode qui lance un appel asynchrone à la bdd pour
    // obtenir les drapeaux et remplir aléatoirement l'objet SelectionDrapeau
    // Elle initialise l'affichage
    private void getDrapeaux() {

        class GetDrapeaux extends AsyncTask<Void, Void, List<Drapeau>> {

            @Override
            protected List<Drapeau> doInBackground(Void... voids) {
                // Récupération des drapeaux/pays
                List<Drapeau> ListDrapeaux = db.getAppDatabase()
                        .drapeauDao()
                        .getAll();
                return ListDrapeaux;
            }

            @Override
            protected void onPostExecute(List<Drapeau> cAll) {
                super.onPostExecute(cAll);

                // Création de l'objet qui séléctionne des drapeaux aléatoirement dans la BDD
                drapeaux = new SelectionDrapeau((ArrayList<Drapeau>) cAll);

                // Initialisation de l'affichage
                ImageView drapeau = findViewById(R.id.affichageDrapeau);
                drapeau.setImageDrawable(getDrawable(drapeaux.getDrapeauCourant().getDrapeau()));

            }
        }

        GetDrapeaux gd = new GetDrapeaux();
        gd.execute();
    }

    // Méthode appelée lors de la validation de son choix par l'utilisateur
    // Elle met à jour l'affichage et gére les erreurs
    public void validationDrapeau(View view) {
        // Récupération des objets de l'interface pour les données
        ImageView drapeau = findViewById(R.id.affichageDrapeau);
        EditText reponse = findViewById(R.id.reponseDrapeauPays);

        reponse.setHintTextColor(Color.GRAY);

        if (reponse.getText().toString().isEmpty()){ // Si pas de réponse
            reponse.setHintTextColor(Color.RED);
            Toast.makeText(this,"Veuillez répondre avant de valider",Toast.LENGTH_SHORT).show();
        }else{
            // Si mauvaise réponse
            if(!reponse.getText().toString().toUpperCase().replaceAll("\\s+","").equals(drapeaux.getResultatCourant().toUpperCase().replaceAll("\\s+",""))){
                erreurs++;
                Toast.makeText(this,"Mauvaise réponse, la réponse était : "+drapeaux.getResultatCourant(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Bonne réponse",Toast.LENGTH_SHORT).show();
            }
            drapeaux.indiceSuivant(); // Passage au drapeau suivant
            if(drapeaux.finListe()){ // Si plus de drapeau

                Intent resultat = new Intent(this, ActivityGeoResultat.class);

                // Envoie des données à l'activité résultat pour enregistrement
                resultat.putExtra("theme","Géographie");
                resultat.putExtra("sous_theme","Drapeaux");
                resultat.putExtra("erreurs",erreurs);
                resultat.putExtra("note",String.valueOf(5-erreurs)+"/5");

                startActivity(resultat);
                finish();
            }else{
                // Mise à jour de l'affichage
                reponse.setText("");
                drapeau.setImageDrawable(getDrawable(drapeaux.getDrapeauCourant().getDrapeau()));
            }

        }
    }

}