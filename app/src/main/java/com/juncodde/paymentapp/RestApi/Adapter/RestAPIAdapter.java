package com.juncodde.paymentapp.RestApi.Adapter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.Constantes.Const_SharedPreferences;
import com.juncodde.paymentapp.RestApi.Deserializador.CuotasDeserializador;
import com.juncodde.paymentapp.RestApi.Endpoints;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.CuotasResponse;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIAdapter {

    public Endpoints establecerConexionMercadoPago(){

        Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(Const_RestAPI.ROOT)
             .addConverterFactory(GsonConverterFactory.create())
             .build();


        return retrofit.create(Endpoints.class);
    }

    public Endpoints establecerConexionMercadoPagoCuotas(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const_RestAPI.ROOT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        return retrofit.create(Endpoints.class);
    }


    public Gson contruirGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CuotasResponse.class, new CuotasDeserializador());
        return gsonBuilder.create();
    }

}
