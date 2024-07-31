package com.example.josseraj_ecole_des_loustics.classes.utilitaire;

public class Utilitaire {

    // Fonction pour eval une expression et donné le résultat
    // Fonctionne uniquement si c'est de la forme xx/xx ou x/x
    // Fait par Josserand Jordan
    // Tests effectués :
    // Log.e("5/5",String.valueOf(eval("5/5"))); // 1.0
    // Log.e("5/10",String.valueOf(eval("5/10"))); // 0.5
    // Log.e("2/10",String.valueOf(eval("2/10"))); // 0.2
    // Log.e("4/5",String.valueOf(eval("4/5"))); // 0.8
    // Log.e("7.5/10",String.valueOf(eval("7.5/10"))); // 0.75
    public static float eval(String expression){
        String sTampon = String.valueOf(expression.charAt(0));
        String sTampon2 = "";
        for(int i = 1;i<expression.length();i++){

            if(expression.charAt(i)=='/'){
                for(int j = i+1;j<expression.length();j++){
                    sTampon2 += expression.charAt(j);
                }
                break;

            }else{
                sTampon += expression.charAt(i);
            }
        }
        return Float.valueOf(sTampon)/Float.valueOf(sTampon2);
    }
}
