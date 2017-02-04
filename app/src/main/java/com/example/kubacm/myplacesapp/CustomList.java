package com.example.kubacm.myplacesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by KUBACM on 2017-02-04.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Context context;
    private final String[] list;

    public CustomList(Context context, String [] values){
        super(context,R.layout.list_single,values);
        this.context = context;
        this.list = values;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.list_single,null,true);
        TextView txtTile = (TextView) rowView.findViewById(R.id.txt);
        txtTile.setText(list[position]);

        return rowView;
    }
}
