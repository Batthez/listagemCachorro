package com.matthbr.listacachorros.model;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RacaTest {

    /**
     * Teste do construtor um
     *
     * este teste serve para testar o primeiro construtor
     * que é responsável por adicionar um novo cachorro
     */
    @Test
    public void testConstructorBreedName(){
        String nomeRaca = "akita";
        ArrayList<String> aaaa = new ArrayList<>();
        aaaa.add("AAA");
        Raca raca = new Raca(nomeRaca,aaaa);

        Assert.assertEquals(nomeRaca,raca.getNomeRaca());


    }

    /**
     *
     * Teste do construtor com nome e array das subracas
     *
     *
     *
     */

    @Test
    public void testConstructorArrayName(){

        String nome = "terrier";
        ArrayList<String> subRaca = new ArrayList<>();
        subRaca.add("australian");

        Raca raca = new Raca(nome,subRaca);
        Assert.assertEquals(subRaca.get(0),raca.getSubRacas().get(0));

    }

}