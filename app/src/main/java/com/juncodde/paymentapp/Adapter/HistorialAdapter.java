package com.juncodde.paymentapp.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juncodde.paymentapp.Model.PagoCompleto;
import com.juncodde.paymentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialAdapterViewHolder>{

    private ArrayList<PagoCompleto> listaPagos;
    private Activity a;

    public int idSelected = -1;

    public HistorialAdapter(ArrayList<PagoCompleto> listaPagos , Activity a) {
        this.listaPagos = listaPagos;
        this.a = a;
    }

    @NonNull
    @Override
    public HistorialAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_historial, viewGroup, false);
        return new HistorialAdapter.HistorialAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialAdapterViewHolder holder, int i) {

        PagoCompleto pc = listaPagos.get(i);

        holder.tv_cuotas.setText(pc.getCuotas().getRecommended_message());
        holder.tv_monto.setText("$ " + pc.getMonto());
        holder.tv_fecha.setText(pc.getFechaHora());

        Picasso.get().load(pc.getPaymentMethods().getSecure_thumbnail())
                .into(holder.img_medioPago);

        Picasso.get().load(pc.getCardIssuers().getSecure_thumbnail())
                .into(holder.img_tipoBanco);

    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public static class HistorialAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tv_monto, tv_cuotas, tv_fecha;
        ImageView img_medioPago, img_tipoBanco;

        public HistorialAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_monto = (TextView) itemView.findViewById(R.id.tv_monto);
            tv_cuotas = (TextView) itemView.findViewById(R.id.tv_cuotas);
            tv_fecha = (TextView) itemView.findViewById(R.id.tv_fecha);

            img_medioPago = (ImageView) itemView.findViewById(R.id.img_medioPago);
            img_tipoBanco = (ImageView) itemView.findViewById(R.id.img_tipoBanco);

        }
    }
}
