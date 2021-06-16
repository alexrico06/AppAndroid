package com.example.Madridizate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ReservasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);


        HiloCliente hilo = new HiloCliente(16,User.getEmail());
        hilo.start();

        RecyclerView listaplazas = findViewById(R.id.reservas);
        listaplazas.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaplazas.setLayoutManager(layoutManager);

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String[] plaza: hilo.listaReservas) {
            System.out.println("id"+ plaza[0] +"dia"+plaza[1]);
        }

        RecyclerView.Adapter adaptadorReservasUser = new AdaptadorReservasUser(hilo.listaReservas);
        listaplazas.setAdapter(adaptadorReservasUser);
    }


}