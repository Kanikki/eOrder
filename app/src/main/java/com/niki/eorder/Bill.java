package com.niki.eorder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.niki.eorder.model.History;

import java.util.List;
import java.util.Map;

public class Bill extends AppCompatActivity {
    private TextView locationName, standName, date;
    private History history;
    private Utility util = new Utility();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Integer> price;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        DataPassing dataPassing = DataPassing.getInstance();
        history = dataPassing.getHistory();
        adapter = new ArrayAdapter<>(this, R.layout.activity_bill, R.layout.activity_bill_list, StringArray);


        locationName = findViewById(R.id.bill_locationName);
        standName = findViewById(R.id.bill_stand_name);
        date = findViewById(R.id.bill_time);

        locationName.setText(util.capitalizeString(history.getLocationID().replaceAll("_", " ")));
        standName.setText(util.capitalizeString(history.getStandID()));
        date.setText(history.getDate());


    }
}
