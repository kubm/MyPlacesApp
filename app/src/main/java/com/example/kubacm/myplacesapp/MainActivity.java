package com.example.kubacm.myplacesapp;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kubacm.myplacesapp.gson.Place;
import com.example.kubacm.myplacesapp.gson.PlacesResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

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

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        MainPresenterImpl presenter = new MainPresenterImpl(this);
        mPresenter = presenter;

        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mPresenter.onStartup(savedInstanceState);
    }

    @Override
    public Resources getViewResources(){
        return null;
    }

    @Override
    public void setupMap(GoogleMap map){
        mMap = map;
    }

    @Override
    public void printMarketAndMoveCamera(LatLng latLng,String text){
        if (mMap != null){
            mMap.addMarker(new MarkerOptions().position(latLng).title(text));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16),2000,null);
        }
        else{
            throw new NullPointerException("mMap is unavailable");
        }
    }

    @Override
    public SupportMapFragment getSupportMapFragment(){
        return mapFragment;
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public void requestGpsPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
    }

    @Override
    public void hideMap() {
        findViewById(R.id.map).setVisibility(View.GONE);
    }

    @Override
    public void showMap() {
        findViewById(R.id.map).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }


    @Override
    public void hideListView() {
        findViewById(R.id.listView).setVisibility(View.GONE);
    }

    @Override
    public void showListView() {
        findViewById(R.id.listView).setVisibility(View.VISIBLE);
    }


    @Override
    public void hideSearchBar() {
        findViewById(R.id.searchBar).setVisibility(View.GONE);
    }

    @Override
    public void showSearchBar() {
        findViewById(R.id.searchBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void showPlaceList(PlacesResult placesResult) {


        listView = (ListView) this.findViewById(R.id.listView);


        List<String> list = new ArrayList<String>();

        for ( Place place : placesResult ) {
            list.add(place.getName());
        }

        CustomList adapter = new
                CustomList(getContext(), list.toArray(new String[0]) );
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos,   long id) {
                mPresenter.onPlaceSelected(pos);
            }
        });
    }

    @Override
    public void showMsg(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchConfirmed(CharSequence charSequence){
        mPresenter.onSearchConfirmed(charSequence);
    }

    @Override
    public void onSearchStateChanged(boolean b){
        mPresenter.onSearchStateChanged(b);
    }

    @Override
    public void onButtonClicked(int buttonCode){

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("...","onRequestPermissionsResult");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mPresenter.permissionGranted();
        }
    }
}
