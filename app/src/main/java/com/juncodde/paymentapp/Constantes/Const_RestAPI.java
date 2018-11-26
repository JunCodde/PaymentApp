package com.juncodde.paymentapp.Constantes;

public class Const_RestAPI {

    public static final String ROOT_BASE = "https://api.mercadopago.com/";
    public static final String VERSION = "v1/";
    public static final String ROOT = ROOT_BASE + VERSION;


    public static final String API_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88";

    public static final String PAYMENT_METHODS = "payment_methods";

    public static final String CARD_ISSUERS = PAYMENT_METHODS + "/card_issuers";

    public static final String PAYMENT_METHOD_KEY = "public_key";
    public static final String PAYMENT_METHOD_ID = "payment_method_id";

    public static final String CUOTAS_INSTALLMENTS = "/installments";
    public static final String CUOTAS = PAYMENT_METHODS + CUOTAS_INSTALLMENTS;


    public static final String AMOUNT = "amount";
    public static final String ISSUER_ID = "issuer.id";
}

