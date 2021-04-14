package com.example.madridizate2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    ArrayList<String[]> listaPlazas=null;

    public Adaptador(ArrayList<String[]> listaResultados) {
        this.listaPlazas = listaResultados;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView direccion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.parking_direccion);
        }
    }

    @NonNull
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.elemento, parent, false);
        MyViewHolder mv = new MyViewHolder(v);

        return mv;    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyViewHolder holder, int position) {

        String[] plaza = listaPlazas.get(position);
        holder.direccion.setText(plaza[0]+"-"+plaza[1]+"-"+plaza[2]);

    }

    @Override
    public int getItemCount() {
        return listaPlazas.size();
    }

}
