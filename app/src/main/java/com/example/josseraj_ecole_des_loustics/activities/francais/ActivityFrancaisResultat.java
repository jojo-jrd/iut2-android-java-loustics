package com.example.josseraj_ecole_des_loustics.activities.francais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.menus.ActivityMenuFrancais;
import com.example.josseraj_ecole_des_loustics.activities.menus.ActivityMenuPrincipal;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;
import com.example.josseraj_ecole_des_loustics.classes.utilitaire.Utilitaire;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityFrancaisResultat extends AppCompatActivity {

    // Attributs de l'activité
    private String theme;
    private String sous_theme;
    private String note;
    private DatabaseLoustics db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DatabaseLoustics.getInstance(getApplicationContext());

        // Récupération des données envoyées par les activités d'affichage
        this.theme = getIntent().getStringExtra("theme");
        this.sous_theme = getIntent().getStringExtra("sous_theme");
        this.note = getIntent().getStringExtra("note");
        int erreurs = getIntent().getIntExtra("erreurs",0);

        setContentView(R.layout.activity_francais_resultat);

        // Affichage de la note avec la couleur correspondante
        float ratio = Utilitaire.eval(note);
        TextView noteAffichage = findViewById(R.id.noteFrancais);
        noteAffichage.setText(note);
        if(ratio>0.69){
            noteAffichage.setTextColor(Color.GREEN);
        }else if(ratio>=0.35){
            noteAffichage.setTextColor(Color.rgb(255,180,0));
        }else{
            noteAffichage.setTextColor(Color.RED);
        }

        // Initialisation de l'affichage
        if(erreurs!=0){
            TextView messagePrincipal = findViewById(R.id.resultatAffichageFrancais);
            messagePrincipal.setText("Dommage !");
            TextView messageSecondaire = findViewById(R.id.resultatErreursFrancais);
            messageSecondaire.setText("Vous avez fait "+String.valueOf(erreurs)+" erreurs.\nVous pourrez réessayer plus tard.");
        }

    }

    // Méthode qui enregistre dans la BDD l'exercice effectué et
    // affiche l'activité voulue
    private void enregistrementExercice(boolean menuPrincipale) {

        class EnregistrementExercice extends AsyncTask<Void, Void, Exercice> {

            @Override
            protected Exercice doInBackground(Void... voids) {

                // Création de l'exercice
                Exercice e = new Exercice();
                e.setPseudo(((MyApplication) ActivityFrancaisResultat.this.getApplication()).getCompteCourant().getPseudo());

                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = new Date();
                e.setDate(s.format(date));

                e.setTheme(ActivityFrancaisResultat.this.theme);
                e.setSous_theme(ActivityFrancaisResultat.this.sous_theme);
                e.setResultat(ActivityFrancaisResultat.this.note);

                // Insertion dans la BDD de l'exercice
                long id = db.getAppDatabase()
                        .exerciceDao()
                        .insert(e);

                e.setId(id);

                return e;
            }

            @Override
            protected void onPostExecute(Exercice e) {
                super.onPostExecute(e);

                // Affichage de l'activité voulue en fonction du bouton cliqué
                Intent intent;
                if(menuPrincipale){
                    intent = new Intent(ActivityFrancaisResultat.this, ActivityMenuPrincipal.class);
                }else{
                    intent = new Intent(ActivityFrancaisResultat.this, ActivityMenuFrancais.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }

        EnregistrementExercice eE = new EnregistrementExercice();
        eE.execute();

    }

    // Méthode qui appelle l'enregistrement de l'exercice et affichage du menu principale
    public void retourMenuPrincipal(View view) {
        enregistrementExercice(true);
    }

    // Méthode qui appelle l'enregistrement de l'exercice et affichage du menu francais
    public void retourMenuFrancais(View view) {
        enregistrementExercice(false);
    }
}