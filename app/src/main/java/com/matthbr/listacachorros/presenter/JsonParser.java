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

import java.util.ArrayList;
import java.util.Iterator;


/**
 * <h3>Classe responsável pelas requisições à API <a href='dog.ceo'>Dog.ceo</a></h3>
 *
 * <p>Nesta classe temos as requisições da API para pegar a lista das raças e
 * suas sub-raças para depois mostrar na tela na classe do adapter.</p>
 *
 * @author Matheus Geiser <matheusgeiser@gmail.com>
 * @since 1.0.0
 *
 */
public class JsonParser {


    /**
     * Variável global da queue para realizar a requisição à API
     */
    private RequestQueue queue;

    /**
     * ArrayList de Raças que recebe as raças e sub-raças recebidas da API
     */
    private ArrayList<Raca> listRacas;

    /**
     * Variável de requisição do objeto JSON
     */
    private JsonObjectRequest request;

    /**
     * Variável que contém a url da requisição
     */
    private String urlRequest;

    /**
     * Variável que contém a url da imagem da ultima raça clicada
     */
    private String urlImg = "https://www.urbanarts.com.br/imagens/produtos/079326/0/Ampliada/pixel-dog.jpg";


    /**
     * <p>Método que através da requisição, pega os dados de raça e sub-raça</p>
     *
     * <p>Usando o contexto para criar o objeto que é responsável por gerenciar
     * a nossa fila de requisições.</p>
     *
     * <p>No JsonObjectRequest é realizada a chamada da API e logo abaixo no
     * onResponse, é captado o valor do objeto 'message' para depois
     * utilizar o 'Iterator<String> keys' que tem todas as chaves do objeto
     * 'message' que no caso seriam todas as raças. No while, capturamos
     * o vetor de sub-raça. Já no for, adicionamos as informações do JsonArray
     * no vetor String para depois adicionar tanto a raça(key) quanto o array
     * em um objeto. Como temos um objeto da Raça, adicionamos ele ao array
     * de raças que irá para o recyclerView.</p>
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param context em que é passado da activity
     * @param adapter para atualizar os dados do adapter para
     *                vizualizar os dados da lista
     */
    public void getInfos(Context context, final MyAdapter adapter){

        queue = Volley.newRequestQueue(context);
        listRacas = new ArrayList<>();

        String url= "https://dog.ceo/api/breeds/list/all";

        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject message = response.getJSONObject("message");
                    Iterator<String> keys = message.keys();
                    while(keys.hasNext()){

                        String key = keys.next();

                        if(message.get(key) instanceof JSONArray){
                            JSONArray racaArray = (JSONArray)message.get(key);

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

    /**
     * <p>Método que através da API retorna o link de uma imagem da raça informada</p>
     *
     * <p>A requisição retorna um JSON com uma key chamada 'message'
     * que contém o link da imagem da raça que foi passada como parâmetro.
     * Logo depois, é utilizado o picasso para setar a imagem puxada da API
     * dentro do imageView</p>
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com>
     * @since 1.0.0
     *
     * @param nomeRaca Nome da raça que irá ser pesquisada na API
     * @param context Contexto da aplicação para realizar a queue
     * @param imageView recebe o imageView da activity para atualizar a imagem
     *                  através da biblioteca Picasso
     */
    public void getImgRaca(String nomeRaca, final Context context, final ImageView imageView){

        urlRequest = "https://dog.ceo/api/breed/"+nomeRaca+"/images/random";
        queue = Volley.newRequestQueue(context);

        request = new JsonObjectRequest(Request.Method.GET, urlRequest, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    String urlImg = response.get("message").toString();
                    Picasso.get().load(urlImg).into(imageView);

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


    /**
     * <p>Método que retorna a variável global urlImg</p>
     *
     * <p>Esse getUrlImg serve para pegar variável da url, pois
     * esta variável contém o link da ultima raça clicada pelo
     * usuário.</p>
     *
     *
     * TODO  // Acredito que não funciona por questão de assincronicidade com a request
     *
     * @author Matheus Geiser <matheusgeiser@gmail.com/>
     * @since 1.0.0
     *
     * @return
     */
    @Deprecated
    public String getUrlImg(){
        return this.urlImg;
    }

    private void setUrlImg(String urlImg){this.urlImg = urlImg;}

}
