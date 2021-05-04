package com.example.madridizate2.ui.home;

import android.content.Intent;
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
import com.example.madridizate2.User;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText name,mail,tlf,direccion;

    Button pressEditProfile;
    Button pressSaveProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        //BOTON EDITAR PERFIL
        pressEditProfile = (Button) root.findViewById(R.id.edit_profile);
        pressEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                mail.setEnabled(true);
                tlf.setEnabled(true);
                direccion.setEnabled(true);

                pressEditProfile.setVisibility(View.INVISIBLE);
                pressSaveProfile.setVisibility(View.VISIBLE);

            }
        });

        //BOTON GUARDAR PERFIL EDITADO
        pressSaveProfile = (Button) root.findViewById(R.id.save_profile);
        pressSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = root.findViewById(R.id.name_user);
                String nombre = name.toString();
                name.setEnabled(false);

                mail = root.findViewById(R.id.mail_user);
                String email = mail.toString();
                mail.setEnabled(false);

                tlf = root.findViewById(R.id.mobile_user);
                String tel = tlf.toString();
                tlf.setEnabled(false);

                direccion = root.findViewById(R.id.address_user);
                String direc = direccion.toString();
                direccion.setEnabled(false);

               /* HiloCliente hilo = new HiloCliente(7, User.getEmail());
                hilo.start();

                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                pressEditProfile.setVisibility(View.VISIBLE);
                pressSaveProfile.setVisibility(View.INVISIBLE);

            }
        });

        HiloCliente hilo = new HiloCliente(5, User.getEmail());
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        name = root.findViewById(R.id.name_user);
        name.setEnabled(false);
        name.setText(User.getNombre());

        mail = root.findViewById(R.id.mail_user);
        mail.setEnabled(false);
        mail.setText(User.getEmail());

        tlf = root.findViewById(R.id.mobile_user);
        tlf.setEnabled(false);
        tlf.setText(User.getTel());

        direccion = root.findViewById(R.id.address_user);
        direccion.setEnabled(false);
        direccion.setText(User.getDireccion());

        return root;
    }

}