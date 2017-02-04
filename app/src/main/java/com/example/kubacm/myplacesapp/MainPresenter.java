package com.example.kubacm.myplacesapp;

import android.os.Bundle;

/**
 * Created by KUBACM on 2017-02-04.
 */

public interface MainPresenter {
    void onStartup(Bundle savedInstanceState);
    void permissionGranted();
    void onSearchConfirmed(CharSequence charSequence);
    void onSearchStateChanged(boolean b);
    void onPlaceSelected(int pos);
}
