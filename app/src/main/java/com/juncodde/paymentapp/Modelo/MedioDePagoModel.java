package com.juncodde.paymentapp.Modelo;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.juncodde.paymentapp.Adapter.PaymentMethodsAdapter;
import com.juncodde.paymentapp.BankSelectionActivity;
import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.Interfaces.IMedioDePago;
import com.juncodde.paymentapp.MedioDePagoActivity;
import com.juncodde.paymentapp.RestApi.Adapter.RestAPIAdapter;
import com.juncodde.paymentapp.RestApi.Endpoints;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedioDePagoModel implements IMedioDePago.Model{

    private IMedioDePago.Presenter presenter;

    public MedioDePagoModel(IMedioDePago.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setListPM() {

        final Map<String, String> params = new HashMap<String, String>();
        params.put(Const_RestAPI.PAYMENT_METHOD_KEY, Const_RestAPI.API_KEY);

        RestAPIAdapter adapter = new RestAPIAdapter();
        Endpoints e = adapter.establecerConexionMercadoPago();
        Call<List<PaymentMethods>> response = e.getPayment_Methods(params);

        response.enqueue(new Callback<List<PaymentMethods>>() {
            @Override
            public void onResponse(Call<List<PaymentMethods>> call, Response<List<PaymentMethods>> response) {
                List<PaymentMethods> listaPagosCurrent = response.body();
                List<PaymentMethods> listaPagos = new ArrayList<>();

                if(listaPagosCurrent!=null){
                    if(!listaPagosCurrent.isEmpty()){

                        for (int i = 0; i < listaPagosCurrent.size(); i++) {

                            if(listaPagosCurrent.get(i).getPayment_type_id().equals("credit_card")){

                                listaPagos.add(listaPagosCurrent.get(i));

                            }

                        }

                        presenter.setListPM(listaPagos);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethods>> call, Throwable t) {

            }
        });

    }

    @Override
    public void guardarPM(PaymentMethodsAdapter adapter, Activity activity, List<PaymentMethods> ListPM) {

        ManageSharedPreferences msp = new ManageSharedPreferences(activity);

        int idSelected = adapter.idSelected;

        if(idSelected!=-1){

            PaymentMethods pm = ListPM.get(idSelected);
            msp.guardarMetodoPago(pm);

            Intent i = new Intent(activity, BankSelectionActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            activity.startActivity(i);
            activity.finish();

        }else{
            presenter.showError("Seleccione un metodo de pago");
        }

    }
}
