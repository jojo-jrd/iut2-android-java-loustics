package com.example.josseraj_ecole_des_loustics.bd;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.josseraj_ecole_des_loustics.bd.capitale.Capitale;
import com.example.josseraj_ecole_des_loustics.bd.capitale.CapitaleDao;
import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;
import com.example.josseraj_ecole_des_loustics.bd.compte.CompteDao;
import com.example.josseraj_ecole_des_loustics.bd.drapeau.Drapeau;
import com.example.josseraj_ecole_des_loustics.bd.drapeau.DrapeauDao;
import com.example.josseraj_ecole_des_loustics.bd.exercice.Exercice;
import com.example.josseraj_ecole_des_loustics.bd.exercice.ExerciceDao;
import com.example.josseraj_ecole_des_loustics.bd.question.Question;
import com.example.josseraj_ecole_des_loustics.bd.question.QuestionDao;
import com.example.josseraj_ecole_des_loustics.bd.texte_a_trou.TexteATrouDao;
import com.example.josseraj_ecole_des_loustics.bd.texte_a_trou.TexteATrou;

@Database(entities = {Compte.class,
                      Exercice.class,
                      Capitale.class,
                      Question.class,
                      TexteATrou.class,
                      Drapeau.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract CompteDao compteDao();

    public abstract ExerciceDao exerciceDao();

    public abstract CapitaleDao capitaleDao();

    public abstract QuestionDao questionDao();

    public abstract TexteATrouDao texteATrouDao();

    public abstract DrapeauDao drapeauDao();

}
