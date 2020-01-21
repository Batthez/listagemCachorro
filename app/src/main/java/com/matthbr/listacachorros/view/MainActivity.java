package com.matthbr.listacachorros.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matthbr.listacachorros.R;
import com.matthbr.listacachorros.model.Raca;
import com.matthbr.listacachorros.presenter.JsonParser;
import com.matthbr.listacachorros.presenter.MyAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 *
 * Activity principal do aplicativo
 *
 * <p>Nesta activity temos a utilização do recycler view para fazer as
 * listas das raças e é onde todas as informações são setadas em seus
 * respectivos componentes da Activity</p>
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class MainActivity extends AppCompatActivity {

    /**
     * RecyclerView
     */
    private RecyclerView recyclerView;
    /**
     * ArrayList de Raças para colocar no adapter do RecyclerView
     */
    private ArrayList<Raca> arrayRacas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rPrincipal);

        arrayRacas = new ArrayList<>();

        //Configura MyAdapter
        MyAdapter adapter = new MyAdapter(arrayRacas,this);

        //Configura o Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        JsonParser jsonParser = new JsonParser();
        jsonParser.getInfos(this,adapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        //ImageView
        ImageView imgPrincipal = findViewById(R.id.imgPrincipal);

        //SharedPreferences
        SharedPreferences preferences = getSharedPreferences("dog_preferences",MODE_PRIVATE);

        //String que contém a url da imagem da ultima raça clicada
        String urlImg = preferences.getString("url","https://www.urbanarts.com.br/imagens/produtos/079326/0/Ampliada/pixel-dog.jpg");
        Log.e("AAAAAA",urlImg);
        Picasso.get().load(urlImg).into(imgPrincipal);

    }
}
