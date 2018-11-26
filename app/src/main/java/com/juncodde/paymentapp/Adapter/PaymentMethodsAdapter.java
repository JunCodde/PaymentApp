package com.juncodde.paymentapp.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juncodde.paymentapp.R;
import com.juncodde.paymentapp.RestApi.model.PaymentMethods;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.PaymentMethodsViewHolder> {

    List<PaymentMethods> listaPagos;
    Activity a;

    public int idSelected = -1;

    ManageSharedPreferences manageSharedPreferences;


    public PaymentMethodsAdapter(List<PaymentMethods> listaPagos , Activity a) {
        this.listaPagos = listaPagos;
        this.a = a;
    }

    @NonNull
    @Override
    public PaymentMethodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_payment_methods, viewGroup, false);
        PaymentMethodsAdapter.PaymentMethodsViewHolder pvh = new PaymentMethodsAdapter.PaymentMethodsViewHolder(v);

        manageSharedPreferences = new ManageSharedPreferences(a);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsViewHolder holder, final int i) {

        PaymentMethods paymentMethods = listaPagos.get(i);

        holder.tv_medioPago.setText(paymentMethods.getName());

        //Set image from URL Piscasso
        Picasso.get().load(paymentMethods.getSecure_thumbnail())
//                .resize(98, 34)
                .into(holder.img_medioPago);

        if(idSelected==i){

            holder.cv_selected.setCardBackgroundColor(a.getResources().getColor(R.color.colorButton));


        }else{
            holder.cv_selected.setCardBackgroundColor(a.getResources().getColor(R.color.cardview_light_background));
        }

        holder.Rly_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idSelected = i;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public static class PaymentMethodsViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout Rly_card;
        ImageView img_medioPago;
        TextView tv_medioPago;
        CardView cv_selected;

        public PaymentMethodsViewHolder(@NonNull View itemView) {
            super(itemView);

            Rly_card = (RelativeLayout) itemView.findViewById(R.id.Rly_card);
            img_medioPago = (ImageView) itemView.findViewById(R.id.img_medioPago);
            tv_medioPago = (TextView) itemView.findViewById(R.id.tv_medioPago);
            cv_selected = (CardView) itemView.findViewById(R.id.cv_selected);

        }
    }

}
