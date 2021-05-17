package com.example.madridizate2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madridizate2.Adaptador;
import com.example.madridizate2.HiloCliente;
import com.example.madridizate2.R;
import com.example.madridizate2.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_menu_parkings, container, false);

/*

        HiloCliente hilo = new HiloCliente(4);
        hilo.start();

        RecyclerView listaplazas = root.findViewById(R.id.parkings);
        listaplazas.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listaplazas.setLayoutManager(layoutManager);

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String[] plaza: hilo.listaResultados) {
            System.out.println(plaza[0] +"-"+plaza[1]);
        }

        RecyclerView.Adapter miAdaptador = new Adaptador(hilo.listaResultados);
        listaplazas.setAdapter(miAdaptador);


*/

        return root;

    }
}