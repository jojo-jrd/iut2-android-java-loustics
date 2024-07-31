package com.example.josseraj_ecole_des_loustics.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.menus.ActivityMenuPrincipal;
import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static int CODE_REQUETE_INSCRIPTION = 0;
    public final static int CODE_REQUETE_MODIFICATION = 1;
    private ArrayList<Compte> comptes;
    private DatabaseLoustics db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DatabaseLoustics.getInstance(getApplicationContext());
        comptes = new ArrayList<>();
        setContentView(R.layout.activity_main);

        getComptes();

    }
    // PARTIE POUR L'ACTIVITE INSCRIPTION ET RETOUR

    public void lancerInscription(View view) {
        Intent inscription = new Intent(this,ActivityInscription.class);
        startActivityForResult(inscription,CODE_REQUETE_INSCRIPTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode== CODE_REQUETE_INSCRIPTION && resultCode==RESULT_OK){
            Toast.makeText(this,"Inscription réussie", Toast.LENGTH_SHORT).show();
            getComptes();
        }else if(requestCode== CODE_REQUETE_MODIFICATION && resultCode==RESULT_OK){
            Toast.makeText(this,"Modifications réussies", Toast.LENGTH_SHORT).show();
            getComptes();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    // AFFICHAGE DE TOUS LES COMPTES


    private void getComptes() {

        class GetComptes extends AsyncTask<Void, Void, List<Compte>> {

            @Override
            protected List<Compte> doInBackground(Void... voids) {
                List<Compte> ListComptes = db.getAppDatabase()
                        .compteDao()
                        .getAll();
                return ListComptes;
            }

            @Override
            protected void onPostExecute(List<Compte> cAll) {
                super.onPostExecute(cAll);
                // Mettre à jour l'adapter avec la liste de taches
                comptes.clear();
                comptes.addAll(cAll);

                MiseaJourCompte();

            }
        }

        GetComptes gt = new GetComptes();
        gt.execute();
    }



    private void MiseaJourCompte() {
        LinearLayout linearBase = findViewById(R.id.comptes);
        linearBase.removeAllViews();
        for(Compte c : comptes){

            if(!c.getPseudo().equals("Anonyme")){
                LinearLayout l = (LinearLayout) getLayoutInflater().inflate(R.layout.template_compte,null);

                TextView pseudo = l.findViewById(R.id.pseudo_template);
                pseudo.setText(c.getPseudo());

                TextView nom = l.findViewById(R.id.nom_template);
                nom.setText(c.getNom());

                TextView prenom = l.findViewById(R.id.prenom_template);
                prenom.setText(c.getPrenom());

                ImageView avatar = l.findViewById(R.id.avatar_template);
                Drawable d = getResources().getDrawable((int) c.getAvatar());
                avatar.setImageDrawable(d);

                // Possibilité de modifier le profil
                ImageButton crayon = l.findViewById(R.id.penModif);
                crayon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent modif = new Intent(MainActivity.this,ActivityInscription.class);
                        modif.putExtra("compte",c);
                        startActivityForResult(modif,CODE_REQUETE_MODIFICATION);
                    }
                });

                // Pour aller au menu principal en cliquant sur ce compte
                l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getCompteChoisi(c.getPseudo());
                    }
                });




                linearBase.addView(l);
            }
        }
    }

    // ENVOIE DES DONNES DU COMPTE CHOISI AU MENU PRINCIPAL
    private void getCompteChoisi(String p) {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetCompteChoisi extends AsyncTask<Void, Void, Compte> {

            @Override
            protected Compte doInBackground(Void... voids) {
                Compte c = db.getAppDatabase()
                        .compteDao()
                        .getCompte(p);
                return c;
            }

            @Override
            protected void onPostExecute(Compte c) {
                super.onPostExecute(c);

                Intent intent = new Intent(MainActivity.this, ActivityMenuPrincipal.class);

                ((MyApplication) MainActivity.this.getApplication()).setCompteCourant(c);

                startActivity(intent);
            }
        }

        GetCompteChoisi gt = new GetCompteChoisi();
        gt.execute();

    }

    public void ConnexionAnonyme(View view) {
        getCompteChoisi("Anonyme");
    }
}