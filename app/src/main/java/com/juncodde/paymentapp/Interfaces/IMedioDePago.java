package com.juncodde.paymentapp.Interfaces;

import android.app.Activity;

import com.juncodde.paymentapp.Adapter.PaymentMethodsAdapter;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

import java.util.List;

public interface IMedioDePago {

    interface View{
        void getListPM(List<PaymentMethods> ListPM);
        void showError(String err);
    }

    interface Presenter{
        void getListPM();
        void setListPM(List<PaymentMethods> ListPM);
        void guardarPM(PaymentMethodsAdapter adapter, Activity activity, List<PaymentMethods> ListPM);
        void showError(String err);
    }

    interface Model{
        void setListPM();
        void guardarPM(PaymentMethodsAdapter adapter, Activity activity, List<PaymentMethods> ListPM);
    }

}
