package com.matthbr.listacachorros.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matthbr.listacachorros.R;
import com.matthbr.listacachorros.model.Raca;
import com.matthbr.listacachorros.view.MainActivity;
import com.matthbr.listacachorros.view.SubRacaActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Raca> listaRacas;
    private Context mContext;

    public void setListaRacas(ArrayList<Raca> listaRacas) {
        this.listaRacas = listaRacas;
    }

    public MyAdapter(ArrayList<Raca> racas, Context context){
        this.mContext = context;
        this.listaRacas = racas;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout,parent,false);
        return new MyViewHolder(itemLista);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Raca raca = listaRacas.get(position);
        holder.lblRaca.setText(raca.getNomeRaca());

    }

    @Override
    public int getItemCount() {
        return listaRacas.size();
    }


    //ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lblRaca;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lblRaca = itemView.findViewById(R.id.lblNomeRaca);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Raca raca = listaRacas.get(getAdapterPosition());
            ArrayList<String> sRacas = raca.getSubRacas();
            String[] vetorzinhoZika = new String[sRacas.size()];
            Log.e("AAA",raca.getNomeRaca());
            for(int i = 0; i<sRacas.size(); i++){
                vetorzinhoZika[i] = sRacas.get(i);
            }

            Intent i = new Intent(mContext, SubRacaActivity.class);
            i.putExtra("vetorSRacas",vetorzinhoZika);
            i.putExtra("nRaca",raca.getNomeRaca());

            Log.e("SIZE",String.valueOf(sRacas.size()));

            if(sRacas.size()==0){
               i.putExtra("verif",true);
            }

            mContext.startActivity(i);

            Log.e("AAA",raca.getNomeRaca());

        }
    }


}
