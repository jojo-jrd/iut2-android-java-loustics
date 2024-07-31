package com.example.josseraj_ecole_des_loustics.bd.texte_a_trou;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "texte_a_trou")
public class TexteATrou {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String partiePhrase1;
    private String reponse1;
    private String partiePhrase2;
    private String reponse2;
    private String partiePhrase3;

    private String theme;

    //=====================
    // GETTERS
    //=====================

    public long getId() { return id; }

    public String getPartiePhrase1() { return partiePhrase1; }

    public String getReponse1() { return reponse1; }

    public String getPartiePhrase2() { return partiePhrase2; }

    public String getReponse2() { return reponse2; }

    public String getTheme() { return theme; }

    public String getPartiePhrase3() { return partiePhrase3; }

    //=====================
    // SETTERS
    //=====================

    public void setId(long id) {
        this.id = id;
    }

    public void setPartiePhrase1(String partiePhrase1) { this.partiePhrase1 = partiePhrase1; }

    public void setReponse1(String reponse1) { this.reponse1 = reponse1; }

    public void setPartiePhrase2(String partiePhrase2) { this.partiePhrase2 = partiePhrase2; }

    public void setReponse2(String reponse2) { this.reponse2 = reponse2; }

    public void setTheme(String theme) { this.theme = theme; }

    public void setPartiePhrase3(String partiePhrase3) { this.partiePhrase3 = partiePhrase3; }
}
