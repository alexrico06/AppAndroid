package com.example.Madridizate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GestionReservas extends AppCompatActivity {

    ArrayList<String[]> listaReservas = null;

    private TextView direccion;
    private TextView plaza;
    private TextView fechaReserva,horas;
    private TextView matricula;
    private Button button;
    String[] plazas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_reservas);

        direccion = findViewById(R.id.parking_direccion);
        plaza  = findViewById(R.id.plaza_parking);
        horas  = findViewById(R.id.horasReserva);
        matricula  = findViewById(R.id.matriculaReserva);
        fechaReserva = findViewById(R.id.fechaReserva);
        button = findViewById(R.id.cancelarReserva);

        HiloCliente hilo = new HiloCliente(16,User.getEmail());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        plazas= hilo.datosReserva;

        if(plazas != null) {
            System.out.println("id: " + plazas[0] + " dia: " + plazas[1]);
            direccion.setText(plazas[6]);
            fechaReserva.setText(plazas[1].substring(0, 10));
            horas.setText(plazas[2] + "-" + plazas[3]);
            plaza.setText(plazas[5]);
            matricula.setText(plazas[4]);
            button.setEnabled(true);
        }else{
            direccion.setText("");
            fechaReserva.setText("");
            horas.setText("");
            plaza.setText("");
            matricula.setText("");
            button.setEnabled(false);
        }
    }

    public void cancelarReserva(View v) {

        direccion.setText("");
        fechaReserva.setText("");
        horas.setText("");
        plaza.setText("");
        matricula.setText("");

        HiloCliente hilo = new HiloCliente(10, plazas[0]);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"RESERVA ELIMINADA",Toast.LENGTH_SHORT).show();
        button.setEnabled(false);

    }
}