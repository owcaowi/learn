package com.example.aprzybylo.learn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.util.LinkedList;


public class MapFragment extends SupportMapFragment {

    private static final String TAG = "MapFragment";
    private static final int REQUEST_LOCATION_PERMISSIONS = 0;

    private static final String[] LOCATION_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    private GoogleApiClient mClient;
    private MapsUtils mUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mUtils = new MapsUtils(getActivity(), googleMap);

                if(hasLocationPermission()) {
                    setContent();
                }else{
                    requestPermissions(LOCATION_PERMISSIONS, REQUEST_LOCATION_PERMISSIONS);
                }
            }
        });

    }


    public LinkedList<LatLng> getLocalizations(){

        LinkedList<LatLng> locList = new LinkedList<>();
        locList.add( new LatLng(48.137154, 11.576124) ); // Munich
        locList.add( new LatLng(52.520008, 13.404954) ); // Berlin
        locList.add( new LatLng(52.237049, 21.017532) ); // Warsaw

        return locList;
    }

    public void setContent(){
        LinkedList loc = getLocalizations();
        mUtils.zoomCamera(loc);
        mUtils.setMarkers(loc);
        mUtils.addPolyline(loc);
        mUtils.setTooltips(loc);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSIONS:
                if (hasLocationPermission()) {
                    setContent();
                }else{
                    Log.d(TAG,"Enable Permissions!");
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean hasLocationPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), LOCATION_PERMISSIONS[0]);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}
