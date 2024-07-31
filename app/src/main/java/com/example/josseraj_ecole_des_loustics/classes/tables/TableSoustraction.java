package com.example.josseraj_ecole_des_loustics.classes.tables;

import com.example.josseraj_ecole_des_loustics.classes.operations.Soustraction;

import java.util.ArrayList;
import java.util.Random;

public class TableSoustraction {

    private ArrayList<Soustraction> soustractions;
    private ArrayList<Integer> resultats;
    private int indice_courant;

    public TableSoustraction(int difficultés){
        indice_courant = 0;
        soustractions = new ArrayList<>();
        Random r = new Random();
        int op1;
        int op2;
        if(difficultés==1){ // facile
            // 5 soustractions avec opérandes entre 0 et 20
            for (int k = 0; k<5; k++){
                op1 = 0;
                op2 = 0;
                while(op1<=op2){
                    op1 = r.nextInt(21);
                    op2 = r.nextInt(21);
                }

                soustractions.add(new Soustraction(op1,op2));
            }
        }else if(difficultés==2){ // moyen
            // 7 additions avec opérandes entre 0 et 40

            for (int k = 0; k<7; k++){
                op1 = 0;
                op2 = 0;
                while(op1<=op2){
                    op1 = r.nextInt(41);
                    op2 = r.nextInt(41);
                }

                soustractions.add(new Soustraction(op1,op2));
            }
        }else{ // Difficile
            // 10 additions avec opérandes entre 0 et 100

            for (int k = 0; k<10; k++){
                op1 = 0;
                op2 = 0;
                while(op1<=op2){
                    op1 = r.nextInt(101);
                    op2 = r.nextInt(101);
                }

                soustractions.add(new Soustraction(op1,op2));
            }
        }

        resultats = new ArrayList<Integer>();
        for(Soustraction s : soustractions){
            resultats.add(s.getResultat());
        }

    }

    //=====================
    // GETTERS
    //=====================

    public Integer getResultatCourant(){
        return resultats.get(indice_courant);
    }

    public Soustraction getSoustractionCourante(){
        return soustractions.get(indice_courant);
    }

    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indice_courant++;
    }

    public boolean finListe(){
        return indice_courant == soustractions.size();
    }
}
