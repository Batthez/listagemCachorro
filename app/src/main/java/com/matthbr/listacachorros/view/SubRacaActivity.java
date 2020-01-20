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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubRacaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Raca> arrayRacas;
    private ArrayList<String> subRacas;

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

        jsonParser.getImgRaca(nomeRaca,this,imgRaca);

        //Adapter
        SubRacaAdapter adapter = new SubRacaAdapter(this.subRacas, this);

        //Configurar o RecyclerView


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


    }



}
