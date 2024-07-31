package com.example.josseraj_ecole_des_loustics.bd.exercice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.josseraj_ecole_des_loustics.bd.compte.Compte;

@Entity(tableName = "exercice")
public class Exercice implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String theme;
    private String sous_theme;
    private String date;
    private String resultat;

    @ForeignKey(entity = Compte.class,
            parentColumns = "pseudo",
            childColumns = "pseudo")
    private String pseudo;

    public Exercice(){}

    //=====================
    // GETTERS
    //=====================

    public long getId() { return id; }

    public String getTheme() { return theme; }

    public String getSous_theme() { return sous_theme; }

    public String getDate() { return date; }

    public String getResultat() { return resultat; }

    public String getPseudo() { return pseudo; }

    //=====================
    // SETTERS
    //=====================

    public void setId(long id) { this.id = id; }

    public void setTheme(String theme) { this.theme = theme; }

    public void setSous_theme(String sous_theme) { this.sous_theme = sous_theme; }

    public void setDate(String date) { this.date = date; }

    public void setResultat(String resultat) { this.resultat = resultat; }

    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    //=====================
    // PARCELABLE
    //=====================

    protected Exercice(Parcel in) {
        id = in.readLong();
        theme = in.readString();
        sous_theme = in.readString();
        date = in.readString();
        resultat = in.readString();
        pseudo = in.readString();
    }

    public static final Creator<Exercice> CREATOR = new Creator<Exercice>() {
        @Override
        public Exercice createFromParcel(Parcel in) {
            return new Exercice(in);
        }

        @Override
        public Exercice[] newArray(int size) {
            return new Exercice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(theme);
        parcel.writeString(sous_theme);
        parcel.writeString(date);
        parcel.writeString(resultat);
        parcel.writeString(pseudo);
    }
}
