package com.niki.eorder;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    private TextView tvPrice, tvTimer;
    private long timeLeftMilliseconds = 1050000;
    private boolean pay = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvPrice = findViewById(R.id.tv_payment_price);
        tvTimer = findViewById(R.id.tv_payment_timer);

        Intent intent = getIntent();
        int price = intent.getIntExtra("paymentPrice", 0);

        tvPrice.setText("IDR" + String.valueOf(price));

        Button btnConfirmPayment = findViewById(R.id.btn_confirm_payment);
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent = new Intent(Payment.this, PaymentReceipt.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean shouldAllowBack(){
        return false;
    }
    public void doNothing(){

    }

    CountDownTimer countDownTimer = new CountDownTimer(timeLeftMilliseconds, 1000) {
        public void onTick(long millisUntilFinished) {
            int second = (int) millisUntilFinished / 1000;
            int minutes = second / 60;
            second = second % 60;
            tvTimer.setText("00:" + String.format("%02d", minutes) + ":" + String.format("%02d", second));
        }

        public void onFinish() {
            tvTimer.setText("00:00:00");
            Intent intent = new Intent(Payment.this, Dashboard.class);
            startActivity(intent);
            Toast.makeText(Payment.this, "Transaction has been cancelled", Toast.LENGTH_LONG).show();
            finish();
        }
    }.start();

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()){
            doNothing();
        }
        else{
            super.onBackPressed();
        }
    }
}
