package com.example.josseraj_ecole_des_loustics.classes.operations;

public class Addition extends Operation {

    public Addition(int operande1, int operande2){
        super(operande1,operande2);
    }

    @Override
    public int getResultat() {
        return getOperande1()+getOperande2();
    }
}
