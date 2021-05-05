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
import com.example.madridizate2.R;
import com.example.madridizate2.RegistrarUserActivity;
import com.example.madridizate2.RegistrarVehiculoActivity;
import com.example.madridizate2.User;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText name,mail,tlf,calle, numero, portal, piso, ciudad, codigoPostal;

    Button pressEditProfile;
    Button pressSaveProfile;
    Button adminVehiculos;

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
                tlf.setEnabled(true);
                calle.setEnabled(true);
                numero.setEnabled(true);
                portal.setEnabled(true);
                piso.setEnabled(true);
                ciudad.setEnabled(true);
                codigoPostal.setEnabled(true);

                pressEditProfile.setVisibility(View.INVISIBLE);
                pressSaveProfile.setVisibility(View.VISIBLE);

            }
        });

        //BOTON GUARDAR PERFIL EDITADO
        pressSaveProfile = (Button) root.findViewById(R.id.save_profile);
        pressSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tlf = root.findViewById(R.id.mobile_user);
                tlf.setEnabled(false);

                calle = root.findViewById(R.id.calle);
                calle.setEnabled(false);

                numero = root.findViewById(R.id.numero_calle);
                numero.setEnabled(false);

                portal = root.findViewById(R.id.portal);
                portal.setEnabled(false);

                piso = root.findViewById(R.id.piso);
                piso.setEnabled(false);

                ciudad = root.findViewById(R.id.ciudad);
                ciudad.setEnabled(false);

                codigoPostal = root.findViewById(R.id.codigo_postal);
                codigoPostal.setEnabled(false);

                String[] datos = new String[7];
                datos[0] = tlf.getText().toString();
                datos[1] = calle.getText().toString();
                datos[2] = numero.getText().toString();
                datos[3] = portal.getText().toString();
                datos[4] = piso.getText().toString();
                datos[5] = codigoPostal.getText().toString();
                datos[6] = ciudad.getText().toString();

                HiloCliente hilo = new HiloCliente(7, datos,User.getEmail(),'u');
                hilo.start();

                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pressEditProfile.setVisibility(View.VISIBLE);
                pressSaveProfile.setVisibility(View.INVISIBLE);

            }
        });

        //BOTON ADMINISTRAR VEHICULOS
        adminVehiculos = (Button) root.findViewById(R.id.admin_vehiculos);
        adminVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RegistrarVehiculoActivity.class);
                startActivity(i);
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

        calle = root.findViewById(R.id.calle);
        calle.setEnabled(false);
        calle.setText(User.getCalle());

        numero = root.findViewById(R.id.numero_calle);
        numero.setEnabled(false);
        numero.setText(User.getNumero());

        portal = root.findViewById(R.id.portal);
        portal.setEnabled(false);
        portal.setText(User.getPortal());

        piso = root.findViewById(R.id.piso);
        piso.setEnabled(false);
        piso.setText(User.getPiso());

        ciudad = root.findViewById(R.id.ciudad);
        ciudad.setEnabled(false);
        ciudad.setText(User.getCiudad());

        codigoPostal = root.findViewById(R.id.codigo_postal);
        codigoPostal.setEnabled(false);
        codigoPostal.setText(User.getCp());


        return root;
    }

}