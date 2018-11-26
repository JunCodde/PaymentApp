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
import com.juncodde.paymentapp.RestApi.model.CardIssuers;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardIssuersAdapter  extends RecyclerView.Adapter<CardIssuersAdapter.CardIssuersViewHolder>{

    List<CardIssuers> listaCardIssuers;
    Activity a;

    public int idSelected = -1;

    ManageSharedPreferences manageSharedPreferences;

    public CardIssuersAdapter(List<CardIssuers> listaPagos , Activity a) {
        this.listaCardIssuers = listaPagos;
        this.a = a;
    }

    @NonNull
    @Override
    public CardIssuersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_payment_methods, viewGroup, false);
        CardIssuersAdapter.CardIssuersViewHolder pvh = new CardIssuersAdapter.CardIssuersViewHolder(v);

        manageSharedPreferences = new ManageSharedPreferences(a);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardIssuersViewHolder holder, final int i) {

        CardIssuers cardIssuers = listaCardIssuers.get(i);

        holder.tv_medioPago.setText(cardIssuers.getName());

        //Set image from URL Piscasso
        Picasso.get().load(cardIssuers.getSecure_thumbnail())
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
        return listaCardIssuers.size();
    }

    public static class CardIssuersViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout Rly_card;
        ImageView img_medioPago;
        TextView tv_medioPago;
        CardView cv_selected;

        public CardIssuersViewHolder(@NonNull View itemView) {
            super(itemView);

            Rly_card = (RelativeLayout) itemView.findViewById(R.id.Rly_card);
            img_medioPago = (ImageView) itemView.findViewById(R.id.img_medioPago);
            tv_medioPago = (TextView) itemView.findViewById(R.id.tv_medioPago);
            cv_selected = (CardView) itemView.findViewById(R.id.cv_selected);

        }
    }
}
