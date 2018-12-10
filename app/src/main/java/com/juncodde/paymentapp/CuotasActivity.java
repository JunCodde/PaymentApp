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
import android.widget.Toast;

import com.google.gson.Gson;
import com.juncodde.paymentapp.Adapter.CuotasAdapter;
import com.juncodde.paymentapp.BaseDatos.BaseDatos;
import com.juncodde.paymentapp.Constantes.Const_RestAPI;
import com.juncodde.paymentapp.POJO.PagoCompleto;
import com.juncodde.paymentapp.RestApi.Adapter.RestAPIAdapter;
import com.juncodde.paymentapp.RestApi.Endpoints;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.CuotasResponse;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuotasActivity extends AppCompatActivity {

    ManageSharedPreferences msp;
    private LinearLayout Lly_principal;
    private RecyclerView rv_cuotas;
    private Button btn_continuar;
    private ProgressBar pb_cargarCuotas;

    ArrayList<Cuotas> cuotas = new ArrayList<>();
    CuotasAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuotas);

        pb_cargarCuotas = (ProgressBar) findViewById(R.id.pb_cargarCuotas);
        btn_continuar = (Button) findViewById(R.id.btn_continuar);
        rv_cuotas = (RecyclerView) findViewById(R.id.rv_cuotas);
        Lly_principal = (LinearLayout) findViewById(R.id.Lly_principal);

        //Set title Color
        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
        if (actionBarToolbar != null) {
            actionBarToolbar.setTitleTextColor(Color.BLACK);
            getSupportActionBar().setTitle("Selecciona las cuotas");
        }


        showProgressBar(true);

        msp = new ManageSharedPreferences(getApplicationContext());

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adapter!=null) {

                    int idSelected = adapter.idSelected;

                    if(idSelected!=-1){

                        Cuotas cuotaCurrent = cuotas.get(idSelected);
                        msp.guardarCuotas(cuotaCurrent);
                        msp.setPagoCompleted(true);


                        DateFormat df = new SimpleDateFormat("dd-MM-yy h:mm a");
                        String date = df.format(Calendar.getInstance().getTime());

                        PagoCompleto pc = new PagoCompleto( msp.obtenerMonto(),date ,
                                msp.obtenerCardIssuers(),cuotaCurrent,msp.obtenerMetodoPago());

                        BaseDatos db = new BaseDatos(getApplicationContext());
                        db.agregarPagoCompletado(pc);

                        Intent i = new Intent(CuotasActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(CuotasActivity.this, "Selecciona una opcion de cuota", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        final Map<String, String> params = new HashMap<String, String>();
        params.put(Const_RestAPI.PAYMENT_METHOD_KEY, Const_RestAPI.API_KEY);
        params.put(Const_RestAPI.AMOUNT, msp.obtenerMonto());
        params.put(Const_RestAPI.PAYMENT_METHOD_ID, msp.obtenerMetodoPago().getId());
        params.put(Const_RestAPI.ISSUER_ID, msp.obtenerCardIssuers().getId());

        RestAPIAdapter adapter = new RestAPIAdapter();
        Gson gson = adapter.contruirGson();
        Endpoints e = adapter.establecerConexionMercadoPagoCuotas(gson);
        Call<CuotasResponse> response = e.getCuotas(params);

        response.enqueue(new Callback<CuotasResponse>() {
            @Override
            public void onResponse(Call<CuotasResponse> call, Response<CuotasResponse> response) {

                if(response.body()!=null){
                    cuotas = response.body().getCuotasResponse();

                    showProgressBar(false);
                    setRV();
                }else{
                    Toast.makeText(CuotasActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    showProgressBar(false);
                    setRV();
                }

            }

            @Override
            public void onFailure(Call<CuotasResponse> call, Throwable t) {

                Log.d("LELE", "Error: " + t);

            }
        });

    }

    private void showProgressBar(boolean show){
        if(show){
            pb_cargarCuotas.setVisibility(View.VISIBLE);
            Lly_principal.setVisibility(View.GONE);
        }else{
            pb_cargarCuotas.setVisibility(View.GONE);
            Lly_principal.setVisibility(View.VISIBLE);
        }
    }

    private void setRV(){
        rv_cuotas.setHasFixedSize(true);
        rv_cuotas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new CuotasAdapter(cuotas,this);
        rv_cuotas.setAdapter(adapter);

    }



        @Override
    public void onBackPressed() {
        Intent i = new Intent(CuotasActivity.this, BankSelectionActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
