package com.example.madridizate2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    public MapsFragment() {
        // Required empty public constructor
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng madrid = new LatLng(40.41, -3.70);
            googleMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));
            googleMap.getMinZoomLevel();

            LatLng parking1 = new LatLng(40.41826897827488, -3.6984408001271163);
            MarkerOptions p1 = new MarkerOptions();
            googleMap.addMarker(new MarkerOptions().position(parking1).title("Parking Alsepark-Calle de Alcalá, 27, 28014 Madrid"));


            LatLng parking2 = new LatLng(40.442688490789976, -3.691395914534013);
            googleMap.addMarker(new MarkerOptions().position(parking2).title("Parking Saba-Paseo de la Castellana, 100, 28046 Madrid"));

            /*
            System.out.println(marker.getTitle());
                    String[] direccion;
                    direccion = marker.getTitle().split("-");

                    Intent i = new Intent(getContext(),ReservaParking.class);
                    i.putExtra("direccion",direccion[1]);
                    getContext().startActivity(i);
             */
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {


                    System.out.println(marker.getTitle());
                    String[] direccion;
                    direccion = marker.getTitle().split("-");

                    Intent i = new Intent(getContext(),ReservaParking.class);
                    i.putExtra("direccion",direccion[1]);
                    getContext().startActivity(i);


                }


            });


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}