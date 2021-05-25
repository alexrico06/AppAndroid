package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madridizate2.ui.home.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean bandera = false;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText correo = findViewById(R.id.correo);
        correo.setText(User.getEmail());

        EditText contrasena = findViewById(R.id.contrasena);
        contrasena.setText(User.getPassword());
    }

    public void pulsarRegistrar(View view) {
        Intent i = new Intent(this, RegistrarUserActivity.class);
        startActivity(i);
    }

    public void pulsarInicioSesion(View view) {

        EditText correo = findViewById(R.id.correo);
        String textCorreo = correo.getText().toString();


        EditText contrasena = findViewById(R.id.contrasena);
        String textContrasena = contrasena.getText().toString();


        //Intent i = new Intent(this, Principal.class);
        //startActivity(i);

        HiloCliente hilo = new HiloCliente(1, 1,textCorreo, textContrasena);
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(hilo.existe);

        if(hilo.existe){

            Intent i = new Intent(this, Principal.class);
            //i.putExtra("correo", user.getEmail());
            user.setEmail(correo.getText().toString());
            user.setPassword(contrasena.getText().toString());
            startActivity(i);

        }else{

            Toast.makeText(this,"USUARIO NO ENCONRTADO",Toast.LENGTH_SHORT).show();
            System.out.println("no existe");
        }

    }

}