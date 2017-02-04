package com.example.kubacm.myplacesapp;

import android.content.Context;
import android.content.res.Resources;


import com.example.kubacm.myplacesapp.gson.PlacesResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by KUBACM on 2017-02-04.
 */

public interface MainView {
    Resources getViewResources();
    void setupMap (GoogleMap map);
    void printMarketAndMoveCamera(LatLng latLng, String text);

    SupportMapFragment getSupportMapFragment();

    Context getContext();

    void requestGpsPermission();

    void hideMap();
    void showMap();

    void showProgressBar();
    void hideProgressBar();

    void showListView();
    void hideListView();

    void showSearchBar();
    void hideSearchBar();

    void showPlaceList(PlacesResult place);
    void showMsg (String s);
}
