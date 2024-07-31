package com.example.josseraj_ecole_des_loustics.activities.maths;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.menus.ActivityMenuMaths;
import com.example.josseraj_ecole_des_loustics.activities.menus.ActivityMenuPrincipal;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;
import com.example.josseraj_ecole_des_loustics.classes.utilitaire.Utilitaire;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityMathTableEchec extends AppCompatActivity {

    private int erreurs;
    private String theme;
    private String sous_theme;
    private String note;
    private DatabaseLoustics db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = DatabaseLoustics.getInstance(getApplicationContext());

        this.erreurs = getIntent().getIntExtra("erreurs",0);
        this.theme = getIntent().getStringExtra("theme");
        this.sous_theme = getIntent().getStringExtra("sous_theme");
        this.note = getIntent().getStringExtra("note");

        setContentView(R.layout.activity_math_table_echec);

        // Initisialisation de l'affichage avec les données
        TextView tw = findViewById(R.id.erreur);
        tw.setText("Nombre d'erreurs : "+ String.valueOf(this.erreurs));

        // Affichage de la note avec la couleur correspondante
        float ratio = Utilitaire.eval(note);
        TextView noteAffichage = findViewById(R.id.noteMathEchec);
        noteAffichage.setText(note);
        if(ratio>0.69){
            noteAffichage.setTextColor(Color.GREEN);
        }else if(ratio>=0.35){
            noteAffichage.setTextColor(Color.rgb(255,180,0));
        }else{
            noteAffichage.setTextColor(Color.RED);
        }

    }

    // Affichage de l'activité précédente pour correction
    public void retourArriere(View view) {
        setResult(RESULT_OK);
        finish();
    }

    // Affichage du menu mathématiques
    public void retourMenuMath(View view) {
        enregistrementExercice(false);
    }

    // Affichage du menu principal
    public void retourMenuPrincipal(View view) { enregistrementExercice(true); }


    // Méthode qui enregistre dans la bdd l'exercice effectué et affiche l'activité voulue
    private void enregistrementExercice(boolean menuPrincipal) {
        class EnregistrementExercice extends AsyncTask<Void, Void, Exercice> {

            @Override
            protected Exercice doInBackground(Void... voids) {
                // Création de l'exercice
                Exercice e = new Exercice();

                e.setPseudo(((MyApplication) ActivityMathTableEchec.this.getApplication()).getCompteCourant().getPseudo());

                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = new Date();
                e.setDate(s.format(date));

                e.setTheme(ActivityMathTableEchec.this.theme);
                e.setSous_theme(ActivityMathTableEchec.this.sous_theme);
                e.setResultat(ActivityMathTableEchec.this.note);

                // Insertion dans la bdd de l'exercice
                long id = db.getAppDatabase()
                        .exerciceDao()
                        .insert(e);

                e.setId(id);

                return e;
            }

            @Override
            protected void onPostExecute(Exercice e) {
                super.onPostExecute(e);

                Intent intent;
                // Affichage de l'exercice voulu
                if(menuPrincipal){
                    intent = new Intent(ActivityMathTableEchec.this, ActivityMenuPrincipal.class);
                }else{
                    intent = new Intent(ActivityMathTableEchec.this, ActivityMenuMaths.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // On nettoie la pile d'activité
                startActivity(intent);
            }
        }

        EnregistrementExercice eE = new EnregistrementExercice();
        eE.execute();

    }
}