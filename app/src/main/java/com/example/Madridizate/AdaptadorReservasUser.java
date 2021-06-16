package com.example.Madridizate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorReservasUser extends RecyclerView.Adapter<AdaptadorReservasUser.MyViewHolder> {

    ArrayList<String[]> listaReservas=null;

    public AdaptadorReservasUser(ArrayList<String[]> listaResultados) {
        this.listaReservas = listaResultados;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView direccion;
        private TextView plaza;
        private TextView fechaReserva,horas;
        private TextView matricula;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.parking_direccion);
            plaza  = itemView.findViewById(R.id.plaza_parking);
            horas  = itemView.findViewById(R.id.horasReserva);
            matricula  = itemView.findViewById(R.id.matriculaReserva);
            fechaReserva = itemView.findViewById(R.id.fechaReserva);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }
    }

    @NonNull
    @Override
    public AdaptadorReservasUser.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.elemento_reserva_user, parent, false);
        MyViewHolder mv = new MyViewHolder(v);

        return mv;    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorReservasUser.MyViewHolder holder, int position) {

        String[] plaza = listaReservas.get(position);
        holder.direccion.setText(plaza[6]);
        holder.fechaReserva.setText(plaza[1].substring(0,10));
        holder.horas.setText(plaza[2]+"-"+plaza[3]);
        holder.plaza.setText(plaza[5]);
        holder.matricula.setText(plaza[4]);

    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

}
