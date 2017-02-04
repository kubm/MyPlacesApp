package com.example.kubacm.myplacesapp.gson;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class PlacesResult extends Result implements Iterable<Place>{
    private String nextPageToken;
    private List<Place> results;
    public List<Place> asList(){
        return Collections.unmodifiableList(this.results);
    }
    public String getNextPageToken(){
        return this.nextPageToken;
    }
    @Override
    public Iterator<Place> iterator(){
        return this.results.iterator();
    }
    public int size(){
        return this.results.size();
    }
}
