package com.example.madridizate2;

import android.content.Intent;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(),ReservaParking.class);
                    i.putExtra("direccion", direccion.getText().toString());
                    itemView.getContext().startActivity(i);
                }
            });
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
        holder.direccion.setText(plaza[1]);

    }

    @Override
    public int getItemCount() {
        return listaPlazas.size();
    }

}
