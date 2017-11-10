package com.example.gilbertosoares.cantinus3;

import java.util.ArrayList;

/**
 * Created by Heraclito on 08/11/2017.
 */

public class Opcoes {
    ArrayList<String> opc;

    public Opcoes() {
        opc=new ArrayList<String>();
    }

    public ArrayList<String> getOpc() {
        return opc;
    }

    public void setOpc(ArrayList<String> opc) {
        this.opc = opc;
    }

    public void add(String entrada){
        opc.add(entrada);
    }
}
