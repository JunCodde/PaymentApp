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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView tv_errorMonto, tv_monto, tv_medioPago, tv_tipoBanco, tv_Cuotas;
    private EditText et_monto;
    private Button btn_continuar, btn_nuevo_pago;

    private ImageView img_medioPago, img_tipoBanco;

    private LinearLayout Lly_pagoCompletado, Lly_monto;

    private ManageSharedPreferences sharedPreferences;

    boolean getPagoCompleted;


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

        showPagoSelected(getPagoCompleted);

       if(!getPagoCompleted){

           //Set title Color
           Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
           if (actionBarToolbar != null) {
               actionBarToolbar.setTitleTextColor(Color.BLACK);
               getSupportActionBar().setTitle("Ingresa el monto");
           }

           showError(false);

           btn_continuar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(et_monto.getText().toString().isEmpty()){
                       showError(true);
                       tv_errorMonto.setText(getResources().getText(R.string.error_monto));
                   }else{
                       int mount = Integer.valueOf(et_monto.getText().toString());
                       if(mount>=250000){
                           //Monto menor a 250mil
                           showError(true);
                           tv_errorMonto.setText("El limite es $250.000");
                       }else{

                           //go to next page TODO
                           sharedPreferences.guardarMonto(et_monto.getText().toString());

                           Intent i = new Intent(MainActivity.this, MedioDePagoActivity.class)
                                   .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                   .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                   .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                           startActivity(i);
                           finish();


                       }
                   }
               }
           });

       }else{

           //Set title Color
           Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
           if (actionBarToolbar != null) {
               actionBarToolbar.setTitleTextColor(Color.BLACK);
               getSupportActionBar().setTitle("Pago Completado");
           }

           String monto = sharedPreferences.obtenerMonto();
           PaymentMethods pm = sharedPreferences.obtenerMetodoPago();
           CardIssuers ci = sharedPreferences.obtenerCardIssuers();
           Cuotas c = sharedPreferences.obtenerCuotas();

           tv_monto.setText("$ " + monto);

           //poner metodo de pago
           Picasso.get().load(pm.getSecure_thumbnail())
                   .into(img_medioPago);
           tv_medioPago.setText(pm.getName());

           //poner tipo de banco
           Picasso.get().load(ci.getSecure_thumbnail())
                   .into(img_tipoBanco);
           tv_tipoBanco.setText(ci.getName());

           //poner cuotas
           tv_Cuotas.setText(c.getRecommended_message());


           btn_nuevo_pago.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   sharedPreferences.borrarUltimoPago();
                   showPagoSelected(false);
               }
           });

       }


    }

    public void showError(boolean isError){

        if(isError){
            tv_errorMonto.setVisibility(View.VISIBLE);
            et_monto.getBackground().mutate().setColorFilter(getResources()
                    .getColor(R.color.colorError), PorterDuff.Mode.SRC_ATOP);
        }else{

            tv_errorMonto.setVisibility(View.GONE);
            et_monto.getBackground().mutate().setColorFilter(getResources()
                    .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        }

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

            showError(false);
        }
    }
}
