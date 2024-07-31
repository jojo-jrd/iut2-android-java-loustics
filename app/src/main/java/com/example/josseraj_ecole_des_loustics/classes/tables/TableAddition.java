package com.example.josseraj_ecole_des_loustics.classes.tables;

import com.example.josseraj_ecole_des_loustics.classes.operations.Addition;

import java.util.ArrayList;
import java.util.Random;

public class TableAddition {

    private ArrayList<Addition> additions;
    private ArrayList<Integer> resultats;
    private int indice_courant;

    public TableAddition(int difficultés){
        indice_courant = 0;
        additions = new ArrayList<>();
        Random r = new Random();
        int op1;
        int op2;
        if(difficultés==1){ // facile
            // 5 additions avec opérandes entre 0 et 10

            for (int k = 0; k<5; k++){
                op1 = r.nextInt(11);
                op2 = r.nextInt(11);
                additions.add(new Addition(op1,op2));
            }
        }else if(difficultés==2){ // moyen
            // 7 additions avec opérandes entre 0 et 50

            for (int k = 0; k<7; k++){
                op1 = r.nextInt(51);
                op2 = r.nextInt(51);
                additions.add(new Addition(op1,op2));
            }
        }else{ // Difficile
            // 10 additions avec opérandes entre 0 et 500

            for (int k = 0; k<10; k++){
                op1 = r.nextInt(501);
                op2 = r.nextInt(501);
                additions.add(new Addition(op1,op2));
            }
        }

        resultats = new ArrayList<Integer>();
        for(Addition a : additions){
            resultats.add(a.getResultat());
        }

    }

    //=====================
    // GETTERS
    //=====================

    public Integer getResultatCourant(){
        return resultats.get(indice_courant);
    }

    public Addition getAdditionCourante(){
        return additions.get(indice_courant);
    }

    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indice_courant++;
    }

    public boolean finListe(){
        return indice_courant == additions.size();
    }
}
