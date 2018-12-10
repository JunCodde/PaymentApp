package com.juncodde.paymentapp.Interfaces;

import com.juncodde.paymentapp.POJO.PagoCompleto;

public interface IMonto {

    interface View{
        void ShowError ( String er);
        void showPagoCompleto(PagoCompleto pagoCompleto);
    }

    interface Presenter{
        void guardarMonto(String data);
        void showPagoCompleto();
        void getPagoCompleto(PagoCompleto pagoCompleto);
        void showError(String er);
    }

    interface Model{
        void guardarMonto(String data);
        void getPagoCompleto();
    }

}
