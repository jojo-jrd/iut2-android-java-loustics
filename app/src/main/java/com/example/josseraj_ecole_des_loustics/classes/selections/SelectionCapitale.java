package com.example.josseraj_ecole_des_loustics.classes.selections;

import android.util.Log;

import com.example.josseraj_ecole_des_loustics.bd.capitale.Capitale;

import java.util.ArrayList;
import java.util.Random;

public class SelectionCapitale {

    private ArrayList<String> capitalesPays;
    private ArrayList<String> resultat;
    private int indice_courant;

    public SelectionCapitale(String mode, ArrayList<Capitale> capitalesExistants){
        // Initialisation des arrayList
        capitalesPays = new ArrayList<>();
        indice_courant = 0;
        resultat = new ArrayList<>();
        Random r = new Random();


        ArrayList<Integer> choixDejaFait = new ArrayList<>();
        int numCapital = r.nextInt(capitalesExistants.size());

        if(mode.equals("rC")){ // Recherche Capital
            for(int i = 0; i<7; i++){
                while(choixDejaFait.contains(numCapital)){
                    numCapital = r.nextInt(capitalesExistants.size());
                }
                capitalesPays.add(capitalesExistants.get(numCapital).getPays());
                resultat.add(capitalesExistants.get(numCapital).getCapitale());
                choixDejaFait.add(numCapital);
            }


        }else{ // recherche Pays
            for(int i = 0; i<7; i++){
                while(choixDejaFait.contains(numCapital)){
                    numCapital = r.nextInt(capitalesExistants.size());
                }
                resultat.add(capitalesExistants.get(numCapital).getPays());
                capitalesPays.add(capitalesExistants.get(numCapital).getCapitale());
                choixDejaFait.add(numCapital);
            }

        }
    }

    //=====================
    // GETTERS
    //=====================

    public ArrayList<String> getCapitalesPays() { return capitalesPays;}

    public ArrayList<String> getResultat() {return resultat;}

    public String getCapitalesPaysCourant(){
        return capitalesPays.get(indice_courant);
    }

    public String getResultatCourant(){
        return resultat.get(indice_courant);
    }

    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indice_courant++;
    }

    public boolean finListe(){
        return indice_courant == resultat.size();
    }

}
