package com.example.Madridizate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class GestionReservas extends AppCompatActivity {

    ArrayList<String[]> listaReservas = null;

    private TextView direccion;
    private TextView plaza;
    private TextView fechaReserva,horas;
    private TextView matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_reservas);

        direccion = findViewById(R.id.parking_direccion);
        plaza  = findViewById(R.id.plaza_parking);
        horas  = findViewById(R.id.horasReserva);
        matricula  = findViewById(R.id.matriculaReserva);
        fechaReserva = findViewById(R.id.fechaReserva);

        HiloCliente hilo = new HiloCliente(16,User.getEmail());
        hilo.start();

        for (int i = 0; i < listaReservas.size(); i++) {
            String[] plazas = listaReservas.get(i);
            direccion.setText(plazas[6]);
            fechaReserva.setText(plazas[1].substring(0,10));
            horas.setText(plazas[2]+"-"+plazas[3]);
            plaza.setText(plazas[5]);
            matricula.setText(plazas[4]);
        }


        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String[] plazas: hilo.listaReservas) {
            System.out.println("id"+ plazas[0] +"dia"+plazas[1]);
        }

    }
}