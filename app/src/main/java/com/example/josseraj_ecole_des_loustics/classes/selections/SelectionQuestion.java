package com.example.josseraj_ecole_des_loustics.classes.selections;

import android.util.Log;

import com.example.josseraj_ecole_des_loustics.bd.capitale.Capitale;
import com.example.josseraj_ecole_des_loustics.bd.question.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SelectionQuestion {

    private ArrayList<String> ennonce;
    private ArrayList<String> bonnesReponses;
    private ArrayList<String> mauvaisesReponses1;
    private ArrayList<String> mauvaisesReponses2;
    private int indice_courant;

    public SelectionQuestion(ArrayList<Question> questions) {
        // Initialisation des arrayList
        ennonce = new ArrayList<>();
        indice_courant = 0;
        bonnesReponses = new ArrayList<>();
        mauvaisesReponses1 = new ArrayList<>();
        mauvaisesReponses2 = new ArrayList<>();

        Random r = new Random();


        ArrayList<Integer> choixDejaFait = new ArrayList<>();
        int numQuestion = r.nextInt(questions.size());

        for (int i = 0; i < 5; i++) {
            while (choixDejaFait.contains(numQuestion)) {
                numQuestion = r.nextInt(questions.size());
            }
            choixDejaFait.add(numQuestion);

            Question q = questions.get(numQuestion);
            ennonce.add(q.getQuestion());
            bonnesReponses.add(q.getBonneReponse());
            mauvaisesReponses1.add(q.getAutreReponse1());
            mauvaisesReponses2.add(q.getAutreReponse2());

        }
    }

    //=====================
    // GETTERS
    //=====================

    public ArrayList<String> getReponsesCourante(){
        Random r = new Random();
        int bonneReponse = r.nextInt(3);
        int mauvaiseReponse1 = r.nextInt(3);

        while(mauvaiseReponse1==bonneReponse){
            mauvaiseReponse1 = r.nextInt(3);
        }
        int total = mauvaiseReponse1 + bonneReponse;
        int mauvaiseReponse2;
        if(total==2){
            mauvaiseReponse2 = 1;
        }else if(total==1){
            mauvaiseReponse2 = 2;
        }else{
            mauvaiseReponse2 = 0;
        }

        ArrayList<String> reponse = new ArrayList<>(Arrays.asList("", "", ""));


        reponse.set(bonneReponse,bonnesReponses.get(indice_courant));
        reponse.set(mauvaiseReponse1,mauvaisesReponses1.get(indice_courant));
        reponse.set(mauvaiseReponse2,mauvaisesReponses2.get(indice_courant));

        return reponse;
    }

    public String getResultatCourant(){
        return bonnesReponses.get(indice_courant);
    }

    public String getEnnonceCourant(){
        return ennonce.get(indice_courant);
    }

    //=====================
    // Autres méthodes
    //=====================

    public void indiceSuivant(){
        indice_courant++;
    }

    public boolean finListe(){
        return indice_courant == bonnesReponses.size();
    }
}
