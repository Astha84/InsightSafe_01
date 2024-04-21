package com.example.firstapp.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstapp.NearbyHospitalsManager;
import com.example.firstapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
//        Setting();
//        setContentView(R.layout.fragment_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.google_map);
//        mapFragment.getMapAsync(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
//        EditText editText= getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // When map is loaded
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // When clicked on map
                        // Initialize marker options
                        MarkerOptions markerOptions=new MarkerOptions();
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                        // Remove all marker
                        mMap.clear();
                        // Animating to zoom the marker
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        // Add marker on map
                        mMap.addMarker(markerOptions);
                    }
                });
            }
        });

        EditText editText = view.findViewById(R.id.editText);
        Button button= view.findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location = editText.getText().toString();
                List<Address>addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    findNearbyHospitals(address.getLatitude(), address.getLongitude());
                }
            }
        });














        // Return view
        return view;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);

    }

//    public void Setting(){
//        Button button = getView().findViewById(R.id.search_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText locationSearch =getView().findViewById(R.id.editText);
//                String location = locationSearch.getText().toString();
//                List<Address> addressList = null;
//
//                if (location != null || !location.equals("")) {
//                    Geocoder geocoder = new Geocoder(getContext());
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 1);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                }
//            }
//        });
//    }

//    public void onMapSearch(View view) {
//        EditText locationSearch =findViewById(R.id.editText);
//        String location = locationSearch.getText().toString();
//        List<Address> addressList = null;
//
//        if (location != null || !location.equals("")) {
//            Geocoder geocoder = new Geocoder(this);
//            try {
//                addressList = geocoder.getFromLocationName(location, 1);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Address address = addressList.get(0);
//            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
//            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        }
//    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(27.746974, 85.301582);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Kathmandu, Nepal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    private void findNearbyHospitals(double latitude, double longitude) {
        // Use Google Places API to find nearby hospitals
        // This part involves making a request to the Places API and parsing the response
        // to get nearby hospital locations. Refer to the Google Places API documentation for details.
        // Once you have the hospital locations, you can add markers to the map to display them.
        // Here's a sample code for adding a marker:


        Map<Double,Double> pairMap = new NearbyHospitalsManager().findNearbyHospitals(latitude,longitude,"AIzaSyBVsSQ7RecFk_nkcLFUqd9-y8DSp94kaRQ",mMap,getContext(),"hospital");
        Map<Double,Double> pairMap1 = new NearbyHospitalsManager().findNearbyHospitals(latitude,longitude,"AIzaSyBVsSQ7RecFk_nkcLFUqd9-y8DSp94kaRQ",mMap,getContext(),"police");
        Log.d("Astha","inNearByHospital");
        for (Map.Entry<Double,Double> entry : pairMap.entrySet()){
        Log.d("Astha","inForLoop");

        LatLng hospitalLocation = new LatLng(entry.getKey(), entry.getValue());
        mMap.addMarker(new MarkerOptions().position(hospitalLocation).title("Hospital Name").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
    }


    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=").append(latitude).append(",").append(longitude);
        googlePlaceUrl.append("&radius=").append(500);
        googlePlaceUrl.append("&type=").append(nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + "AIzaSyBVsSQ7RecFk_nkcLFUqd9-y8DSp94kaRQ");

        Log.d("NearbyHospitalActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();


    }


}
