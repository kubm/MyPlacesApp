package com.example.kubacm.myplacesapp;

/**
 * Created by KUBACM on 2017-02-04.
 */

public interface MainModel {
    void prepareMapData(MainModelImpl.ModelMapResult callBack);
    void startGps(MainModelImpl.ModelLocationResult result);
    void downloadPlaceData(CharSequence charSequence, LocationE currentLoc, MainModelImpl.ModelPlacesResult result);
}
