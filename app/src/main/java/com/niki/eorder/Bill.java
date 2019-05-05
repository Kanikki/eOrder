package com.niki.eorder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.niki.eorder.model.History;

public class Bill extends AppCompatActivity {
    private TextView locationName, standName, date;
    private History history;
    private Utility util = new Utility();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DataPassing dataPassing = DataPassing.getInstance();
        history = dataPassing.getHistory();

        locationName = findViewById(R.id.bill_locationName);
        standName = findViewById(R.id.bill_stand_name);
        date = findViewById(R.id.bill_time);

        locationName.setText(history.getLocationID());
        standName.setText(util.capitalizeString(history.getStandID()));
        date.setText(history.getDate());




    }
}
