package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class ReservaParking extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    TimePicker timePicker;
    int TIME_PICKER_INTERVAL = 15;
    NumberPicker minutePicker;
    List<String> displayedValues;
    String direccion;
    Spinner spinner_alias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_parking);

        /*onCreate(savedInstanceState);
        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(0);
        timePicker.setCurrentMinute(0);
        setTimePickerInterval(timePicker);

         */

        Intent intent = getIntent();
        direccion = intent.getStringExtra("direccion");

        EditText direccionText = findViewById(R.id.direccionParking);
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
        Spinner spinner_plazas = findViewById(R.id.spinner_plazas);

        spinner_plazas.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaPlazasParkin));
        //spinner_plazas.setOnItemSelectedListener(this);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


/*
    private void setTimePickerInterval(TimePicker timePicker) {
        try {
            Class<?> classForid = Class.forName("com.android.internal.R$id");

            Field field = classForid.getField("minute");
            minutePicker = (NumberPicker) timePicker .findViewById(field.getInt(null));
            minutePicker.setMinValue(0); minutePicker.setMaxValue(7);
            displayedValues = new ArrayList<String>();

            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
                displayedValues.add(String.format("%02d", i));
            }
            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
                displayedValues.add(String.format("%02d", i));
            }
            minutePicker.setDisplayedValues(displayedValues .toArray(new String[0]));
        }
        catch (Exception e) { e.printStackTrace(); }
    }
 */



}