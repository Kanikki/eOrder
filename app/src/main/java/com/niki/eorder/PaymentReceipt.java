package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PaymentReceipt extends AppCompatActivity {
    private TextView tvReservationID, tvSeatNumber;
    private DataPassing dataPassing = DataPassing.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        Random rand = new Random();
        int reservationID = rand.nextInt(9999) + 1;

        tvReservationID = findViewById(R.id.tv_reservation_id);
        tvSeatNumber = findViewById(R.id.tv_seat_number);
        tvReservationID.setText(String.format("%04d", reservationID));
        tvSeatNumber.setText("" + dataPassing.getSeatNumber());

        Button btnBackHome;
        btnBackHome = findViewById(R.id.btn_back_home);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentReceipt.this, Dashboard.class);
                startActivity(intent);
                Toast.makeText(PaymentReceipt.this, "Thank you for ordering using eOrder :)", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
