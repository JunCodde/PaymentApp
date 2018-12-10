package com.juncodde.paymentapp.POJO;

import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

public class PagoCompleto {

    private int id;
    private String monto, fechaHora;
    private CardIssuers cardIssuers;
    private Cuotas cuotas;
    private PaymentMethods paymentMethods;

    public PagoCompleto( String monto, String fechaHora, CardIssuers cardIssuers, Cuotas cuotas, PaymentMethods paymentMethods) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cardIssuers = cardIssuers;
        this.cuotas = cuotas;
        this.paymentMethods = paymentMethods;
    }

    public PagoCompleto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public CardIssuers getCardIssuers() {
        return cardIssuers;
    }

    public void setCardIssuers(CardIssuers cardIssuers) {
        this.cardIssuers = cardIssuers;
    }

    public Cuotas getCuotas() {
        return cuotas;
    }

    public void setCuotas(Cuotas cuotas) {
        this.cuotas = cuotas;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
