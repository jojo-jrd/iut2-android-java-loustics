package com.example.josseraj_ecole_des_loustics.classes.operations;

import com.example.josseraj_ecole_des_loustics.classes.operations.Operation;

public class Multiplication extends Operation {

    public Multiplication(int operande1, int operande2){
        super(operande1,operande2);
    }

    @Override
    public int getResultat(){
        return operande1*operande2;
    }

}
