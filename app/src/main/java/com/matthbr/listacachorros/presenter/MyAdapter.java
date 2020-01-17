package com.matthbr.listacachorros.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matthbr.listacachorros.R;
import com.matthbr.listacachorros.model.Raca;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Raca> listaRacas;

    public void setListaRacas(ArrayList<Raca> listaRacas) {
        this.listaRacas = listaRacas;
    }

    public MyAdapter(ArrayList<Raca> racas){
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lblRaca;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lblRaca = itemView.findViewById(R.id.lblNomeRaca);

        }
    }


}
