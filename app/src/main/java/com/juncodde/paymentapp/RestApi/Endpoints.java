package com.juncodde.paymentapp.RestApi;


import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.CuotasResponse;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Endpoints {

    @GET(Const_RestAPI.PAYMENT_METHODS)
    Call<List<PaymentMethods>> getPayment_Methods(@QueryMap Map<String, String> params);

    @GET(Const_RestAPI.CARD_ISSUERS)
    Call<List<CardIssuers>> getBankSelection(@QueryMap Map<String, String> params);

    @GET(Const_RestAPI.CUOTAS)
    Call<CuotasResponse> getCuotas(@QueryMap Map<String, String> params);



}
