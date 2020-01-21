package com.matthbr.listacachorros.model;

import java.util.ArrayList;

/**
 * Model da Raça, contendo seus atributos
 *
 * <p>É nesta raça que temos o nosso modelo de dados
 * da raça. Temos o nome da Raça e um array com suas sub-raças
 * além dos getters & setters </p>
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
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
