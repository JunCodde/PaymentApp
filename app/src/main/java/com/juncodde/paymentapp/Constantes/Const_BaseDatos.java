package com.juncodde.paymentapp.Constantes;

import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;

public class Const_BaseDatos {

    public static final String DB_NAME = "pago_completo";
    public static final int DB_VERSION = 1;

    public static final String DB_TABLE_NAME = "pago_completo";
    public static final String DB_TABLE_ID = "pago_completo_id";

    public static final String DB_MONTO = "monto";
    public static final String DB_FECHA = "fecha";

    public static final String DB_C_I_ID = "CardIssuers_ID";
    public static final String DB_C_I_NAME = "CardIssuers_name";
    public static final String DB_C_I_FOTO = "CardIssuers_foto";

    public static final String DB_CUOTAS_MESSAGE = "Cuotas_message";
    public static final String DB_CUOTAS_INSTALLMENTS = "Cuotas_installments";
    public static final String DB_CUOTAS_TOTAL = "Cuotas_total";

    public static final String DB_P_M_ID = "pm_id";
    public static final String DB_P_M_FOTO = "pm_foto";
    public static final String DB_P_M_NAME = "pm_name";
    public static final String DB_P_M_TIPO_ID = "pm_tipo_id";
}
