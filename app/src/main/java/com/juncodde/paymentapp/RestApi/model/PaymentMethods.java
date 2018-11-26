package com.juncodde.paymentapp.RestApi.model;

public class PaymentMethods {

    private String id, secure_thumbnail, name, payment_type_id;

    public PaymentMethods(String id, String thumbnail, String name, String payment_type_id) {
        this.id = id;
        this.secure_thumbnail = thumbnail;
        this.name = name;
        this.payment_type_id = payment_type_id;
    }

    public PaymentMethods() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecure_thumbnail() {
        return secure_thumbnail;
    }

    public void setSecure_thumbnail(String secure_thumbnail) {
        this.secure_thumbnail = secure_thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }
}

