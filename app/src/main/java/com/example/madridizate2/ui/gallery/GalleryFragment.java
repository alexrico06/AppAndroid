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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        Button pressEditProfile = (Button) root.findViewById(R.id.edit_profile);
        pressEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

        return root;
    }

}