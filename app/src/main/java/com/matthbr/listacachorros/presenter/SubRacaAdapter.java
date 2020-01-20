package com.matthbr.listacachorros.presenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matthbr.listacachorros.R;

import java.util.ArrayList;

public class SubRacaAdapter extends RecyclerView.Adapter<SubRacaAdapter.MyViewHolder> {

    private ArrayList<String> listaSubRacas;
    private Context context;


    public SubRacaAdapter(ArrayList<String> listaSubRacas, Context context){
        this.context = context;
        this.listaSubRacas=listaSubRacas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_raca_adapter,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String subRacas = listaSubRacas.get(position);
        Log.e("AA",subRacas);
        holder.nSubRaca.setText(subRacas);

    }

    @Override
    public int getItemCount() {
        return listaSubRacas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nSubRaca;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nSubRaca = itemView.findViewById(R.id.lblSRaca);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
