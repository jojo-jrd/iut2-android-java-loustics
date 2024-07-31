package com.example.josseraj_ecole_des_loustics.bd.question;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String question;

    private String bonneReponse;
    private String autreReponse1;
    private String autreReponse2;

    private String theme;

    //=====================
    // GETTERS
    //=====================

    public long getId() { return id; }

    public String getQuestion() { return question; }

    public String getBonneReponse() { return bonneReponse; }

    public String getAutreReponse2() { return autreReponse2; }

    public String getAutreReponse1() { return autreReponse1; }

    public String getTheme() { return theme; }

    //=====================
    // SETTERS
    //=====================

    public void setId(long id) { this.id = id; }

    public void setQuestion(String question) { this.question = question; }


    public void setBonneReponse(String bonneReponse) { this.bonneReponse = bonneReponse; }


    public void setAutreReponse1(String autreReponse1) { this.autreReponse1 = autreReponse1; }


    public void setAutreReponse2(String autreReponse2) { this.autreReponse2 = autreReponse2; }

    public void setTheme(String theme) { this.theme = theme; }
}
