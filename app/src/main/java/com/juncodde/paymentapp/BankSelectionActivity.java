package com.juncodde.paymentapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juncodde.paymentapp.Adapter.CardIssuersAdapter;
import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.RestApi.Adapter.RestAPIAdapter;
import com.juncodde.paymentapp.RestApi.Endpoints;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankSelectionActivity extends AppCompatActivity {

    ManageSharedPreferences msp;
    List<CardIssuers> cardIssuers;
    boolean error = false;

    private LinearLayout Lly_principal;
    private ProgressBar pb_cargarBancos;
    private Button btn_continuar;
    private RecyclerView rv_tiposBancos;
    private TextView tv_showError;

    private CardIssuersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection);

        btn_continuar = (Button) findViewById(R.id.btn_continuar);
        rv_tiposBancos = (RecyclerView) findViewById(R.id.rv_tiposBancos);
        pb_cargarBancos = (ProgressBar) findViewById(R.id.pb_cargarBancos);
        Lly_principal = (LinearLayout) findViewById(R.id.Lly_principal);
        tv_showError = (TextView) findViewById(R.id.tv_showError);

        showProgressBar(true);

        //Set title Color
        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
        if (actionBarToolbar != null) {
            actionBarToolbar.setTitleTextColor(Color.BLACK);
            getSupportActionBar().setTitle("Seleccion de banco");
        }

        msp = new ManageSharedPreferences(getApplicationContext());

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(error){
                    Intent i = new Intent(BankSelectionActivity.this, MedioDePagoActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    finish();
                }else{

                    if(adapter!=null) {

                        int idSelected = adapter.idSelected;

                        if(idSelected!=-1){

                            CardIssuers pm = cardIssuers.get(idSelected);
                            msp.guardarCardIssuers(pm);

                            Log.d("LELE", "onClick: " + pm.getId());

                            Intent i = new Intent(BankSelectionActivity.this, CuotasActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(i);
                            finish();

                        }else{
                            Toast.makeText(BankSelectionActivity.this, "Selecciona un metodo de pago", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        final Map<String, String> params = new HashMap<String, String>();
        params.put(Const_RestAPI.PAYMENT_METHOD_KEY, Const_RestAPI.API_KEY);
        params.put(Const_RestAPI.PAYMENT_METHOD_ID, msp.obtenerMetodoPago().getId());

        RestAPIAdapter adapter = new RestAPIAdapter();
        Endpoints e = adapter.establecerConexionMercadoPago();
        Call<List<CardIssuers>> response = e.getBankSelection(params);

        response.enqueue(new Callback<List<CardIssuers>>() {
            @Override
            public void onResponse(Call<List<CardIssuers>> call, Response<List<CardIssuers>> response) {
                cardIssuers = response.body();

                if(cardIssuers!=null) {
                    if (!cardIssuers.isEmpty()){

                        showProgressBar(false);
                        setRV();


                    }else{
                        showProgressBar(false);
                        error = true;
                        setRV();
                    }
                }else{
                    showProgressBar(false);
                    error = true;
                    setRV();
                }

            }

            @Override
            public void onFailure(Call<List<CardIssuers>> call, Throwable t) {
                showProgressBar(false);
                error = true;
                setRV();
            }
        });

    }

    private void showProgressBar(boolean show){
        if(show){
            pb_cargarBancos.setVisibility(View.VISIBLE);
            Lly_principal.setVisibility(View.GONE);
        }else{
            pb_cargarBancos.setVisibility(View.GONE);
            Lly_principal.setVisibility(View.VISIBLE);
        }
    }

    private void setRV(){
        rv_tiposBancos.setHasFixedSize(true);
        rv_tiposBancos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new CardIssuersAdapter(cardIssuers,this);
        rv_tiposBancos.setAdapter(adapter);

        checkError();
    }

    private void checkError(){
        if(error){
            tv_showError.setVisibility(View.VISIBLE);
            rv_tiposBancos.setVisibility(View.GONE);
            btn_continuar.setText("Volver");
        }else{
            tv_showError.setVisibility(View.GONE);
            rv_tiposBancos.setVisibility(View.VISIBLE);
            btn_continuar.setText("Continuar");
        }
    }
}
