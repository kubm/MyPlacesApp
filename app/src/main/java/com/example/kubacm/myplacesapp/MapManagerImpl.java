package com.example.kubacm.myplacesapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class MapManagerImpl implements MapOps.MapManager {

    SupportMapFragment supportMapFragment;

    public MapManagerImpl(SupportMapFragment supportMapFragment){
        this.supportMapFragment = supportMapFragment;
    }

    @Override
    public void setupMap(final MapOps.MapManagerResult callBack){
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                callBack.onMapReady(googleMap);
            }
        });
    }
}
