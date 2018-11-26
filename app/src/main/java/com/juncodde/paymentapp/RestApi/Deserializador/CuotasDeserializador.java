package com.juncodde.paymentapp.RestApi.Deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.CuotasResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CuotasDeserializador implements JsonDeserializer<CuotasResponse> {

    @Override
    public CuotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Log.d("LELE", "deserialize 1");
        Gson gson = new Gson();
        CuotasResponse cuotas = new CuotasResponse(); //gson.fromJson(json, CuotasResponse.class);

        JsonArray array1 = json.getAsJsonArray();

        cuotas.setCuotasResponse(deserializarCoutoas(array1));


        return cuotas;

    }

    private ArrayList<Cuotas> deserializarCoutoas(JsonArray array){

        ArrayList<Cuotas> cuotasArrayList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {

            JsonObject payer_costs = array.get(i).getAsJsonObject();
            JsonArray payerCostsArray = payer_costs.getAsJsonArray("payer_costs").getAsJsonArray();

            for (int j = 0; j < payerCostsArray.size(); j++) {

                JsonObject payer_costsCurrent = payerCostsArray.get(j).getAsJsonObject();

                Cuotas cuotaCurrent = new Cuotas();

                cuotaCurrent.setRecommended_message(payer_costsCurrent.get("recommended_message").getAsString());
                cuotaCurrent.setInstallments(payer_costsCurrent.get("installments").getAsString());
                cuotaCurrent.setTotal_amount(payer_costsCurrent.get("total_amount").getAsString());
                cuotasArrayList.add(cuotaCurrent);

            }

        }

        return cuotasArrayList;
    }


}
