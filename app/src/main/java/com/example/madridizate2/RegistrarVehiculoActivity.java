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
        //User.setDni(i.getStringExtra("DNI"));
        datosV[4] = i.getStringExtra("DNI");
    }

    public void guardarVehiculo(View view) {

        System.out.println(datosV[4]);

        RadioButton moto = findViewById(R.id.radioButtonMoto);
        RadioButton coche = findViewById(R.id.radioButtonCoche);
        RadioButton furgoneta = findViewById(R.id.radioButtonFurgoneta);

        EditText matricula = findViewById(R.id.textMatricula);
        datosV[0] = matricula.getText().toString();

        EditText marca = findViewById(R.id.textMarca);
        datosV[2] = marca.getText().toString();

        EditText modelo = findViewById(R.id.textModelo);
        datosV[3] = modelo.getText().toString();

        if(moto.isChecked()){
            datosV[1]="m";
        }else if(coche.isChecked()){
            datosV[1]="c";
        }else if(furgoneta.isChecked()){
            datosV[1]="f";
        }

        System.out.println(datosV[1]);

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