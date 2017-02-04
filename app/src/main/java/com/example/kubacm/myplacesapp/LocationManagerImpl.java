package com.example.kubacm.myplacesapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class LocationManagerImpl implements MapOps.LocationManager {
    Context context;

    public LocationManagerImpl(Context context){this.context = context;};

    @Override
    public void initGps(final MapOps.LocationManagerResult callBack){
        Log.d("...","initGps");

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(!isGPSEnabled){
            Log.d("...","GPS is not enabled");

        }

        final LocationListener locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location){
                callBack.onLocationChange(new LocationE(location.getLatitude(),location.getLongitude()));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle){

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            Log.d("...","no permissions");
            callBack.onLocationSetupFailed();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        Log.d("...","GPS started");
    }
}
