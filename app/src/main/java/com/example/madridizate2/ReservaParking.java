package com.example.madridizate2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReservaParking extends AppCompatActivity {

    TimePicker timePicker;
    int TIME_PICKER_INTERVAL = 15;
    NumberPicker minutePicker;
    List<String> displayedValues;

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