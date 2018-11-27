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
import com.juncodde.paymentapp.RestApi.model.Cuotas;
import com.juncodde.paymentapp.Utilities.ManageSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CuotasAdapter extends RecyclerView.Adapter<CuotasAdapter.CuotasAdapterViewHolder> {

    private ArrayList<Cuotas> listaCuotas;
    private Activity a;

    public int idSelected = -1;


    public CuotasAdapter(ArrayList<Cuotas> listaPagos , Activity a) {
        this.listaCuotas = listaPagos;
        this.a = a;
    }

    @NonNull
    @Override
    public CuotasAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_cuotas, viewGroup, false);
        CuotasAdapter.CuotasAdapterViewHolder pvh = new CuotasAdapter.CuotasAdapterViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CuotasAdapterViewHolder holder, final int i) {

        Cuotas cuotas = listaCuotas.get(i);

        holder.tv_Cuotas.setText(cuotas.getRecommended_message());

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
        return listaCuotas.size();
    }

    public static class CuotasAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tv_Cuotas;
        CardView cv_selected;
        RelativeLayout Rly_card;

        public CuotasAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Cuotas = (TextView) itemView.findViewById(R.id.tv_Cuotas);
            Rly_card = (RelativeLayout) itemView.findViewById(R.id.Rly_card);
            cv_selected = (CardView) itemView.findViewById(R.id.cv_selected);

        }
    }
}
