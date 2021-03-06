package com.example.Madridizate.ui.slideshow;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.Madridizate.AdaptadorExpandableList;
import com.example.Madridizate.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_help, container, false);

        expListView = (ExpandableListView) root.findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new AdaptadorExpandableList(getActivity(), listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        Button openPdf = (Button) root.findViewById(R.id.openPdf);
        openPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Abriendo la Web...", Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //PREGUNTAS FRECUENTES
        listDataHeader.add("??Qui??nes pueden usar la aplicaci??n de Madrid??zate?");
        listDataHeader.add("??Cu??les son los requisitos necesarios para poder usar la app?");
        listDataHeader.add("??D??nde puedo descargar la aplicaci??n?");
        listDataHeader.add("??El uso de la aplicaci??n es gratuito?");
        listDataHeader.add("??Cuenta Madrid??zate con una interfaz amigable para el usuario?");
        listDataHeader.add("??En d??nde puedo solicitar ayuda para su uso?");
        listDataHeader.add("??En qu?? ciudades se encutra disponible Madrid??zate?");
        listDataHeader.add("??Qu?? m??todos de pago acepta Madrid??zate?");
        listDataHeader.add("??Qu?? suceder??a si tengo una incidencia con alg??n parking?");


        //RESPUESTAS
        List<String> quienes = new ArrayList<String>();
        quienes.add("Aquellas personas mayores de 18 a??os con permiso de conducir.");

        List<String> requisitos = new ArrayList<String>();
        requisitos.add("Ser mayor de 18 a??os");
        requisitos.add("Permiso de conducir vigente");
        requisitos.add("DNI/NIE vigente");
        requisitos.add("Estar registrado en Madrid??zate");

        List<String> store = new ArrayList<String>();
        store.add("Madrid??zate se encuentra disponible en en las tiendas de los smartphone Android e iOstiendas, PlayStore y AppleStore");

        List<String> free = new ArrayList<String>();
        free.add("El uso de Madrid??zate es totalmente. Simplemente la buscas en tu tienda de m??vil, la descargas, te registras y la usas.");

        List<String> easy = new ArrayList<String>();
        easy.add("Madrid??zate es una aplicaci??n sencilla de utilizar. Cuenta con una interfaz de usuario amigable.");

        List<String> help = new ArrayList<String>();
        help.add("En cualquiera de nuestros tel??fonos de contacto o en nuestro correo info@madridizate.org. Tambi??n contamos con un manual de ayuda para que el usuario " +
                "pueda descargar y utilizar");

        List<String> city = new ArrayList<String>();
        city.add("Por el momento Madrid??zate se encuentra disponible en la ciudad de Madrid, pero estamos en plena expansi??n, pronto estaremos en las ciudades m??s grandes de Espa??a");

        List<String> payment = new ArrayList<String>();
        payment.add("Cualquier tarjeta de cr??dito o d??bito que pertenzcan al grupo de VISA o Mastercard");

        List<String> incidencia = new ArrayList<String>();
        incidencia.add("En caso de una incidencia con el parking, Madrid??zate cuenta con un seguro a todo riesgo para el veh??culo del usuario. Se puede leer en el aviso legal de nuestra web");

        //A??ADIENDO RESPUESTAS EN CADA PREGUNTA
        listDataChild.put(listDataHeader.get(0), quienes);
        listDataChild.put(listDataHeader.get(1), requisitos);
        listDataChild.put(listDataHeader.get(2), store);
        listDataChild.put(listDataHeader.get(3), free);
        listDataChild.put(listDataHeader.get(4), easy);
        listDataChild.put(listDataHeader.get(5), help);
        listDataChild.put(listDataHeader.get(6), city);
        listDataChild.put(listDataHeader.get(7), payment);
        listDataChild.put(listDataHeader.get(8), incidencia);

    }

}