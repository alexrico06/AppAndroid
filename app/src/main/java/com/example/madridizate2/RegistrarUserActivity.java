package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class RegistrarUserActivity extends AppCompatActivity {

    String textDNI;
    String textNombre;
    String textApellidos;
    String textPhone;
    String textEmail;
    String textFechaNacimiento;
    String textCalle;
    String textNumero;
    String textPortal;
    String textPiso;
    String textCodPostal;
    String textCiudad;
    String editTextTextPassword;
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
        i.putExtra("DNI",textDNI);
        startActivity(i);
    }

    public void pulsarMasTarde(View view) {

        insertarUsuario();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void insertarUsuario(){

        EditText dni = findViewById(R.id.textDNI);
        textDNI = dni.getText().toString();

        EditText nombre = findViewById(R.id.textNombre);
        textNombre = nombre.getText().toString();

        EditText apellidos = findViewById(R.id.textApellidos);
        textApellidos = apellidos.getText().toString();

        EditText telefone =  findViewById(R.id.textPhone);
        textPhone = telefone.getText().toString();

        EditText correo =  findViewById(R.id.textEmail);
        textEmail = correo.getText().toString();

        EditText fecha =  findViewById(R.id.textFechaNacimiento);
        textFechaNacimiento = fecha.getText().toString();

        EditText calle = findViewById(R.id.textCalle);
        textCalle = calle.getText().toString();

        EditText numero =  findViewById(R.id.textNumero);
        textNumero = numero.getText().toString();

        EditText portal =  findViewById(R.id.textPortal);
        textPortal = portal.getText().toString();

        EditText piso =  findViewById(R.id.textPiso);
        textPiso = piso.getText().toString();

        EditText cp =  findViewById(R.id.textCodPostal);
        textCodPostal = cp.getText().toString();

        EditText ciudad =  findViewById(R.id.textCiudad);
        textCiudad = ciudad.getText().toString();

        EditText password = findViewById(R.id.editTextTextPassword);
        editTextTextPassword = password.getText().toString();

        datosP[0] = textDNI;
        datosP[1] = textNombre;
        datosP[2] = textApellidos;
        datosP[3] = textPhone;
        datosP[4] = textEmail;
        datosP[5] = textFechaNacimiento;
        datosP[6] = editTextTextPassword;

        datosD[0] = textCalle;
        datosD[1] = textNumero;
        datosD[2] = textPortal;
        datosD[3] = textPiso;
        datosD[4] = textCodPostal;
        datosD[5] = textCiudad;

        HiloCliente hilo = new HiloCliente(2, datosP,datosD);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String[] getDatosP() {
        return datosP;
    }

    public void setDatosP(String[] datosP) {
        this.datosP = datosP;
    }
}