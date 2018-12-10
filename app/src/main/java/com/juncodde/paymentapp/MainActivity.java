package com.juncodde.paymentapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.juncodde.paymentapp.Interfaces.IMonto;
import com.juncodde.paymentapp.POJO.PagoCompleto;
import com.juncodde.paymentapp.Presentador.MainActivitypresenter;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements IMonto.View {

    //Hola mucho gusto, soy Junior Ricci, me encanta programar y mucho mas aprender
    //Espero que les guste mi app. un saludo, quedo a la espera de su respuesta :)

    private TextView tv_errorMonto, tv_monto, tv_medioPago, tv_tipoBanco, tv_Cuotas;
    private EditText et_monto;
    private Button btn_continuar, btn_nuevo_pago;

    private ImageView img_medioPago, img_tipoBanco;

    private LinearLayout Lly_pagoCompletado, Lly_monto;

    private ManageSharedPreferences sharedPreferences;
    boolean getPagoCompleted;

    private IMonto.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_errorMonto = (TextView) findViewById(R.id.tv_errorMonto);
        tv_monto = (TextView) findViewById(R.id.tv_monto);
        tv_medioPago = (TextView) findViewById(R.id.tv_medioPago);
        tv_tipoBanco = (TextView) findViewById(R.id.tv_tipoBanco);
        tv_Cuotas = (TextView) findViewById(R.id.tv_Cuotas);

        et_monto = (EditText) findViewById(R.id.et_monto);

        img_medioPago = (ImageView) findViewById(R.id.img_medioPago);
        img_tipoBanco = (ImageView) findViewById(R.id.img_tipoBanco);

        btn_continuar = (Button) findViewById(R.id.btn_continuar);
        btn_nuevo_pago = (Button) findViewById(R.id.btn_nuevo_pago);

        Lly_pagoCompletado = (LinearLayout) findViewById(R.id.Lly_pagoCompletado);
        Lly_monto = (LinearLayout) findViewById(R.id.Lly_monto);

        sharedPreferences = new ManageSharedPreferences(getApplicationContext());

        getPagoCompleted = sharedPreferences.getPagoCompleted();

        presenter = new MainActivitypresenter(this, MainActivity.this);

        showPagoSelected(getPagoCompleted);

       if(!getPagoCompleted){

           //Set title Color
           Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
           if (actionBarToolbar != null) {
               actionBarToolbar.setTitleTextColor(Color.BLACK);
               getSupportActionBar().setTitle("Ingresa el monto");
           }


       }else{

           //Set title Color
           Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
           if (actionBarToolbar != null) {
               actionBarToolbar.setTitleTextColor(Color.BLACK);
               getSupportActionBar().setTitle("Pago Completado");
           }

           presenter.showPagoCompleto();

       }

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.guardarMonto(et_monto.getText().toString());

            }
        });

        btn_nuevo_pago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.borrarUltimoPago();
                showPagoSelected(false);
            }
        });

    }


    public void showPagoSelected(boolean pago){
        if(pago){

            Lly_pagoCompletado.setVisibility(View.VISIBLE);
            Lly_monto.setVisibility(View.GONE);

        }else{
            Lly_pagoCompletado.setVisibility(View.GONE);
            Lly_monto.setVisibility(View.VISIBLE);

            //Set title Color
            Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
            if (actionBarToolbar != null) {
                actionBarToolbar.setTitleTextColor(Color.BLACK);
                getSupportActionBar().setTitle("Ingresa el monto");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.historial_pagos:

                Intent i = new Intent(MainActivity.this, HistorialPagosActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                finish();

                break;

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void ShowError(String err) {

        tv_errorMonto.setText(err);

        tv_errorMonto.setVisibility(View.VISIBLE);
        et_monto.getBackground().mutate().setColorFilter(getResources()
                .getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public void showPagoCompleto(PagoCompleto pc) {
        tv_monto.setText("$ " + pc.getMonto());

        //poner metodo de pago
        Picasso.get().load(pc.getPaymentMethods().getSecure_thumbnail())
                .into(img_medioPago);
        tv_medioPago.setText(pc.getPaymentMethods().getName());

        //poner tipo de banco
        Picasso.get().load(pc.getCardIssuers().getSecure_thumbnail())
                .into(img_tipoBanco);
        tv_tipoBanco.setText(pc.getCardIssuers().getName());

        //poner cuotas
        tv_Cuotas.setText(pc.getCuotas().getRecommended_message());

    }
}
