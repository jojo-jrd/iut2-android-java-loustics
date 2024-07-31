package com.example.josseraj_ecole_des_loustics.classes.tables;

import com.example.josseraj_ecole_des_loustics.classes.operations.Multiplication;

import java.util.ArrayList;
import java.util.Random;

public class TableMultiplication {

    private ArrayList<Multiplication> multiplications;

    public TableMultiplication(int nb, int difficultés){
        multiplications = new ArrayList<>();
        Random r = new Random();
        if(difficultés==1){ // facile
            // 5 Multiplications de 0 à 4

            for (int k = 0; k<5; k++){
                multiplications.add(new Multiplication(nb,k));
            }
        }else if(difficultés==2){ // moyen
            // 5 Multiplications aléatoires mais dans l'ordre

            int multiplication_courante = 0;
            for (int k = 0; k<5; k++){
                multiplication_courante += r.nextInt(3)+1;
                multiplications.add(new Multiplication(nb,multiplication_courante));
            }
        }else if(difficultés==3){ // Difficile
            // 10 multiplications dans un ordre aléatoire

            int multiplication_courante = r.nextInt(11);
            ArrayList<Integer> nombresDejaFait = new ArrayList<>();

            for (int k = 0; k<10; k++){

                while (nombresDejaFait.contains(multiplication_courante)){
                    multiplication_courante = r.nextInt(11);
                }

                nombresDejaFait.add(multiplication_courante);
                multiplications.add(new Multiplication(nb,multiplication_courante));
            }
        }else{ // Très Difficile
            // 10 multiplications avec les 2 opérandes aléatoires
            int operande1;
            int operande2;
            for (int k = 0; k<10; k++){
                operande1 = r.nextInt(11);
                operande2 = r.nextInt(11);
                multiplications.add(new Multiplication(operande1,operande2));
            }



        }
    }

    public ArrayList<Multiplication> getMultiplications() {
        return multiplications;
    }

    // Retourne un arrayList de tous les résultats
    public ArrayList<Integer> getResultats(){
        ArrayList<Integer> resultats = new ArrayList<Integer>();
        for(Multiplication m : multiplications){
            resultats.add(m.getResultat());
        }
        return resultats;
    }
}
