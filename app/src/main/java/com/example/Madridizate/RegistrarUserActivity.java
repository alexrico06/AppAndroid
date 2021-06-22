package com.example.Madridizate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarUserActivity extends AppCompatActivity {

    static String[] datosP = new String[7];
    String[] datosD = new String[6];
    boolean completo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_user);

    }

    public void registrarCoches(View view) {

        completo = insertarUsuario();

        if(completo){
            Intent i = new Intent(this, RegistrarVehiculoActivity.class);
            i.putExtra("registro","A");
            startActivity(i);
        }/*else{
            Toast.makeText(this,"ERROR AL INSERTAR",Toast.LENGTH_SHORT).show();
        }*/

    }

    public void pulsarMasTarde(View view) {

        completo = insertarUsuario();

        if(completo){
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
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher match = pat.matcher(datosP[0]);
            if(match.find()) {
                if (!datosP[1].equals("")) {
                    if (!datosP[2].equals("")) {
                        if (!datosP[5].equals("")) {
                            Pattern patt = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
                            Matcher match1 = patt.matcher(datosP[5]);
                            if(match1.find()) {
                                if (!datosP[3].equals("")) {
                                    if (!datosP[4].equals("")) {

                                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                                        Matcher mather = pattern.matcher(datosP[4]);

                                        if (mather.find()) {

                                            //DATOS DIRECCION
                                            if (!datosD[0].equals("")) {
                                                if (!datosD[1].equals("")) {
                                                    if (!datosD[4].equals("")) {
                                                        if (!datosD[5].equals("")) {

                                                            //DATO CONTRASEÑA
                                                            if (!datosP[6].equals("")) {

                                                                completo = true;

                                                            } else {

                                                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                                builder.setMessage("NO PUEDE DEJAR LA CONTRASEÑA VACIA ");

                                                                builder.setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        dialog.cancel();
                                                                    }
                                                                });

                                                                AlertDialog dialog = builder.create();
                                                                dialog.show();
                                                            }

                                                        } else {
                                                            Toast.makeText(this, "INTRODUZCA LA CIUDAD", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(this, "INTRODUZCA EL CODIGO POSTAL", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(this, "INTRODUZCA EL NUMERO DE VIVIENDA", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(this, "INTRODUZCA UNA CALLE", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(this, "INTRODUZCA UN CORREO CORRECTO", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(this, "INTRODUZCA UN CORREO", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "INTRODUZCA UN TELEFONO", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "FORMATO DE FECHA CORRECTO: \n 'DD/MM/YYYY' ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "INTRODUZCA SU FECHA DE NACIMIENTO", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "INTRODUZCA SUS APELLIDOS", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "INTRODUZCA SU NOMBRE", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"MAL FORMATO DEL DNI",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"LONGITUD DEL DNI DEBE SER 9",Toast.LENGTH_SHORT).show();
        }

        System.out.println(completo);

        if(completo){
            HiloCliente hilo = new HiloCliente(2, datosP,datosD);
            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return completo;
    }

}