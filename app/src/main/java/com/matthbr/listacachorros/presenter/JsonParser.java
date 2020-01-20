package com.matthbr.listacachorros.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.matthbr.listacachorros.model.Raca;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {


    private RequestQueue queue;
    private ArrayList<Raca> listRacas;
    private JsonObjectRequest request;
    private String urlRequest;

    private String urlImg;

    public void getInfos(Context context, final MyAdapter adapter){

        queue = Volley.newRequestQueue(context);
        listRacas = new ArrayList<>();

        String url= "https://dog.ceo/api/breeds/list/all";

        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject listaResponse = response.getJSONObject("message");
                    Iterator<String> keys = listaResponse.keys();
                    while(keys.hasNext()){

                        String key = keys.next();

                        if(listaResponse.get(key) instanceof JSONArray){
                            JSONArray racaArray = (JSONArray)listaResponse.get(key);

                            ArrayList<String> subRaca = new ArrayList<>();

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

    }


    public void getImgRaca(String nomeRaca, Context context, final ImageView imageView){

        urlRequest = "https://dog.ceo/api/breed/"+nomeRaca+"/images/random";
        queue = Volley.newRequestQueue(context);
        urlImg = "";

        request = new JsonObjectRequest(Request.Method.GET, urlRequest, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    urlImg = response.get("message").toString();
                    setImg(imageView,urlImg);

                }catch(Exception e){
                    Log.e("Erro",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERRO","Erro ao procurar imagem");
            }
        });

        queue.add(request);

    }

    private void setImg(ImageView imgView, String urlImg){

        Picasso.get().load(urlImg).into(imgView);

    }



}
