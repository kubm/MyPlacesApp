package com.example.kubacm.myplacesapp;


import com.example.kubacm.myplacesapp.gson.PlacesResult;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class MainModelImpl implements MainModel {

    MapOps.MapManager mapManagerImpl;
    MapOps.LocationManager mLocationManagerImpl;
    MapOps.PlaceFinder mPlaceFinderImpl;

    public MainModelImpl(MapOps.MapManager mapManagerImpl, MapOps.LocationManager mLocationManagerImpl,MapOps.PlaceFinder mPlaceFinderImpl){
        this.mapManagerImpl = mapManagerImpl;
        this.mLocationManagerImpl = mLocationManagerImpl;
        this.mPlaceFinderImpl = mPlaceFinderImpl;
    }

    public interface ModelMapResult {
        void onMapReady(GoogleMap map);
    }

    public interface ModelLocationResult {
        void onLocationChange(LocationE location);
        void onLocationSetupFailed();
    }

    public interface ModelPlacesResult {

        void onPlacesResult(PlacesResult result);
    }

    @Override
    public void prepareMapData(final ModelMapResult result){
        mapManagerImpl.setupMap(new MapOps.MapManagerResult(){
            @Override
            public void onMapReady(GoogleMap map){
                result.onMapReady(map);
            }
        });
    }

    @Override
    public void startGps(final ModelLocationResult result){
        mLocationManagerImpl.initGps(new MapOps.LocationManagerResult(){
            @Override
            public void onLocationChange(LocationE location) {result.onLocationChange(location);}

            @Override
            public void onLocationSetupFailed(){result.onLocationSetupFailed();}
        });
    }

    @Override
    public void downloadPlaceData(CharSequence charSequence, LocationE currentLoc, final ModelPlacesResult results){
        mPlaceFinderImpl.getPlaceData(charSequence, currentLoc, new MapOps.PlaceFinderResult() {
            @Override
            public void onPlacesResult(PlacesResult result) {
                results.onPlacesResult(result);
            }
        });
    }

}
