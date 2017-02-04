package com.example.kubacm.myplacesapp;

import android.util.Log;

import com.google.android.gms.awareness.snapshot.PlacesResult;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class PlaceFinderImpl implements MapOps.PlaceFinder {
    private static final String GOOGLE_PLACES_API = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=5000&keyword=%s&language=pl&key=AIzaSyAH8EFBEKCHbKd10AVG8RlJd-ESRLJZ3ho";

    private static final String LOCATION_PATTERN = "%f,%f";

    @Override
    public void getPlaceData(final CharSequence charSequence, final LocationE currentLoc, final MapOps.PlaceFinderResult placeFinderResult){

        Thread thread = new Thread(new Runnable() {
            public Gson gson;
            @Override
            public void run() {
                URL url =  null;
                OkHttpClient client = new OkHttpClient();
                PlaceE placeResult = new PlaceE();
                try {
                    url = new URL(String.format(Locale.US,GOOGLE_PLACES_API,currentLoc.lat,currentLoc.lng,charSequence.toString()));

                    Request request = new Request.Builder().url(url).build();

                    Response response = client.newCall(request).execute();
                    Log.d("my Log","Request to api: "+request.toString());

                    GsonBuilder gb = new GsonBuilder();
                    gb.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                    this.gson = gb.create();

                    PlacesResult res = this.gson.fromJson(response.body().string(), PlacesResult.class);
                    placeFinderResult.onPlacesResult(res);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
