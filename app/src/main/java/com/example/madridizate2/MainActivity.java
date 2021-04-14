package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.madridizate2.ui.home.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean bandera = false;
    String textCorreo = "lolaso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void pulsarRegistrar(View view) {
        Intent i = new Intent(this, RegistrarUserActivity.class);
        startActivity(i);
    }

    public void pulsarInicioSesion(View view) {

        EditText correo = findViewById(R.id.correo);
        textCorreo = correo.getText().toString();


        EditText contrasena = findViewById(R.id.contrasena);
        String textContrasena = contrasena.getText().toString();

        //Intent i = new Intent(this, Principal.class);
        //startActivity(i);

        HiloCliente hilo = new HiloCliente(1, textCorreo,textContrasena);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hilo.existe);

        if(hilo.existe){

            Intent i = new Intent(this, Principal.class);
            //i.putExtra("correo", textCorreo);
            startActivity(i);

        }else{
            System.out.println("no existe");
        }

    }

}