package com.example.josseraj_ecole_des_loustics.activities.menus;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.activities.ActivityProfil;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;
import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;
import com.example.josseraj_ecole_des_loustics.classes.MyApplication;

import java.util.HashMap;

public class ActivityMenuPrincipal extends AppCompatActivity {

    private DatabaseLoustics db;

    // Création de l'activité du menu principal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_principal);

        Compte compteCourant = ((MyApplication) this.getApplication()).getCompteCourant();
        db = DatabaseLoustics.getInstance(getApplicationContext());

        ImageView avatar = findViewById(R.id.avatarCourant);
        Drawable d = getResources().getDrawable((int)compteCourant.getAvatar());
        avatar.setImageDrawable(d);

        TextView pseudo = findViewById(R.id.pseudoCourant);
        pseudo.setText(compteCourant.getPseudo());

        // Mise d'une page profil si la personne connecté n'est pas inconnue
        if(!compteCourant.getPseudo().equals("Anonyme")){
            LinearLayout l = findViewById(R.id.profil);
            l.setOnClickListener(new View.OnClickListener() {
                // Affiche l'activité profil
                @Override
                public void onClick(View view) {
                    Intent profil = new Intent(ActivityMenuPrincipal.this, ActivityProfil.class);
                    startActivity(profil);
                }
            });

        }else{ // On enlève la bar de progression pour l'utilisateur anonyme
            ProgressBar pb = findViewById(R.id.progress_bar);
            pb.setVisibility(View.INVISIBLE);
            TextView tv = findViewById(R.id.avancement);
            tv.setVisibility(View.INVISIBLE);
        }

    }

    // A chaque fois le menu principal est affiché, on mets à jour la bar d'avancement
    @Override
    protected void onResume() {
        super.onResume();
        if(!((MyApplication) this.getApplication()).getCompteCourant().getPseudo().equals("Anonyme")) {
            getAvancement();
        }
    }

    // Affiche l'activité menu Mathématique
    public void MenuMaths(View view) {
        Intent math = new Intent(this, ActivityMenuMaths.class);
        startActivity(math);
    }

    // Affiche l'activité menu Geographie
    public void MenuGeographie(View view) {
        Intent geo = new Intent(this, ActivityMenuGeographie.class);
        startActivity(geo);
    }

    // Affiche l'activité menu Francais
    public void MenuFrancais(View view) {
        Intent fr = new Intent(this, ActivityMenuFrancais.class);
        startActivity(fr);
    }


    private void getAvancement() {
        class GetAvancement extends AsyncTask<Void, Void, Integer> {

            @Override
            protected Integer doInBackground(Void... voids) {
                float nombreExo = 15;
                HashMap<String,String> exo = new HashMap<>(); // <sous_theme,theme>

                // Additions
                exo.put("Addition - facile","Mathématiques");
                exo.put("Addition - moyen","Mathématiques");
                exo.put("Addition - difficile","Mathématiques");

                // Multiplication
                exo.put("Multiplication - facile","Mathématiques");
                exo.put("Multiplication - moyen","Mathématiques");
                exo.put("Multiplication - difficile","Mathématiques");
                exo.put("Multiplication - très difficile","Mathématiques");

                // Soustractions
                exo.put("Soustraction - facile","Mathématiques");
                exo.put("Soustraction - moyen","Mathématiques");
                exo.put("Soustraction - difficile","Mathématiques");

                // Conjugaison
                exo.put("Conjugaison","Français");

                // Grammaire
                exo.put("Grammaire - Facile","Français");
                exo.put("Grammaire - Moyen","Français");

                // Capitale
                exo.put("Capitales","Géographie");
                exo.put("Pays","Géographie");

                // Drapeau
                exo.put("Drapeaux","Géographie");
                float pourcentage = 0;
                for (String st : exo.keySet()){
                    boolean existe = db.getAppDatabase()
                                       .exerciceDao()
                                       .getExerciceFromTheme(
                                                ((MyApplication) ActivityMenuPrincipal.this.getApplication()).getCompteCourant().getPseudo(),
                                                exo.get(st),
                                                st);
                    if(existe){
                        pourcentage += 100.0/nombreExo;
                    }
                }



                return ((int)pourcentage);
            }

            @Override
            protected void onPostExecute(Integer p) {
                super.onPostExecute(p);
                // Affichage de la bar d'avanacement en fonction du pourcentage
                ProgressBar pb = findViewById(R.id.progress_bar);
                pb.setProgress(p);
                TextView tv = findViewById(R.id.avancement);
                tv.setText("Avancement : "+String.valueOf(p)+"%");

            }
        }

        GetAvancement ga = new GetAvancement();
        ga.execute();

    }
}