package com.example.gilbertosoares.cantinus3;

import java.util.ArrayList;

/**
 * Created by Heraclito on 08/11/2017.
 */

public class SelecaoMenu {
    int quantidade;
    int id;
    Double ValorItemTotal;
    ArrayList<String> opcoes;


    public SelecaoMenu() {
        quantidade=0;
        id=0;
        ValorItemTotal=0.0;
        opcoes=new ArrayList<String>();
    }

    public void add(int quant,int idN, double valorFinal, ArrayList<String> escolhidas){
        quantidade=quant;
        id=idN;
        ValorItemTotal=valorFinal;
        opcoes=escolhidas;
    }

}
