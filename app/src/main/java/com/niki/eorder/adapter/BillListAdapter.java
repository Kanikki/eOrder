package com.niki.eorder.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

public class BillListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] qty;
    private final String[] name;
    private final Integer[] price;

    public BillListAdapter(Adapter context, Integer[] qty, String[] name, Integer[] price){
        super(context);

    }


}
