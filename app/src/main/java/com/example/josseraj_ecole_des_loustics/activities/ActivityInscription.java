package com.example.josseraj_ecole_des_loustics.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.josseraj_ecole_des_loustics.R;
import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;
import com.example.josseraj_ecole_des_loustics.bd.DatabaseLoustics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ActivityInscription extends AppCompatActivity {

    private long avatarChoisi;
    private int idAvatarChoisi;
    private HashMap<Integer,Integer> avatars;
    private Compte compteModifie;
    private DatabaseLoustics db;
    private boolean confirmation = true; // Variable de confirmation lors de la modification d'un compte


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        db = DatabaseLoustics.getInstance(getApplicationContext());

        // Recupération de tous les avatars
        avatars = new HashMap<>();
        avatars.put(R.id.a1,R.drawable.avatar1);
        avatars.put(R.id.a2,R.drawable.avatar2);
        avatars.put(R.id.a3,R.drawable.avatar3);
        avatars.put(R.id.a4,R.drawable.avatar4);
        avatars.put(R.id.a5,R.drawable.avatar5);
        avatars.put(R.id.a6,R.drawable.avatar6);
        avatars.put(R.id.a7,R.drawable.avatar7);
        avatars.put(R.id.a8,R.drawable.avatar8);
        avatars.put(R.id.a9,R.drawable.avatar9);
        avatars.put(R.id.a10,R.drawable.avatar10);
        avatars.put(R.id.a11,R.drawable.avatar11);
        avatars.put(R.id.a12,R.drawable.avatar12);

        // Initialistion des valeurs
        avatarChoisi = avatars.get(R.id.a1);
        idAvatarChoisi = R.id.a1;

        // Recupération du compte s'il est modifié
        compteModifie = getIntent().getParcelableExtra("compte");
        if(compteModifie!=null){
            avatarChoisi = (int) compteModifie.getAvatar();
            for (Integer i : avatars.keySet()){
                if(avatars.get(i)==avatarChoisi){
                    idAvatarChoisi = i; // Recupération de l'id de l'avatar du compte
                }
            }

            // Initialisation de l'affichage
            ((TextView) findViewById(R.id.texteInscription)).setText("Modification du compte");
            EditText pseudo = findViewById(R.id.pseudo);
            pseudo.setText(compteModifie.getPseudo());
            pseudo.setFocusable(false);
            pseudo.setTextColor(Color.rgb(158,158,158));
            ((EditText) findViewById(R.id.prenom)).setText(compteModifie.getPrenom());
            ((EditText) findViewById(R.id.nom)).setText(compteModifie.getNom());

        }

        // Attribution de l'avatar ou celui modifié
        ImageView avatar = findViewById(R.id.avatar);
        ImageView i = findViewById(idAvatarChoisi);
        i.setBackground(getDrawable(R.drawable.selection));
        avatar.setImageDrawable(getDrawable(avatars.get(idAvatarChoisi)));

    }

    public void SaveCompte(View view){

        EditText editPseudo = findViewById(R.id.pseudo);
        String pseudo = editPseudo.getText().toString().trim();
        String nom = ((EditText)findViewById(R.id.nom)).getText().toString().trim();
        String prenom = ((EditText)findViewById(R.id.prenom)).getText().toString().trim();


        editPseudo.setTextColor(Color.BLACK);
        // Si un des EdiTtext est vide
        if(pseudo.length()==0 || nom.length()==0 || prenom.length()==0){
            Toast.makeText(this, "Merci de remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }


        class SaveCompte extends AsyncTask<Void, Void, Compte> {

            @Override
            protected Compte doInBackground(Void... voids) {
                // Si inscription
                if(compteModifie==null){

                    Compte compteExistant = db.getAppDatabase().compteDao().getCompte(pseudo.trim());
                    // S'il n'y a pas de compte avec ce pseudo
                    if(compteExistant==null){
                        // creating a task
                        Compte compte = new Compte();
                        compte.setAvatar(avatarChoisi);
                        compte.setNom(nom);
                        compte.setPrenom(prenom);
                        compte.setPseudo(pseudo);

                        // adding to database
                        db.getAppDatabase()
                                .compteDao()
                                .insert(compte);

                        return compte;
                    }else{

                        return null;
                    }
                }else{ // Si modification d'un compte

                    compteModifie.setAvatar(avatarChoisi);
                    compteModifie.setNom(nom);
                    compteModifie.setPrenom(prenom);

                    db.getAppDatabase()
                            .compteDao()
                            .update(compteModifie);

                    return compteModifie;
                }


            }

            @Override
            protected void onPostExecute(Compte compte) {
                super.onPostExecute(compte);
                if(compte==null){
                    editPseudo.setTextColor(Color.RED);
                    Toast.makeText(ActivityInscription.this, "Pseudo déjà utilisé", Toast.LENGTH_SHORT).show();
                }else{
                    // Quand l'inscription ou la modification est faite, on arrête l'activité ActivityInscription (on l'enleve de la pile d'activités)
                    setResult(RESULT_OK);
                    finish();
                }

            }
        }

        if(compteModifie!=null){ // Si modification

            // Récupération de ce qui a été modifié pour un affichage dans la boite de dialogue
            String affichage = "\n";
            if(compteModifie.getAvatar() != avatarChoisi){
                affichage += "\t - votre avatar\n";
            }
            if(!compteModifie.getNom().equals(nom)){
                affichage += "\t - votre nom\n";
            }
            if(!compteModifie.getPrenom().equals(prenom)){
                affichage += "\t - votre prénom\n";
            }

            if(affichage.equals("\n")){// Si pas de changement

                // Affichage d'un message
                Toast.makeText(this,"Vous n'avez rien modifié sur votre profil.",Toast.LENGTH_SHORT).show();
                editPseudo.setTextColor(Color.rgb(158,158,158));

            }else{ // Si des changements effectués

                // Création d'une boite de dialogue pour demander confirmation
                AlertDialog.Builder dialogue = new AlertDialog.Builder(this);
                dialogue.setTitle("Demande de confirmation");
                dialogue.setMessage("Etes-vous sûr de vouloir modifier :"+affichage);
                dialogue.setIcon(android.R.drawable.ic_dialog_alert);
                dialogue.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SaveCompte st = new SaveCompte();
                        st.execute();
                    }
                }); // rien à faire si clique sur oui
                dialogue.setNegativeButton("Non", null);
                dialogue.show();
            }




        }else{ // Si inscription
            SaveCompte st = new SaveCompte();
            st.execute();
        }



    }

    // Méthode qui gère l'affichage et la sélection des avatars
    public void selectionImage(View view) {
        // Reinitisalisation de l'afficaheg du précédent avatar
        ImageView i = findViewById(idAvatarChoisi);
        i.setBackgroundColor(Color.TRANSPARENT);

        // Selection de l'image cliqué
        ImageView iV = (ImageView) view;
        iV.setBackground(getDrawable(R.drawable.selection));
        avatarChoisi = avatars.get(iV.getId());
        idAvatarChoisi = iV.getId();

        // Mise à jour de l'image principale avatar
        ImageView avatar = findViewById(R.id.avatar);
        avatar.setImageDrawable(getDrawable(avatars.get(idAvatarChoisi)));

    }
}