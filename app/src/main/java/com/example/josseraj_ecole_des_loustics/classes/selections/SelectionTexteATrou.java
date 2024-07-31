package com.example.josseraj_ecole_des_loustics.classes.selections;

import android.util.Log;

import com.example.josseraj_ecole_des_loustics.bd.question.Question;
import com.example.josseraj_ecole_des_loustics.bd.texte_a_trou.TexteATrou;

import java.util.ArrayList;
import java.util.Random;

public class SelectionTexteATrou {

    private ArrayList<TexteATrou> textesATrous;

    private int indice_courant;

    public SelectionTexteATrou(ArrayList<TexteATrou> tousTextes) {
        indice_courant = 0;
        textesATrous = new ArrayList<>();

        Random r = new Random();
        ArrayList<Integer> choixDejaFait = new ArrayList<>();
        int numTexte = r.nextInt(tousTextes.size());

        for(int k = 0; k<4;k++){
            while (choixDejaFait.contains(numTexte)) {
                numTexte = r.nextInt(tousTextes.size());
            }
            choixDejaFait.add(numTexte);
            textesATrous.add(tousTextes.get(numTexte));
        }
    }

    //=====================
    // GETTERS
    //=====================

    public TexteATrou getTexteATrouCourant(){ return textesATrous.get(indice_courant); }

    public String getResultat1Courant(){
        return textesATrous.get(indice_courant).getReponse1();
    }

    public String getResultat2Courant(){
        return textesATrous.get(indice_courant).getReponse2();
    }


    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indice_courant++;
    }

    public boolean finListe(){
        return indice_courant == textesATrous.size();
    }
}
