package com.example.josseraj_ecole_des_loustics.classes.operations;

public abstract class Operation {

    protected int operande1;
    protected int operande2;

    public Operation(int operande1, int operande2){
        this.operande1 = operande1;
        this.operande2 = operande2;
    }

    public int getOperande1() {
        return operande1;
    }

    public int getOperande2() {
        return operande2;
    }
    public abstract int getResultat();

}
