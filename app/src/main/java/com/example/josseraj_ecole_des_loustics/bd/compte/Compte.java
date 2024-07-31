package com.example.josseraj_ecole_des_loustics.bd.compte;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "compte")
public class Compte implements Parcelable {

    @PrimaryKey @NonNull
    private String pseudo;

    private String nom;
    private String prenom;
    private long avatar;

    public Compte(){}

    //=====================
    // GETTERS
    //=====================


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public long getAvatar() {
        return avatar;
    }

    //=====================
    // SETTERS
    //=====================

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAvatar(long avatar) {
        this.avatar = avatar;
    }

    //=====================
    // PARCELABLE
    //=====================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pseudo);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeLong(avatar);
    }

    protected Compte(Parcel in) {
        pseudo = in.readString();
        nom = in.readString();
        prenom = in.readString();
        avatar = in.readLong();
    }

    public static final Creator<Compte> CREATOR = new Creator<Compte>() {
        @Override
        public Compte createFromParcel(Parcel in) {
            return new Compte(in);
        }

        @Override
        public Compte[] newArray(int size) {
            return new Compte[size];
        }
    };
}
