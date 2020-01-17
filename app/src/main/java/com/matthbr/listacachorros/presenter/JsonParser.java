package com.matthbr.listacachorros.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.matthbr.listacachorros.model.Raca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {


    private RequestQueue queue;
    private ArrayList<String> subRaca;
    private ArrayList<Raca> listRacas;

    public ArrayList<Raca> getInfos(Context context, final MyAdapter adapter){

        queue = Volley.newRequestQueue(context);
        listRacas = new ArrayList<>();
        subRaca = new ArrayList<>();

        String url= "https://dog.ceo/api/breeds/list/all";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject listaResponse = response.getJSONObject("message");
                    Iterator<String> keys = listaResponse.keys();
                    while(keys.hasNext()){

                        String key = keys.next();

                        if(listaResponse.get(key) instanceof JSONArray){
                            JSONArray racaArray = (JSONArray)listaResponse.get(key);

                            for(int i = 0; i < racaArray.length(); i++){

                                subRaca.add(racaArray.get(i).toString());

                            }

                            Raca raca = new Raca(key,subRaca);
                            listRacas.add(raca);

                        }

                    }
                    adapter.setListaRacas(listRacas);
                    adapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Erro no Json", error.getMessage());
            }
        });

        queue.add(request);

        return listRacas;
    }





}
