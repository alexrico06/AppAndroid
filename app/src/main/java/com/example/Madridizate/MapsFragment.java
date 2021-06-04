package com.example.Madridizate;

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
import com.google.android.gms.maps.UiSettings;
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

            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);
            uiSettings.setCompassEnabled(true);

            googleMap.setTrafficEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            LatLng madrid = new LatLng(40.44, -3.70);
            //googleMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 12));

            LatLng parking1 = new LatLng(40.41826897827488, -3.6984408001271163);
            googleMap.addMarker(new MarkerOptions().position(parking1).title("Parking Alsepark-Calle de Alcalá, 27, 28014 Madrid"));


            LatLng parking2 = new LatLng(40.442688490789976, -3.691395914534013);
            googleMap.addMarker(new MarkerOptions().position(parking2).title("Parking Saba-Paseo de la Castellana, 100, 28046 Madrid"));

            LatLng parking3 = new LatLng(40.421554 , -3.705557);
            googleMap.addMarker(new MarkerOptions().position(parking3).title("Parking Tudescos-Plaza de Santa María Soledad Torres Acosta, 28004 Madrid"));

            LatLng parking4 = new LatLng( 40.426602, -3.687992);
            googleMap.addMarker(new MarkerOptions().position(parking4).title("SerranoPark-Calle de Hermosilla, 7, 28001 Madrid"));

            LatLng parking5 = new LatLng(40.424231, -3.682763);
            googleMap.addMarker(new MarkerOptions().position(parking5).title("Aparcamiento-Calle de Núñez de Balboa, 28001 Madrid"));

            LatLng parking6 = new LatLng(40.42065 , -3.699332);
            googleMap.addMarker(new MarkerOptions().position(parking6).title("Parking-Plaza de Pedro Zerolo, 28004 Madrid"));

            LatLng parking7 = new LatLng(40.424343, -3.691352);
            googleMap.addMarker(new MarkerOptions().position(parking7).title("Aparcamiento Público-Paseo de Recoletos, 37, 28004 Madrid"));

            LatLng parking8 = new LatLng(40.424124 , -3.684137);
            googleMap.addMarker(new MarkerOptions().position(parking8).title("Parking Público-Calle de Velázquez, 24, 28001 Madrid"));

            LatLng parking9 = new LatLng(40.418076, -3.690512);
            googleMap.addMarker(new MarkerOptions().position(parking9).title("Parking Montalbán-Calle Montalban, 4, 28014, Madrid"));

            LatLng parking10 = new LatLng(40.40695, -3.681271);
            googleMap.addMarker(new MarkerOptions().position(parking10).title("GARCA 2006 S.L. PARKING PÚBLICO-Paseo de la R. Cristina, 24, 28014 Madrid"));

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