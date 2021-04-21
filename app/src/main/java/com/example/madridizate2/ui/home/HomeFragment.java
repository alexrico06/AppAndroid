package com.example.madridizate2.ui.home;

import android.content.Intent;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        String correo = MainActivity.getTextCorreo();

        HiloCliente hilo = new HiloCliente(5, correo);

        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EditText name = root.findViewById(R.id.name_user);
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
        direccion.setText(hilo.direccion);

        return root;
    }

    public void pressEditProfile(View view){

    }
}