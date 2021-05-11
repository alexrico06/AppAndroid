package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegistrarVehiculoActivity extends AppCompatActivity {

    String[] datosV = new String[5];
    EditText matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);

        String correo;
        correo = User.getEmail();

        //consultar todos los vehiculos del usuario
        HiloCliente hilo = new HiloCliente(2,correo);

        ArrayList<String> lista = new ArrayList<String>();
    }

    public void insertarVehiculo(View view) {

        Button insertar = findViewById(R.id.buttonInsertarVehiculo);
        insertar.setVisibility(View.INVISIBLE);

        Button guardar = findViewById(R.id.buttonGuardarVehiculo);
        guardar.setVisibility(View.VISIBLE);

        EditText alias = findViewById(R.id.textAlias);
        alias.setVisibility(View.VISIBLE);

        Spinner spinner = findViewById(R.id.planets_spinner);
        spinner.setVisibility(View.GONE);
    }

    public void guardarVehiculo(View view) {

        System.out.println(datosV[4]);

        RadioButton moto = findViewById(R.id.radioButtonMoto);
        RadioButton coche = findViewById(R.id.radioButtonCoche);
        RadioButton furgoneta = findViewById(R.id.radioButtonFurgoneta);

        matricula = findViewById(R.id.textMatricula);
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


    public void eliminarVehiculo(View view){

        matricula = findViewById(R.id.textMatricula);

        //eliminar vehiculo según la matricula
        HiloCliente hilo = new HiloCliente(3,matricula.getText().toString());
        hilo.start();

    }

}