package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madridizate2.ui.home.HomeFragment;

import java.util.ArrayList;

public class RegistrarVehiculoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] datosV = new String[7];
    EditText matricula;
    EditText marca;
    EditText modelo;
    EditText tamano;
    Spinner spinner_alias;
    String ruta;

    RadioButton moto;
    RadioButton coche;
    RadioButton furgoneta;

    boolean bandera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vehiculo);

        moto = findViewById(R.id.radioButtonMoto);
        coche = findViewById(R.id.radioButtonCoche);
        furgoneta = findViewById(R.id.radioButtonFurgoneta);

        Intent intent = getIntent();
        ruta = intent.getStringExtra("registro");

        if(ruta.equals("A")){

            Button insertar = findViewById(R.id.buttonInsertarVehiculo);
            insertar.setVisibility(View.INVISIBLE);

            Button guardar = findViewById(R.id.buttonGuardarVehiculo);
            guardar.setVisibility(View.VISIBLE);

            EditText alias = findViewById(R.id.textAlias);
            alias.setVisibility(View.VISIBLE);

            Spinner spinner = findViewById(R.id.alias_spinner);
            spinner.setVisibility(View.GONE);

            moto.setEnabled(true);
            coche.setEnabled(true);
            furgoneta.setEnabled(true);

        }else{
            matricula = findViewById(R.id.textMatricula);
            matricula.setEnabled(false);

            marca = findViewById(R.id.textMarca);
            marca.setEnabled(false);

            modelo = findViewById(R.id.textModelo);
            modelo.setEnabled(false);

            tamano = findViewById(R.id.textTamaño);
            tamano.setEnabled(false);

            moto.setEnabled(false);
            coche.setEnabled(false);
            furgoneta.setEnabled(false);

            System.out.println(User.getEmail());

            //consultar todos los vehiculos del usuario
            HiloCliente hilo = new HiloCliente(8,User.getEmail());
            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinner_alias = (Spinner) findViewById(R.id.alias_spinner);

            ArrayList<String> listaAlias = hilo.listaApodos;

            spinner_alias.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaAlias));
            spinner_alias.setOnItemSelectedListener(this);

        }

    }

    public void insertarVehiculo(View view) {

        Button eliminar = findViewById(R.id.buttonElimarVehiculo);
        eliminar.setVisibility(View.INVISIBLE);

        Button insertar = findViewById(R.id.buttonInsertarVehiculo);
        insertar.setVisibility(View.INVISIBLE);

        Button guardar = findViewById(R.id.buttonGuardarVehiculo);
        guardar.setVisibility(View.VISIBLE);

        EditText matricula = findViewById(R.id.textMatricula);
        matricula.setText("");
        matricula.setEnabled(true);

        EditText marca = findViewById(R.id.textMarca);
        marca.setText("");
        marca.setEnabled(true);

        EditText modelo = findViewById(R.id.textModelo);
        modelo.setText("");
        modelo.setEnabled(true);

        EditText tamano = findViewById(R.id.textTamaño);
        tamano.setText("");
        tamano.setEnabled(true);

        EditText alias = findViewById(R.id.textAlias);
        alias.setText("");
        alias.setVisibility(View.VISIBLE);

        Spinner spinner = findViewById(R.id.alias_spinner);
        spinner.setVisibility(View.GONE);

        moto.setEnabled(true);
        coche.setEnabled(true);
        furgoneta.setEnabled(true);

    }

    public void guardarVehiculo(View view) {

        datosV[6]= User.getEmail();
        System.out.println(datosV[6]);

        matricula = findViewById(R.id.textMatricula);
        datosV[0] = matricula.getText().toString();

        EditText marca = findViewById(R.id.textMarca);
        datosV[2] = marca.getText().toString();

        EditText modelo = findViewById(R.id.textModelo);
        datosV[3] = modelo.getText().toString();

        EditText tamano = findViewById(R.id.textTamaño);
        datosV[4] = tamano.getText().toString().toUpperCase();

        EditText alias = findViewById(R.id.textAlias);
        datosV[5] = alias.getText().toString();

        if(moto.isChecked()){
            datosV[1]="m";
        }else if(coche.isChecked()){
            datosV[1]="c";
        }else if(furgoneta.isChecked()){
            datosV[1]="f";
        }else{
            datosV[1]="n";
            bandera = false;
        }

        if(!bandera) {
            if(!datosV[0].equals("")) {

                if(!datosV[2].equals("") && !datosV[3].equals("")) {

                    if (datosV[4].equals("S") || datosV[4].equals("M") || datosV[4].equals("L") || datosV[4].equals("s") || datosV[4].equals("m") || datosV[4].equals("l")) {

                        if (!datosV[5].equals("")) {
                            //insertar vehiculo
                            HiloCliente hilo = new HiloCliente(3, 1, datosV);
                            hilo.start();

                            try {
                                hilo.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (ruta.equals("A")) {

                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            } else {

                                Intent refresh = new Intent(this, RegistrarVehiculoActivity.class);
                                refresh.putExtra("registro", "B");
                                startActivity(refresh);
                                this.finish();

                            }
                        } else {
                            Toast.makeText(this, "ESCRIBA UN ALIAS PARA RECONOCER SU VEHICULO", Toast.LENGTH_LONG).show();
                        }

                    } else {

                        Toast.makeText(this, "ESCRIBA TAMANÑO: S,M,L", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(this, "ESCRIBA MARCA Y MODELO DEL VEHICULO", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this, "ESCRIBA LA MATRICULA DEL VEHICULO", Toast.LENGTH_LONG).show();
            }

        }else{

            Toast.makeText(this,"SELECCIONE TIPO VEHICULO",Toast.LENGTH_SHORT).show();

        }

    }


    public void eliminarVehiculo(View view){

        matricula = findViewById(R.id.textMatricula);

        System.out.println(matricula.getText().toString());

        //eliminar vehiculo según la matricula
        HiloCliente hilo = new HiloCliente(10,matricula.getText().toString());
        hilo.start();

        Intent refresh = new Intent(this, RegistrarVehiculoActivity.class);
        refresh.putExtra("registro", "B");
        startActivity(refresh);
        this.finish();

    }


    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {
        String alias =  parent.getItemAtPosition(pos).toString();
        System.out.println(alias);

        //consultar datos de vehiculo seleccionado
        HiloCliente hilo = new HiloCliente(9,alias);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(User.getTipoVehiculo());

        if(User.getTipoVehiculo().equals("m")){
            moto.setChecked(true);
        }else if(User.getTipoVehiculo().equals("c")){
            coche.setChecked(true);
        }else if(User.getTipoVehiculo().equals("f")){
            furgoneta.setChecked(true);
        }

        matricula.setText(User.getMatricula());
        marca.setText(User.getMarcaVehiculo());
        modelo.setText(User.getModeloVehiculo());
        tamano.setText(User.getTamanoVehiculo());

    }

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }
}