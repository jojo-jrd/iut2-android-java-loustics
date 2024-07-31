package com.example.josseraj_ecole_des_loustics.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;
import com.example.josseraj_ecole_des_loustics.classes.utilitaire.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class ActivityProfil extends AppCompatActivity {

    private ArrayList<Exercice> exercices;
    private DatabaseLoustics db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exercices = new ArrayList<>();
        db = DatabaseLoustics.getInstance(getApplicationContext());
        setContentView(R.layout.activity_profil);


        getExercices();
    }



    // ENVOIE DES DONNES DU COMPTE CHOISI AU MENU PRINCIPAL
    private void getExercices() {

        class GetExercices extends AsyncTask<Void, Void, List<Exercice>> {

            @Override
            protected List<Exercice> doInBackground(Void... voids) {
                 List<Exercice> e =db.getAppDatabase()
                                        .exerciceDao()
                                        .getExerciceFrom(((MyApplication) ActivityProfil.this.getApplication()).getCompteCourant().getPseudo()) ;
                 return e;
            }

            @Override
            protected void onPostExecute(List<Exercice> e) {
                super.onPostExecute(e);
                exercices.clear();
                exercices.addAll(e);

                MiseaJourExercice();
            }
        }

        GetExercices ge = new GetExercices();
        ge.execute();

    }

    private void MiseaJourExercice() {
        LinearLayout linearBase = findViewById(R.id.exercices);
        linearBase.removeAllViews();
        float total = 0;
        int nombreExercice = 0;
        for(Exercice e : exercices){

            LinearLayout l = (LinearLayout) getLayoutInflater().inflate(R.layout.template_exercice,null);

            TextView resultat = l.findViewById(R.id.template_resultat);
            resultat.setText(e.getResultat());
            float note = Utilitaire.eval(e.getResultat());
            total += note;
            nombreExercice++;

            if(note>0.69){
                resultat.setTextColor(Color.GREEN);
            }else if(note>=0.35){
                resultat.setTextColor(Color.rgb(255,180,0));
            }else{
                resultat.setTextColor(Color.RED);
            }

            TextView theme = l.findViewById(R.id.template_theme);
            theme.setText(e.getTheme()+" - "+e.getSous_theme());

            TextView date = l.findViewById(R.id.template_date);
            date.setText(e.getDate());

            linearBase.addView(l);

        }
        TextView moyenne = findViewById(R.id.moyenne);
        if(nombreExercice==0){
            moyenne.setText("NA");
        }else{
            float noteMoyenne = total/nombreExercice;
            if(noteMoyenne>0.69){
                moyenne.setTextColor(Color.GREEN);
            }else if(noteMoyenne>=0.35){
                moyenne.setTextColor(Color.rgb(255,180,0));
            }else{
                moyenne.setTextColor(Color.RED);
            }
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
            moyenne.setText(String.valueOf(df.format(noteMoyenne*20))+"/20");
        }
    }

}