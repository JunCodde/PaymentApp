package com.juncodde.paymentapp.Presentador;

import android.app.Activity;

import com.juncodde.paymentapp.Interfaces.IMonto;
import com.juncodde.paymentapp.Modelo.MainActivityModel;
import com.juncodde.paymentapp.POJO.PagoCompleto;

public class MainActivitypresenter implements IMonto.Presenter {

    private IMonto.View view;
    private IMonto.Model model;
    private Activity activity;

    public MainActivitypresenter(IMonto.View view, Activity activity) {
        this.view = view;
        this.model = new MainActivityModel(this, activity);
        this.activity = activity;
    }

    @Override
    public void guardarMonto(String data) {

        model.guardarMonto(data);

    }

    @Override
    public void showPagoCompleto() {
     model.getPagoCompleto();
    }

    @Override
    public void getPagoCompleto(PagoCompleto pagoCompleto) {
        view.showPagoCompleto(pagoCompleto);
    }

    @Override
    public void showError(String err) {

        if(view!=null){
            view.ShowError(err);
        }

    }
}
