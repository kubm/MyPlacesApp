package com.example.kubacm.myplacesapp.gson;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class Result {
    private static final String OKAY_STATUS = "OK";
    private String status;
    public String getStatus() {return this.status;}
    public boolean isOkay(){ return OKAY_STATUS.equals(this.getStatus());}
}
