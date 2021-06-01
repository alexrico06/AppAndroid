package com.example.madridizate2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.madridizate2.ui.home.HomeFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReservaParking extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener  {


    String direccion;
    Spinner spinner_alias;
    Spinner spinner_plazas;
    EditText direccionText;

    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    private Date horaInicial, horaFinal;

    //Widgets
    EditText etFecha, etHoraIni, etHoraFin;
    ImageButton ibObtenerFecha, ibObtenerHoraIni, ibObtenerHoraFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_parking);

        Intent intent = getIntent();
        direccion = intent.getStringExtra("direccion");

        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        etHoraIni = (EditText) findViewById(R.id.et_mostrar_hora_picker);
        etHoraFin = (EditText) findViewById(R.id.et_mostrar_hora_fin_picker);


        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        ibObtenerHoraIni = (ImageButton) findViewById(R.id.ib_obtener_hora_inicio);
        ibObtenerHoraFin = (ImageButton) findViewById(R.id.ib_obtener_hora_fin);

        ibObtenerFecha.setOnClickListener(this);
        ibObtenerHoraIni.setOnClickListener(this);
        ibObtenerHoraFin.setOnClickListener(this);


        direccionText = findViewById(R.id.direccionParking);
        direccionText.setEnabled(false);
        direccionText.setText(direccion);


        //consultar todos los vehiculos del usuario
        HiloCliente hilo = new HiloCliente(8,User.getEmail());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        spinner_alias = (Spinner) findViewById(R.id.alias_spinner2);

        ArrayList<String> listaAlias = hilo.listaApodos;
        if(listaAlias.size()==0){

            Toast.makeText(this,"No ha registrado ningún vehiculo",Toast.LENGTH_SHORT).show();
            System.out.println("No hay coches insertados");

            /*Intent i = new Intent(this,RegistrarVehiculoActivity.class);
            i.putExtra("registro","A");
            startActivity(i);*/

            Button reserva = findViewById(R.id.reservarPlaza);
            //reserva.setEnabled(false);
        }

        spinner_alias.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaAlias));
        spinner_alias.setOnItemSelectedListener(this);

    }

    public void botonAtras(View view){

        Intent i = new Intent(this,ReservaParking.class);

        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(spinner_alias.getSelectedItem().toString());

        View contenedor_plazar = findViewById(R.id.contenedor_plazas);
        contenedor_plazar.setVisibility(View.VISIBLE);


        HiloCliente hilo = new HiloCliente(11,2, direccion, spinner_alias.getSelectedItem().toString());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> listaPlazasParkin = hilo.listaPlazasParkin;
        spinner_plazas = findViewById(R.id.spinner_plazas);

        spinner_plazas.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaPlazasParkin));
        //spinner_plazas.setOnItemSelectedListener(this);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_obtener_fecha:
                obtenerFecha();
                break;
            case R.id.ib_obtener_hora_inicio:
                obtenerHoraIni();
                break;
            case R.id.ib_obtener_hora_fin:
                obtenerHoraFin();
                break;
        }

    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

            }
        },anio, mes, dia);

        recogerFecha.show();

    }


    private void obtenerHoraIni(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);

                //Muestro la hora con el formato deseado
                etHoraIni.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                try {
                    horaInicial = simpleDateFormat.parse(hourOfDay + ":" + minute);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    private void obtenerHoraFin(){

        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);

                //Muestro la hora con el formato deseado
                etHoraFin.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                try {
                    horaFinal = simpleDateFormat.parse(hourOfDay + ":" + minute);
                    diferencia();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();

    }



    private void diferencia() {

        String diferencia = obtieneDiferencia(horaInicial, horaFinal);
        Toast.makeText(this,"DIFERENCIA "+diferencia,Toast.LENGTH_SHORT).show();

    }

    public String obtieneDiferencia(Date startDate, Date endDate) {
        //Diferencia en millisegundos
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return "horas: " + elapsedHours + " minutos: " + elapsedMinutes + "segundos: " + elapsedSeconds;
    }

    public void buttonReservar(View view){

        EditText fecha = findViewById(R.id.et_mostrar_fecha_picker);
        //System.out.println(fecha.getText());

        EditText horaI = findViewById(R.id.et_mostrar_hora_picker);
       // System.out.println(horaI.getText());

        EditText horaF = findViewById(R.id.et_mostrar_hora_fin_picker);
        //System.out.println(horaF.getText());

        //System.out.println(User.getEmail());

        //System.out.println(spinner_alias.getSelectedItem().toString());
        //System.out.println(spinner_plazas.getSelectedItem().toString());
        //System.out.println(direccionText.getText());


        String[] datosReserva = new String[7];

        datosReserva[0] = fecha.getText().toString();
        datosReserva[1] = horaI.getText().toString();
        datosReserva[2] = horaF.getText().toString();
        datosReserva[3] = User.getEmail();

        datosReserva[4] = spinner_alias.getSelectedItem().toString();
        datosReserva[5] = (spinner_plazas.getSelectedItem().toString());
        datosReserva[6] = direccionText.getText().toString();

        HiloCliente hilo = new HiloCliente(13, 2 ,datosReserva);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(hilo.resultado){

            Toast.makeText(this,"RESERVA NO DISPONIBLE",Toast.LENGTH_SHORT).show();
            System.out.println("no existe");

        }else{

            HiloCliente hiloT = new HiloCliente(6, User.getEmail());
            hiloT.start();

            try {
                hiloT.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(User.getNumTarjeta().equals("")){
                System.out.println("inserte tarjeta");
            }

            HiloCliente hiloCliente = new HiloCliente(12,2,datosReserva);
            hiloCliente.start();

            try {
                hiloCliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Toast.makeText(this,"RESERVA REALIZADA",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);

        }



    }
}