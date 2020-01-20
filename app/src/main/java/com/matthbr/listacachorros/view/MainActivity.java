package com.matthbr.listacachorros.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.matthbr.listacachorros.R;
import com.matthbr.listacachorros.model.Raca;
import com.matthbr.listacachorros.presenter.JsonParser;
import com.matthbr.listacachorros.presenter.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
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
}
