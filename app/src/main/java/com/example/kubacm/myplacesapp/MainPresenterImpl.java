package com.example.kubacm.myplacesapp;

import android.os.Bundle;
import android.os.Handler;


import com.example.kubacm.myplacesapp.gson.PlacesResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.WeakReference;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class MainPresenterImpl implements MainPresenter, MainModelImpl.ModelMapResult, MainModelImpl.ModelLocationResult,MainModelImpl.ModelPlacesResult {

    private WeakReference<MainView> mView;
    private MainModel mModel;
    private Handler handler;
    LocationE currentLoca = new LocationE(51.759445, 19.457216);
    PlacesResult placesResult;
    boolean isFirstMove = true;

    public MainPresenterImpl(MainView view) {
        mView = new WeakReference<>(view);
        handler = new Handler();
        mModel = new MainModelImpl(new MapManagerImpl(getView().getSupportMapFragment()), new LocationManagerImpl(getView().getContext()), new PlaceFinderImpl());

    }

    private MainView getView() throws NullPointerException {
        if (mView != null)
            return mView.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup(Bundle savedInstanceState) {
        getView().showSearchBar();
        getView().hideMap();
        getView().showProgressBar();
        getView().hideListView();
        mModel.prepareMapData(this);
    }

    @Override
    public void permissionGranted() {
        mModel.startGps(this);
    }

    @Override
    public void onSearchConfirmed(CharSequence charSequence) {
        if (charSequence.length() <= 2) {
            getView().showMsg("Too short");
            return;
        }
        getView().hideMap();
        getView().showProgressBar();
        mModel.downloadPlaceData(charSequence, currentLoca, this);
    }

    @Override
    public void onSearchStateChanged(boolean b) {

    }

    @Override
    public void onPlaceSelected(int pos) {
        getView().hideListView();
        getView().showMap();
        getView().showSearchBar();

        getView().printMarketAndMoveCamera(new LatLng(placesResult.asList().get(pos).getGeometry().getLocation().getLat(),
                        placesResult.asList().get(pos).getGeometry().getLocation().getLng()),
                placesResult.asList().get(pos).getName());
    }

    @Override
    public void onMapReady(GoogleMap map) {
        getView().hideProgressBar();
        getView().showMap();
        getView().setupMap(map);
        mModel.startGps(this);
    }

    @Override
    public void onLocationChange(LocationE location) {
        currentLoca = location;
        LatLng latLng = new LatLng(location.lat, location.lng);
        if (isFirstMove) {
            getView().printMarketAndMoveCamera(latLng, "You");
            isFirstMove = false;
        }
    }

    @Override
    public void onLocationSetupFailed() {
        getView().requestGpsPermission();
    }

    @Override
    public void onPlacesResult(final PlacesResult result){
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().hideProgressBar();
                if(result.isOkay()){
                    placesResult = result;
                    getView().hideSearchBar();
                }
            }
        })
    }
}



