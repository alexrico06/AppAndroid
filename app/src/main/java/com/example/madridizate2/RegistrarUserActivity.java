package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class RegistrarUserActivity extends AppCompatActivity {

    static String[] datosP = new String[7];
    String[] datosD = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_user);

    }

    public void registrarCoches(View view) {

        insertarUsuario();

        Intent i = new Intent(this, RegistrarVehiculoActivity.class);
        i.putExtra("DNI",datosP[0]);
        startActivity(i);
    }

    public void pulsarMasTarde(View view) {

        insertarUsuario();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void insertarUsuario(){

        EditText dni = findViewById(R.id.textDNI);
        datosP[0] = dni.getText().toString();

        EditText nombre = findViewById(R.id.textNombre);
        datosP[1] = nombre.getText().toString();

        EditText apellidos = findViewById(R.id.textApellidos);
        datosP[2] = apellidos.getText().toString();

        EditText telefone =  findViewById(R.id.textPhone);
        datosP[3] = telefone.getText().toString();

        EditText correo =  findViewById(R.id.textEmail);
        datosP[4] = correo.getText().toString();
        User.setEmail(datosP[4]);

        EditText fecha =  findViewById(R.id.textFechaNacimiento);
        datosP[5] = fecha.getText().toString();

        EditText password = findViewById(R.id.editTextTextPassword);
        datosP[6] = password.getText().toString();
        User.setPassword(datosP[6]);

        //////////////////////////////////////////
        EditText calle = findViewById(R.id.textCalle);
        datosD[0] = calle.getText().toString();

        EditText numero =  findViewById(R.id.textNumero);
        datosD[1] = numero.getText().toString();

        EditText portal =  findViewById(R.id.textPortal);
        datosD[2] = portal.getText().toString();

        EditText piso =  findViewById(R.id.textPiso);
        datosD[3] = piso.getText().toString();

        EditText cp =  findViewById(R.id.textCodPostal);
        datosD[4] = cp.getText().toString();

        EditText ciudad =  findViewById(R.id.textCiudad);
        datosD[5] = ciudad.getText().toString();

        HiloCliente hilo = new HiloCliente(2, datosP,datosD);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}