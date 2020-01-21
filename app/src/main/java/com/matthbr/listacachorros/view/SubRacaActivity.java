package com.matthbr.listacachorros.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matthbr.listacachorros.R;
import com.matthbr.listacachorros.model.Raca;
import com.matthbr.listacachorros.presenter.JsonParser;
import com.matthbr.listacachorros.presenter.SubRacaAdapter;

import java.util.ArrayList;

/**
 *
 *  <h1>Activity que contém imagem da raça e lista de sub-raças</h1>
 *
 *
 * <p>Foi utilizado o RecyclerView para exibir a lista
 * Nesta classe temos dentro do onCreate temos todas as
 * definições e ações da segunda tela.</p>
 *
 * <p>Através do Intent, peguei as informações de Raça,
 * sub-raça e uma verificação para saber se a informação
 * de sub-raças é existente ou não.</p>
 *
 *<p>Também tem um método que salva a url da ultima raça
 * selecionada.</p>
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 */

public class SubRacaActivity extends AppCompatActivity {

    /**
     * Variável global do recyclerView
     */
    private RecyclerView recyclerView;

    /*ArrayList com as raças*/
    private ArrayList<Raca> arrayRacas;

    /*ArrayList String com as sub raças de uma denominada raça*/
    private ArrayList<String> subRacas;

    private String urlImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_raca);

        recyclerView = findViewById(R.id.rSubRaca);

        subRacas = new ArrayList<>();

        //Intent
        Intent intent = getIntent();
        String[] subRacas = intent.getStringArrayExtra("vetorSRacas");
        String nomeRaca = intent.getStringExtra("nRaca");
        boolean verif = intent.getBooleanExtra("verif",false);

        for(String ss : subRacas){
            this.subRacas.add(ss);
        }

        //TextView
        TextView lblRaca = findViewById(R.id.lblDogName);
        lblRaca.setText(nomeRaca);

        //JSonParser
        JsonParser jsonParser = new JsonParser();

        //ImageView
        ImageView imgRaca = findViewById(R.id.imgR);

        //SharedPreferences
        SharedPreferences preferences = getSharedPreferences("dog_preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        jsonParser.getImgRaca(nomeRaca,this,imgRaca);

        //Adapter
        SubRacaAdapter adapter = new SubRacaAdapter(this.subRacas, this);

        //Configurar o RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        saveImgUrl(jsonParser.getUrlImg());

    }


    /**
     *
     * Método que pega a url da ultima imagem e salva no shared preferences
     *
     * Dentro da classe JsonParser construí um método chamado getUrlImg()
     * que puxa uma variável privada da classe que contém a imagem do
     * ultimo cachorro clicado
     * A partir disso, é salvado em um SharedPreferences essa url para ser
     * chamada na MainActivity
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     */
    public void saveImgUrl(String url){

        //SharedPreferences
        SharedPreferences preferences = getSharedPreferences("dog_preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("url",url);
        editor.apply();
    }



}
