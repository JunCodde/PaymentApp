package com.juncodde.paymentapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.juncodde.paymentapp.Adapter.HistorialAdapter;
import com.juncodde.paymentapp.BaseDatos.BaseDatos;
import com.juncodde.paymentapp.POJO.PagoCompleto;

import java.util.ArrayList;

public class HistorialPagosActivity extends AppCompatActivity {

    private LinearLayout LLy_principal, LLy_error;
    private RecyclerView rv_historialPagos;
    private Button btn_volver;

    ArrayList<PagoCompleto> pagos = new ArrayList<>();
    HistorialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pagos);

        //Set title Color
        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
        if (actionBarToolbar != null) {
            actionBarToolbar.setTitleTextColor(Color.BLACK);
            getSupportActionBar().setTitle("Historial de Pagos");
        }

        LLy_principal = (LinearLayout) findViewById(R.id.LLy_principal);
        LLy_error = (LinearLayout) findViewById(R.id.LLy_error);

        rv_historialPagos = (RecyclerView) findViewById(R.id.rv_historialPagos);

        btn_volver = (Button) findViewById(R.id.btn_volver);

        showError(false);

        setRV();

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistorialPagosActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                finish();
            }
        });

    }

    private void showError(boolean error){
        if(error){
            LLy_principal.setVisibility(View.GONE);
            LLy_error.setVisibility(View.VISIBLE);
        }else{
            LLy_principal.setVisibility(View.VISIBLE);
            LLy_error.setVisibility(View.GONE);
        }
    }

    private void setRV(){
        rv_historialPagos.setHasFixedSize(true);
        rv_historialPagos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getPagos();

        adapter = new HistorialAdapter(pagos,this);
        rv_historialPagos.setAdapter(adapter);
    }

    private void getPagos(){

        BaseDatos db = new BaseDatos(getApplicationContext());

        ArrayList<PagoCompleto> p = db.obtenerpagoCompleto();

        if(p.isEmpty()){
            showError(true);
        }else{
            showError(false);
            for (int i = p.size() -1; i > -1; i--) {
                pagos.add(p.get(i));
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(HistorialPagosActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(i);
        finish();
    }
}
