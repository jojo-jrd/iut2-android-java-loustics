package com.example.josseraj_ecole_des_loustics.classes.selections;

import android.util.Log;

import com.example.josseraj_ecole_des_loustics.bd.drapeau.Drapeau;

import java.util.ArrayList;
import java.util.Random;

public class SelectionDrapeau {

    private ArrayList<Drapeau> drapeaux;
    private ArrayList<String> resultats;
    private int indiceCourant;

    public SelectionDrapeau(ArrayList<Drapeau> drap){
        // Initialisation des variables
        drapeaux = new ArrayList<>();
        resultats = new ArrayList<>();
        indiceCourant = 0;

        ArrayList<Integer> choixDejaFait = new ArrayList<>();
        Random r = new Random();
        int numDrapeau = r.nextInt(drap.size());

        // 5 drapeaux choisi aléatoirement
        for(int k = 0; k<5; k++){
            while(choixDejaFait.contains(numDrapeau)){
                numDrapeau = r.nextInt(drap.size());
            }

            drapeaux.add(drap.get(numDrapeau));
            resultats.add(drap.get(numDrapeau).getPays());
            choixDejaFait.add(numDrapeau);
        }

    }

    //=====================
    // GETTERS
    //=====================

    public Drapeau getDrapeauCourant(){
        return drapeaux.get(indiceCourant);
    }

    public String getResultatCourant(){
        return resultats.get(indiceCourant);
    }

    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indiceCourant++;
    }

    public boolean finListe(){
        return indiceCourant == drapeaux.size();
    }

}
