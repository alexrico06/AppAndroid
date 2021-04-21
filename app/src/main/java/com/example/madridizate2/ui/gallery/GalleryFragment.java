package com.example.madridizate2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.madridizate2.HiloCliente;
import com.example.madridizate2.MainActivity;
import com.example.madridizate2.R;
import com.example.madridizate2.RegistrarUserActivity;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    String cod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);
/*        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        String[] datosP = RegistrarUserActivity.getDatosP();

        HiloCliente hilo = new HiloCliente(6, datosP[0]);

        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*EditText name = root.findViewById(R.id.name_user);
        name.setEnabled(false);
        name.setText(hilo.nombre);

        EditText mail = root.findViewById(R.id.mail_user);
        mail.setEnabled(false);
        mail.setText(correo);

        EditText tlf = root.findViewById(R.id.mobile_user);
        tlf.setEnabled(false);
        tlf.setText(hilo.telefono);

        EditText direccion = root.findViewById(R.id.address_user);
        direccion.setEnabled(false);
        direccion.setText(hilo.direccion);*/


        EditText codigo = root.findViewById(R.id.codigo_promocional);
        cod = codigo.getText().toString();

        if(!cod.isEmpty()) {
            root.findViewById(R.id.edit_card).setEnabled(true);
        }

        return root;
    }

    public void canjear(View v) {

        System.out.println("BOTON CANJEAR");

    }

    public void eitarTarjeta(View v) {

    }

}