package com.juncodde.paymentapp.Presentador;

import android.app.Activity;

import com.juncodde.paymentapp.Adapter.PaymentMethodsAdapter;
import com.juncodde.paymentapp.Interfaces.IMedioDePago;
import com.juncodde.paymentapp.Modelo.MedioDePagoModel;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

import java.util.List;

public class MedioDePagoPresenter implements IMedioDePago.Presenter {

    private IMedioDePago.Model model;
    private IMedioDePago.View view;

    public MedioDePagoPresenter(IMedioDePago.View view) {
        this.view = view;
        this.model = new MedioDePagoModel(this);
    }

    @Override
    public void getListPM() {
            model.setListPM();
    }

    @Override
    public void setListPM(List<PaymentMethods> ListPM) {
        if(view!=null){
            view.getListPM(ListPM);
        }
    }

    @Override
    public void guardarPM(PaymentMethodsAdapter adapter, Activity activity, List<PaymentMethods> ListPM) {
        model.guardarPM(adapter, activity, ListPM);
    }

    @Override
    public void showError(String err) {
        if(view!=null){
            view.showError(err);
        }
    }
}
