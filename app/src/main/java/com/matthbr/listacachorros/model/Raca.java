package com.matthbr.listacachorros.model;

import java.util.ArrayList;

public class Raca {

    private String nomeRaca;
    private ArrayList<String> subRacas = new ArrayList<>();

    public Raca(String nomeRaca, ArrayList<String> subRacas){
        setNomeRaca(nomeRaca);
        setSubRacas(subRacas);
    }


    //GETTERS AND SETTERS
    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public ArrayList<String> getSubRacas(){
        return subRacas;
    }

    public void setSubRacas(ArrayList<String> subRacas) {
        this.subRacas = subRacas;
    }
}
