package com.example.kubacm.myplacesapp;


import com.example.kubacm.myplacesapp.gson.PlacesResult;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by KUBACM on 2017-02-04.
 */

public interface MapOps {
    interface MapManager{
        void setupMap(MapOps.MapManagerResult result);
    }

    interface MapManagerResult{
        void onMapReady(GoogleMap map);
    }

    interface LocationManager{
        void initGps(MapOps.LocationManagerResult result);
    }

    interface  LocationManagerResult{
        void onLocationChange(LocationE location);
        void onLocationSetupFailed();
    }

    interface PlaceFinderResult{
        void onPlacesResult(PlacesResult result);
    }

    interface PlaceFinder{
        void getPlaceData(CharSequence charSequence, LocationE currentLoc, PlaceFinderResult placeFinderResult);
    }
}
