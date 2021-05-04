package com.example.madridizate2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.madridizate2.HiloCliente;
import com.example.madridizate2.MainActivity;
import com.example.madridizate2.R;
import com.example.madridizate2.RegistrarUserActivity;
import com.example.madridizate2.User;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    String cod;
    Button pressSaveCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);


        HiloCliente hilo = new HiloCliente(6, User.getEmail());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EditText cardNum = root.findViewById(R.id.cardNum);
        cardNum.setEnabled(false);
        cardNum.setText(User.getNumTarjeta());

        EditText fechaCaducidad = root.findViewById(R.id.fechaCaducidad);
        fechaCaducidad.setEnabled(false);
        fechaCaducidad.setText(User.getFechaCaducidad());

        EditText cvv = root.findViewById(R.id.cvv);
        cvv.setEnabled(false);
        cvv.setText(User.getCvv());

        EditText tipoTarjeta = root.findViewById(R.id.tipoTarjeta);
        tipoTarjeta.setEnabled(false);
        tipoTarjeta.setText(User.getTipoTarjeta());

        EditText codigo = root.findViewById(R.id.codigo_promocional);
        cod = codigo.getText().toString();


        Button pressEditCard = (Button) root.findViewById(R.id.edit_card);
        pressEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardNum.setEnabled(true);
                fechaCaducidad.setEnabled(true);
                cvv.setEnabled(true);
                tipoTarjeta.setEnabled(true);

                pressEditCard.setVisibility(View.INVISIBLE);
                pressSaveCard.setVisibility(View.VISIBLE);

            }
        });

        pressSaveCard = (Button) root.findViewById(R.id.save_card);
        pressSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardNum.setEnabled(false);
                fechaCaducidad.setEnabled(false);
                cvv.setEnabled(false);
                tipoTarjeta.setEnabled(false);

                String[] datosT = new String[4];

                datosT[0] = cardNum.toString();
                datosT[1] = fechaCaducidad.toString();
                datosT[2] = cvv.toString();
                datosT[3] = tipoTarjeta.toString();

                HiloCliente hilo = new HiloCliente(8, datosT, User.getEmail());
                hilo.start();

                pressSaveCard.setVisibility(View.INVISIBLE);
                pressEditCard.setVisibility(View.VISIBLE);

/*
                User.setNumTarjeta(cardNum.toString());
                User.setFechaCaducidad(fechaCaducidad.toString());
                User.setCvv(cvv.toString());
                User.setTipoTarjeta(tipoTarjeta.toString());
                */

            }
        });

        return root;
    }

}