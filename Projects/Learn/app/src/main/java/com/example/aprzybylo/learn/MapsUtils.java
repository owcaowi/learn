package com.example.aprzybylo.learn;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.LinkedList;

/**
 * Created by aprzybylo on 08/03/2018.
 */

public class MapsUtils implements GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public MapsUtils(Activity activity, GoogleMap map) {
        mMap = map;
        mActivity = activity;
        mInflater = LayoutInflater.from(mActivity);
    }


    /* Zoom Camera to Points */
    public void zoomCamera(LinkedList<LatLng> list){

        if(mMap == null){
            return;
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for(LatLng loc : list){
            builder.include(loc);
        }

        LatLngBounds bounds = builder.build();

        int width = mActivity.getResources().getDisplayMetrics().widthPixels;
        int height = mActivity.getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.25); // 12% of screen

        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(update);

    }

    /* Set Markers */
    public void setMarkers(LinkedList<LatLng> list){

        if(mMap == null){
            return;
        }

        mMap.clear();

        for(LatLng loc : list){

//            MarkerOptions myMarker = new MarkerOptions().position(loc);
//            Marker marker = mMap.addMarker(myMarker);

//            String extraData = new String("abc");
//            marker.setTag(extraData);
        }

    }

    /* Polyline */
    public void addPolyline(LinkedList<LatLng> list){

        if(mMap == null){
            return;
        }

//        mMap.addPolyline(new PolylineOptions()
//                .addAll(list)
//                .width(5)
//                .color(Color.BLUE));

    }

    /* Tooltips */
    public void setTooltips( LinkedList<LatLng> list ){
        mMap.setInfoWindowAdapter(this);
    }

    public View setTooltipView( Marker marker ){

        //Get Object
        String myRestoredData = (String) marker.getTag();

        View layout= mInflater.inflate(R.layout.tooltip, null, false);
        TextView someText = layout.findViewById(R.id.someText);
        someText.setText(myRestoredData);

        return layout;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return setTooltipView(marker);
    }

    @Override
    public View getInfoContents(Marker marker) {
        return setTooltipView(marker);
    }


}
