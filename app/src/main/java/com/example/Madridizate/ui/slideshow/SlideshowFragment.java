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
                try {

                    URL u = new URL("/download/");
                    URLConnection conn = u.openConnection();
                    int contentLength = conn.getContentLength();

                    DataInputStream stream = new DataInputStream(u.openStream());

                    byte[] buffer = new byte[contentLength];
                    stream.readFully(buffer);
                    stream.close();

                    DataOutputStream fos = new DataOutputStream(new FileOutputStream("manual_de_usuario_madridizate.pdf"));
                    fos.write(buffer);
                    fos.flush();
                    fos.close();

                } catch (IOException e) {
                    Log.d("PdfManager", "Error: " + e);
                }

            }
        });

        return root;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //PREGUNTAS FRECUENTES
        listDataHeader.add("¿Quiénes pueden usar la aplicación de Madridízate?");
        listDataHeader.add("¿Cuáles son los requisitos necesarios para poder usar la app?");
        listDataHeader.add("¿Dónde puedo descargar la aplicación?");
        listDataHeader.add("¿El uso de la aplicación es gratuito?");
        listDataHeader.add("¿Cuenta Madridízate con una interfaz amigable para el usuario?");
        listDataHeader.add("¿En dónde puedo solicitar ayuda para su uso?");
        listDataHeader.add("¿En qué ciudades se encutra disponible Madridízate?");
        listDataHeader.add("¿Qué métodos de pago acepta Madridízate?");
        listDataHeader.add("¿Qué sucedería si tengo una incidencia con algún parking?");


        //RESPUESTAS
        List<String> quienes = new ArrayList<String>();
        quienes.add("Aquellas personas mayores de 18 años con permiso de conducir.");

        List<String> requisitos = new ArrayList<String>();
        requisitos.add("Ser mayor de 18 años");
        requisitos.add("Permiso de conducir vigente");
        requisitos.add("DNI/NIE vigente");
        requisitos.add("Estar registrado en Madridízate");

        List<String> store = new ArrayList<String>();
        store.add("Madridízate se encuentra disponible en en las tiendas de los smartphone Android e iOstiendas, PlayStore y AppleStore");

        List<String> free = new ArrayList<String>();
        free.add("El uso de Madridízate es totalmente. Simplemente la buscas en tu tienda de móvil, la descargas, te registras y la usas.");

        List<String> easy = new ArrayList<String>();
        easy.add("Madridízate es una aplicación sencilla de utilizar. Cuenta con una interfaz de usuario amigable.");

        List<String> help = new ArrayList<String>();
        help.add("En cualquiera de nuestros teléfonos de contacto o en nuestro correo info@madridizate.org. También contamos con un manual de ayuda para que el usuario " +
                "pueda descargar y utilizar");

        List<String> city = new ArrayList<String>();
        city.add("Por el momento Madridízate se encuentra disponible en la ciudad de Madrid, pero estamos en plena expansión, pronto estaremos en las ciudades más grandes de España");

        List<String> payment = new ArrayList<String>();
        payment.add("Cualquier tarjeta de crédito o débito que pertenzcan al grupo de VISA o Mastercard");

        List<String> incidencia = new ArrayList<String>();
        incidencia.add("En caso de una incidencia con el parking, Madridízate cuenta con un seguro a todo riesgo para el vehículo del usuario. Se puede leer en el aviso legal de nuestra web");

        //AÑADIENDO RESPUESTAS EN CADA PREGUNTA
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