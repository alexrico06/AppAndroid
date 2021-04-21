package com.example.madridizate2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.madridizate2.R;

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

        EditText codigo = root.findViewById(R.id.codigo_promocional);
        cod = codigo.getText().toString();

        if(!cod.isEmpty()) {
            root.findViewById(R.id.canjear).setEnabled(true);
        }

        return root;
    }

    private void canjear(View v) {

        System.out.println("BOTON CANJEAR");

    }

}