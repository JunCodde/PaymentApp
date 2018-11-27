package com.juncodde.paymentapp.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juncodde.paymentapp.Constantes.Const_BaseDatos;
import com.juncodde.paymentapp.Model.PagoCompleto;
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = Const_BaseDatos.DB_VERSION;
    public static final String DATABASE_NAME = Const_BaseDatos.DB_NAME;
    Context context;

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_QUERY = "create table "       + Const_BaseDatos.DB_TABLE_NAME + " (" +
                Const_BaseDatos.DB_TABLE_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Const_BaseDatos.DB_MONTO            + " TEXT, " +
                Const_BaseDatos.DB_FECHA            + " TEXT, " +
                Const_BaseDatos.DB_C_I_ID           + " TEXT, " +
                Const_BaseDatos.DB_C_I_NAME         + " TEXT, " +
                Const_BaseDatos.DB_C_I_FOTO         + " TEXT, " +
                Const_BaseDatos.DB_CUOTAS_MESSAGE   + " TEXT, " +
                Const_BaseDatos.DB_CUOTAS_INSTALLMENTS+ " TEXT, " +
                Const_BaseDatos.DB_CUOTAS_TOTAL     + " TEXT, " +
                Const_BaseDatos.DB_P_M_ID           + " TEXT, " +
                Const_BaseDatos.DB_P_M_FOTO         + " TEXT, " +
                Const_BaseDatos.DB_P_M_NAME         + " TEXT, " +
                Const_BaseDatos.DB_P_M_TIPO_ID      + " TEXT)";

        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_IF = "DROP TABLE IF EXISTS '" + Const_BaseDatos.DB_TABLE_NAME + "'" ;
        db.execSQL(DROP_IF);

    }

    public void agregarPagoCompletado(PagoCompleto pm){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Const_BaseDatos.DB_MONTO, pm.getMonto());
        values.put(Const_BaseDatos.DB_FECHA, pm.getFechaHora());
        values.put(Const_BaseDatos.DB_C_I_ID, pm.getCardIssuers().getId());
        values.put(Const_BaseDatos.DB_C_I_NAME, pm.getCardIssuers().getName());
        values.put(Const_BaseDatos.DB_C_I_FOTO, pm.getCardIssuers().getSecure_thumbnail());
        values.put(Const_BaseDatos.DB_CUOTAS_MESSAGE, pm.getCuotas().getRecommended_message());
        values.put(Const_BaseDatos.DB_CUOTAS_INSTALLMENTS, pm.getCuotas().getInstallments());
        values.put(Const_BaseDatos.DB_CUOTAS_TOTAL, pm.getCuotas().getTotal_amount());
        values.put(Const_BaseDatos.DB_P_M_ID, pm.getPaymentMethods().getId());
        values.put(Const_BaseDatos.DB_P_M_FOTO, pm.getPaymentMethods().getSecure_thumbnail());
        values.put(Const_BaseDatos.DB_P_M_NAME, pm.getPaymentMethods().getName());
        values.put(Const_BaseDatos.DB_P_M_TIPO_ID, pm.getPaymentMethods().getPayment_type_id());

        db.insert(Const_BaseDatos.DB_TABLE_NAME, null,values);
        db.close();

    }

    public ArrayList<PagoCompleto> obtenerpagoCompleto(){

        ArrayList<PagoCompleto> pagos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(" SELECT * FROM " + Const_BaseDatos.DB_TABLE_NAME , null);

        if (c.moveToFirst()) {
            do {

                PagoCompleto o = new PagoCompleto();
                CardIssuers ci = new CardIssuers();
                PaymentMethods pm = new PaymentMethods();
                Cuotas cu = new Cuotas();

                o.setId(c.getInt(0));
                o.setMonto(c.getString(1));
                o.setFechaHora(c.getString(2));
                ci.setId(c.getString(3));
                ci.setName(c.getString(4));
                ci.setSecure_thumbnail(c.getString(5));
                cu.setRecommended_message(c.getString(6));
                cu.setInstallments(c.getString(7));
                cu.setTotal_amount(c.getString(8));
                pm.setId(c.getString(9));
                pm.setSecure_thumbnail(c.getString(10));
                pm.setName(c.getString(11));
                pm.setPayment_type_id(c.getString(12));
                o.setCardIssuers(ci);
                o.setCuotas(cu);
                o.setPaymentMethods(pm);

                pagos.add(o);

            } while(c.moveToNext());
        }

        return  pagos;
    }
}
