package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderMenu extends AppCompatActivity {
    private TextView tvLocation, tvSeatNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        tvLocation = findViewById(R.id.tv_location);
        tvSeatNumber = findViewById(R.id.tv_seat_number);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            String location = bundle.getString("location");
            String seatNumber = bundle.getString("seatNumber");
            tvLocation.setText(location);
            tvSeatNumber.setText(seatNumber);
        }
    }

}
