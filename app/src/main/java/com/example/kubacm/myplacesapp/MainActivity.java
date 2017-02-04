package com.example.kubacm.myplacesapp;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class MainActivity extends FragmentActivity implements MainView,MaterialSearchBar.OnSearchActionListener{
    private MainPresenter mPresenter;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    ProgressBar progressBar;
    ListView listView;
    private MaterialSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


    }


}
