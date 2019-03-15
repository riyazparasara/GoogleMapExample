package com.riyaz.rkclg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private GoogleMap googleMap;
    private AppCompatEditText edtLatitude, edtLongitude;
    private AppCompatButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {

        edtLatitude = findViewById(R.id.edtLatitude);
        edtLongitude = findViewById(R.id.edtLongitude);
        btnSubmit = findViewById(R.id.btnSubmit);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        String latitudeString = edtLatitude.getText().toString().trim();
        String longitudeString = edtLongitude.getText().toString().trim();

        if (TextUtils.isEmpty(latitudeString)) {
            Toast.makeText(this, "Please enter latitude", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(longitudeString)) {
            Toast.makeText(this, "Please enter longitude", Toast.LENGTH_SHORT).show();
            return;
        }

        LatLng latLng = new LatLng(Double.parseDouble(latitudeString), Double.parseDouble(longitudeString));

        addGoogleMarker(latLng);

    }


    private void addGoogleMarker(LatLng latLng) {
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Hey There"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }


    @Override
    public void onMapReady(GoogleMap google) {
        googleMap = google;
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
    }
}
