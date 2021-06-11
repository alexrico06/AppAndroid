package com.example.Madridizate.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.Madridizate.HiloCliente;
import com.example.Madridizate.R;
import com.example.Madridizate.User;

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

        Spinner tipoTarjeta = root.findViewById(R.id.spinnerTipo);
        tipoTarjeta.setEnabled(false);
        if(User.getTipoTarjeta().equals("VISA")){
            //tipoTarjeta.set
            System.out.println("lol");
            tipoTarjeta.setSelection(0);
        }else if(User.getTipoTarjeta().equals("MASTERCARD")){
            System.out.println("master");
            tipoTarjeta.setSelection(1);
        }else{
            System.out.println("vacio");
        }
        //tipoTarjeta.setEnabled(false);


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
                String[] datosT = new String[4];

                datosT[0] = cardNum.getText().toString();
                datosT[1] = fechaCaducidad.getText().toString();
                datosT[2] = cvv.getText().toString();
                datosT[3] = tipoTarjeta.getSelectedItem().toString();

                if(datosT[0].length() == 16){
                    if(!datosT[1].equals("")) {
                        if (datosT[2].length() == 3) {

                                HiloCliente hilo = new HiloCliente(7, datosT, User.getEmail(), 't');
                                hilo.start();
                                try {
                                    hilo.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                pressSaveCard.setVisibility(View.INVISIBLE);
                                pressEditCard.setVisibility(View.VISIBLE);
                                cardNum.setEnabled(false);
                                fechaCaducidad.setEnabled(false);
                                cvv.setEnabled(false);
                                tipoTarjeta.setEnabled(false);

                        } else {
                            Toast.makeText(getContext(), "EL CVV DEBE SER DE 3 DIGITOS", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "RELLENE LA FECHA DE CADUCIDAD DE LA TARJETA INTRODUCIDA", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "EL NUMERO DE TARJETA TIENE QUE SER DE 16 DIGITOS" , Toast.LENGTH_SHORT).show();
                }


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