 package com.project.random_people;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView latitude;
    TextView longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        longitude = (TextView) findViewById(R.id.txt_out_longitude);
        latitude = (TextView) findViewById(R.id.txt_out_latitude);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String latitudeResposta = (String) bundle.get("latitude");
        String longitudeResposta = (String) bundle.get("longitude");

        double latitudeMap = Double.valueOf(latitudeResposta);
        double longitudeMap = Double.valueOf(longitudeResposta);

        LatLng latlng = new LatLng(latitudeMap, longitudeMap);
        mMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title("Random People Localization"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }
}
