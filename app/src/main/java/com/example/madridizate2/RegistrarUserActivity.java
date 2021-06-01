package com.example.madridizate2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarUserActivity extends AppCompatActivity {

    static String[] datosP = new String[7];
    String[] datosD = new String[6];
    boolean vacios=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_user);

    }

    public void registrarCoches(View view) {

        vacios = insertarUsuario();

        if(!vacios){
            Intent i = new Intent(this, RegistrarVehiculoActivity.class);
            i.putExtra("registro","A");
            startActivity(i);
        }/*else{
            Toast.makeText(this,"ERROR AL INSERTAR",Toast.LENGTH_SHORT).show();
        }*/

    }

    public void pulsarMasTarde(View view) {

        vacios = insertarUsuario();

        if(!vacios){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }/*else{
            Toast.makeText(this,"ERROR AL INSERTAR",Toast.LENGTH_SHORT).show();
        }*/


    }

    private boolean insertarUsuario(){

        EditText dni = findViewById(R.id.textDNI);
        datosP[0] = dni.getText().toString().toUpperCase();
        User.setDni(datosP[0]);

        EditText nombre = findViewById(R.id.textNombre);
        datosP[1] = nombre.getText().toString().toUpperCase();

        EditText apellidos = findViewById(R.id.textApellidos);
        datosP[2] = apellidos.getText().toString().toUpperCase();

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


        if(datosP[0].length()==9){
            if(!datosP[1].equals("")){
                if(!datosP[2].equals("")){
                    if(!datosP[5].equals("")){
                        if(!datosP[3].equals("")){
                            if(!datosP[4].equals("")){

                                Pattern pattern = Pattern.compile ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                                Matcher mather = pattern.matcher(datosP[4]);

                                if (mather.find() == true) {
                                    System.out.println("El email ingresado es válido.");
                                    if(!datosP[6].equals("")){


                                    }else{
                                        //Toast.makeText(this,"INTRODUZCA UNA CONTRASEÑA",Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setMessage("CONTRASEÑA VACÍA, DESEA CONTINUAR?");

                                        builder.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                vacios=false;
                                            }
                                        });
                                        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }

                                } else {
                                    Toast.makeText(this,"INTRODUZCA UN CORREO CORRECTO",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(this,"INTRODUZCA UN CORREO",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this,"INTRODUZCA UN TELEFONO",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"INTRODUZCA SU FEHCA DE NACIMIENTO",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"INTRODUZCA SUS APELLIDOS",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"INTRODUZCA SU NOMBRE",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"MAL FORMATO DEL DNI",Toast.LENGTH_SHORT).show();
        }
        /*
        for (int i=0; datosD.length >= i;i++){
            System.out.println("datosP[i]");
            if(datosD[i].equals("")){
                Toast.makeText(this,"INTRODUZCA TODOS LOS DATOS DE DIRECCION",Toast.LENGTH_SHORT).show();
                vacios=true;
                break;
            }
        }*/

        System.out.println(vacios);

        if(!vacios){
            HiloCliente hilo = new HiloCliente(2, datosP,datosD);
            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return vacios;
    }

}