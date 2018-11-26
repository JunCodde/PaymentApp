package com.juncodde.paymentapp.RestApi.model;

import java.util.ArrayList;

public class CuotasResponse {

    ArrayList<Cuotas> cuotasResponse;

    public CuotasResponse(ArrayList<Cuotas> cuotasResponse) {
        this.cuotasResponse = cuotasResponse;
    }

    public CuotasResponse() {
    }

    public ArrayList<Cuotas> getCuotasResponse() {
        return cuotasResponse;
    }

    public void setCuotasResponse(ArrayList<Cuotas> cuotasResponse) {
        this.cuotasResponse = cuotasResponse;
    }
}
