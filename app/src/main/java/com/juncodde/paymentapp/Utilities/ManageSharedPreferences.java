package com.juncodde.paymentapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.juncodde.paymentapp.Constantes.Const_SharedPreferences;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

public class ManageSharedPreferences {

    Context c;

    public ManageSharedPreferences(Context c) {
        this.c = c;
    }

    public void guardarMonto(String montoPago){


        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.PAGO,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Const_SharedPreferences.PAGO_MONTO, montoPago);
        editor.commit();
        editor.apply();

    }



    public String obtenerMonto() {

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.PAGO, Context.MODE_PRIVATE);

        return prefs.getString(Const_SharedPreferences.PAGO_MONTO, "");
    }

    public void guardarCardIssuers(CardIssuers cardIssuers){

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CARD_ISSUERS,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Const_SharedPreferences.CARD_ISSUERS_ID, cardIssuers.getId());
        editor.putString(Const_SharedPreferences.CARD_ISSUERS_NAME, cardIssuers.getName());
        editor.putString(Const_SharedPreferences.CARD_ISSUERS_FOTO, cardIssuers.getSecure_thumbnail());
        editor.commit();
        editor.apply();

    }

    public CardIssuers obtenerCardIssuers(){

        CardIssuers ci = new CardIssuers();
        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CARD_ISSUERS,Context.MODE_PRIVATE);

        ci.setId(prefs.getString(Const_SharedPreferences.CARD_ISSUERS_ID, ""));
        ci.setName(prefs.getString(Const_SharedPreferences.CARD_ISSUERS_NAME, ""));
        ci.setSecure_thumbnail(prefs.getString(Const_SharedPreferences.CARD_ISSUERS_FOTO, ""));

        return ci;

    }

    public void guardarMetodoPago(PaymentMethods metodoPago){

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.PAGO_ID_METODO_PAGO,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_ID, metodoPago.getId());
        editor.putString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_NAME, metodoPago.getName());
        editor.putString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_FOTO, metodoPago.getSecure_thumbnail());
        editor.commit();
        editor.apply();

    }

    public PaymentMethods obtenerMetodoPago(){

        PaymentMethods pm = new PaymentMethods();

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.PAGO_ID_METODO_PAGO,Context.MODE_PRIVATE);

        pm.setName(prefs.getString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_NAME, ""));
        pm.setId(prefs.getString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_ID, ""));
        pm.setSecure_thumbnail(prefs.getString(Const_SharedPreferences.PAGO_ID_METODO_PAGO_FOTO, ""));

        return pm;
    }

    public void guardarCuotas(Cuotas cuotas){

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CUOTAS,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Const_SharedPreferences.CUOTAS_TOTAL_AMMOUNT, cuotas.getTotal_amount());
        editor.putString(Const_SharedPreferences.CUOTAS_INSTALLMENTS, cuotas.getInstallments());
        editor.putString(Const_SharedPreferences.CUOTAS_RECOMENDED, cuotas.getRecommended_message());
        editor.commit();
        editor.apply();

    }

    public Cuotas obtenerCuotas(){

        Cuotas cuotas = new Cuotas();

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CUOTAS,Context.MODE_PRIVATE);

        cuotas.setTotal_amount(prefs.getString(Const_SharedPreferences.CUOTAS_TOTAL_AMMOUNT, ""));
        cuotas.setInstallments(prefs.getString(Const_SharedPreferences.CUOTAS_INSTALLMENTS, ""));
        cuotas.setRecommended_message(prefs.getString(Const_SharedPreferences.CUOTAS_RECOMENDED, ""));

        return cuotas;
    }

    public void setPagoCompleted(boolean pago){
        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CUOTAS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(Const_SharedPreferences.PAGO_COMPLETED, pago);
        editor.commit();
        editor.apply();

    }

    public boolean getPagoCompleted(){

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.CUOTAS,Context.MODE_PRIVATE);

        return prefs.getBoolean(Const_SharedPreferences.PAGO_COMPLETED, false);

    }

    public void borrarUltimoPago(){

        SharedPreferences prefs = c.getSharedPreferences(Const_SharedPreferences.PAGO,Context.MODE_PRIVATE);
        SharedPreferences prefs1 = c.getSharedPreferences(Const_SharedPreferences.CARD_ISSUERS,Context.MODE_PRIVATE);
        SharedPreferences prefs2 = c.getSharedPreferences(Const_SharedPreferences.PAGO_ID_METODO_PAGO,Context.MODE_PRIVATE);
        SharedPreferences prefs3 = c.getSharedPreferences(Const_SharedPreferences.CUOTAS,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        SharedPreferences.Editor editor1 = prefs1.edit();
        SharedPreferences.Editor editor2 = prefs2.edit();
        SharedPreferences.Editor editor3 = prefs3.edit();
        editor.clear();
        editor.apply();
        editor1.clear();
        editor1.apply();
        editor2.clear();
        editor2.apply();
        editor3.clear();
        editor3.apply();

    }



}
