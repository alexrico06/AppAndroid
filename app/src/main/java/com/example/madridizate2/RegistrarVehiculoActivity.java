package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistrarVehiculoActivity extends AppCompatActivity {

    String dni;
    String tipo = null;
    String[] datosV = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);

        Intent i = getIntent();
        dni = i.getStringExtra("DNI");
    }

    public void guardarVehiculo(View view) {

        System.out.println(dni);

        RadioButton moto = findViewById(R.id.radioButtonMoto);
        RadioButton coche = findViewById(R.id.radioButtonCoche);
        RadioButton furgoneta = findViewById(R.id.radioButtonFurgoneta);

        EditText matricula = findViewById(R.id.textMatricula);
        String textMatricula = matricula.getText().toString();

        EditText marca = findViewById(R.id.textMarca);
        String textMarca = marca.getText().toString();

        EditText modelo = findViewById(R.id.textModelo);
        String textModelo = modelo.getText().toString();

        if(moto.isChecked()){
            tipo="m";
        }else if(coche.isChecked()){
            tipo="c";
        }else if(furgoneta.isChecked()){
            tipo="f";
        }

        System.out.println(tipo);


        datosV[0] = textMatricula;
        datosV[1] = tipo;
        datosV[2] = textMarca;
        datosV[3] = textModelo;
        datosV[4] = dni;

        HiloCliente hilo = new HiloCliente(3,datosV);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}