package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderMenu extends AppCompatActivity {
    private TextView tvLocation, tvSeatNumber;
    private Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        tvLocation = findViewById(R.id.tv_location);
        tvSeatNumber = findViewById(R.id.tv_seat_number);
        btnScan = findViewById(R.id.btn_scan);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            String location = bundle.getString("location");
            String seatNumber = bundle.getString("seatNumber");
            tvLocation.setText(location);
            tvSeatNumber.setText(seatNumber);
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderMenu.this, QrScanner.class);
                startActivity(intent);
            }
        });
    }

}
