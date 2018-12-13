 package com.example.joel.apprestaurant;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joel.apprestaurant.utils.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegisterRestaurant extends AppCompatActivity implements OnMapReadyCallback {
    private MapView map;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private TextView street;
    private Button next;
    private LatLng mainposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);
        map = findViewById(R.id.mapView);
        map.onCreate(savedInstanceState);
        map.onResume();
        MapsInitializer.initialize(this);
        map.getMapAsync(this);
        geocoder =  new Geocoder(getBaseContext(), Locale.getDefault());
        street = findViewById(R.id.street);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

    }
    public void sendData(){
        TextView  name = findViewById(R.id.name);
        TextView  nit = findViewById(R.id.nit);
        TextView  street = findViewById(R.id.street);
        TextView  property = findViewById(R.id.property);
        TextView  phone = findViewById(R.id.telf);


        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authoritation", Data.TOKEN);

        RequestParams params = new RequestParams();
        params.add("name", name.getText().toString());
        params.add("nit", nit.getText().toString());
        params.add("street", street.getText().toString());
        params.add("property", property.getText().toString());
        params.add("phone", phone.getText().toString());
        params.add("Lat", String.valueOf(mainposition.latitude));
        params.add("Log", String.valueOf(mainposition.longitude));

        client.post(Data.REGISTER_RESTAURANT, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCOde, PreferenceActivity.Header[] headers, JSONObject response){
                AlertDialog.Builder diaglog =new AlertDialog.Builder(RegisterRestaurant.this);
               // AsyncHttpClient.log.w(LOG_TAG, onSuccess(int,Header[],"JSONObject) was not overriden, but callback was received")
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
          //-19.5810667,-65.7633013
        LatLng potosi = new LatLng(-19.5810667,-65.7633013);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Lugar").zIndex(17).draggable(true));
        mMap.setMinZoomPreference(16);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(potosi));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mainposition = marker.getPosition();
                String street_string = getStreet(marker.getPosition().latitude, marker.getPosition().longitude);
                street.setText(street_string);
            }
        });
    }
    public String getStreet (Double lat, Double lon){
        List<Address> address;
        String result ="";

        try {
            address = geocoder.getFromLocation(lat,lon,1);
            result += address.get(0).getThoroughfare();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
