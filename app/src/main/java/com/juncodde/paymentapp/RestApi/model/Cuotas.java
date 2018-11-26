package com.juncodde.paymentapp.RestApi.model;

public class Cuotas {

    String recommended_message, installments, total_amount;

    public Cuotas(String recommended_message, String installments, String total_amount) {
        this.recommended_message = recommended_message;
        this.installments = installments;
        this.total_amount = total_amount;
    }

    public Cuotas() {
    }

    public String getRecommended_message() {
        return recommended_message;
    }

    public void setRecommended_message(String recommended_message) {
        this.recommended_message = recommended_message;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}
