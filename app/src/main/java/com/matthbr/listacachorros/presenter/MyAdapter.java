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
import com.matthbr.listacachorros.view.SubRacaActivity;

import java.util.ArrayList;

/**
 * Classe responsável pela configuração do adapter do RecyclerView
 *
 * <p>Nesta classe temos todas as configurações vindas do RecyclerView
 * Adapter para que possamos fazer o que quisermos no recyclerView.</p>
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Raca> listaRacas;
    private Context mContext;

    /**
     * Método que atualiza o array com as raças pesquisadas da API
     * @param listaRacas que é recebido da classe JsonParser
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     */
    public void setListaRacas(ArrayList<Raca> listaRacas) {
        this.listaRacas = listaRacas;
    }

    /**
     * Construtor da Classe
     * @param racas
     * @param context
     */
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


    /**
     * Classe que tem os atributos dos itens do recyclerView
     *
     * No OnClick pegamos o objeto da raça clicada, logo após
     * pegamos o array de sub-raças deste objeto para depois colocá-lo
     * em um vetor para passar por intent.
     * É realizada uma verificação, se o array das sub-raças tiver um tamanho
     * igual a 0, ele irá enviar mais uma informação no intent da outra classe
     * que serve para saber se a raça tem ou não uma ou mais sub-raças.
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lblRaca;

        /**
         * Construtor da classe MyViewHolder
         *
         * @author Matheus Geiser <matheusgeiser@gmail.com>
         * @since 1.0.0
         *
         * @param itemView que é um item, utilizado para conseguir fazer
         *                 interações com esta view (no meu caso, onClick)
         */
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


            if(sRacas.size()==0){
               i.putExtra("verif",true);
            }

            mContext.startActivity(i);

            Log.e("AAA",raca.getNomeRaca());

        }
    }


}
