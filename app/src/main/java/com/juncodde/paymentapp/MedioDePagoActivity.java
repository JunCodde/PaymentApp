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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juncodde.paymentapp.Adapter.PaymentMethodsAdapter;
import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.Interfaces.IMedioDePago;
import com.juncodde.paymentapp.Presentador.MedioDePagoPresenter;
import com.juncodde.paymentapp.RestApi.Adapter.RestAPIAdapter;
import com.juncodde.paymentapp.RestApi.Endpoints;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedioDePagoActivity extends AppCompatActivity implements IMedioDePago.View {

    ManageSharedPreferences msp;

    private LinearLayout Lly_principal;
    private ProgressBar pb_cargarMetodos;
    private Button btn_continuar;
    private RecyclerView rv_metodoDePago;

    private List<PaymentMethods> listaPagos = new ArrayList<>();
    private PaymentMethodsAdapter adapter;

    private MedioDePagoPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio_de_pago);

        msp = new ManageSharedPreferences(getApplicationContext());

        btn_continuar = (Button) findViewById(R.id.btn_continuar);
        rv_metodoDePago = (RecyclerView) findViewById(R.id.rv_metodoDePago);
        pb_cargarMetodos = (ProgressBar) findViewById(R.id.pb_cargarMetodos);
        Lly_principal = (LinearLayout) findViewById(R.id.Lly_principal);

        presenter = new MedioDePagoPresenter(this);

        //Set title Color
        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
        if (actionBarToolbar != null) {
            actionBarToolbar.setTitleTextColor(Color.BLACK);
            getSupportActionBar().setTitle("Medio de pago");
        }

        showProgressBar(true);

        presenter.getListPM();

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null){

                    presenter.guardarPM(adapter, MedioDePagoActivity.this, listaPagos);

                }
            }
        });

    }

    private void showProgressBar(boolean show){
        if(show){
            pb_cargarMetodos.setVisibility(View.VISIBLE);
            Lly_principal.setVisibility(View.GONE);
        }else{
            pb_cargarMetodos.setVisibility(View.GONE);
            Lly_principal.setVisibility(View.VISIBLE);
        }
    }

    private void setRV(){
        rv_metodoDePago.setHasFixedSize(true);
        rv_metodoDePago.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new PaymentMethodsAdapter(listaPagos,this);
        rv_metodoDePago.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MedioDePagoActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(i);
        finish();
    }

    @Override
    public void getListPM(List<PaymentMethods> ListPM) {

        Toast.makeText(this, "" + ListPM.size(), Toast.LENGTH_SHORT).show();

        listaPagos = ListPM;

        showProgressBar(false);
        setRV();
    }

    @Override
    public void showError(String err) {
        Toast.makeText(MedioDePagoActivity.this, err, Toast.LENGTH_SHORT).show();
    }
}
