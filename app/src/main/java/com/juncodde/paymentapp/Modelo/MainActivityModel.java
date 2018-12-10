package com.juncodde.paymentapp.Modelo;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.juncodde.paymentapp.Interfaces.IMonto;
import com.juncodde.paymentapp.MainActivity;
import com.juncodde.paymentapp.MedioDePagoActivity;
import com.juncodde.paymentapp.POJO.PagoCompleto;
import com.juncodde.paymentapp.R;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;

public class MainActivityModel implements IMonto.Model {


    private IMonto.Presenter presenter;
    private Activity activity;
    private ManageSharedPreferences sharedPreferences;

    public MainActivityModel(IMonto.Presenter presenter, Activity activity) {
        this.presenter = presenter;
        this.activity = activity;
        sharedPreferences = new ManageSharedPreferences(activity);
    }

    @Override
    public void guardarMonto(String data) {

        if(data.isEmpty()){
            presenter.showError("Ingrese un monto valido");
        }else{
            int mount = Integer.valueOf(data);
            if(mount>=250000){
                //Monto menor a 250mil
                presenter.showError("El limite es $250.000");
            }else{

                Toast.makeText(activity, "win", Toast.LENGTH_SHORT).show();


                sharedPreferences.guardarMonto(data);

                Intent i = new Intent(activity, MedioDePagoActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                activity.startActivity(i);
                activity.finish();


            }
        }

    }

    @Override
    public void getPagoCompleto() {

        String monto = sharedPreferences.obtenerMonto();
        PaymentMethods pm = sharedPreferences.obtenerMetodoPago();
        CardIssuers ci = sharedPreferences.obtenerCardIssuers();
        Cuotas c = sharedPreferences.obtenerCuotas();

        PagoCompleto pc = new PagoCompleto(monto, "", ci, c, pm);

        presenter.getPagoCompleto(pc);


    }

}
