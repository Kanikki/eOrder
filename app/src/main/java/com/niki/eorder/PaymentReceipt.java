package com.niki.eorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.niki.eorder.model.Cart;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PaymentReceipt extends AppCompatActivity {
    private TextView tvReservationID, tvSeatNumber;
    private DataPassing dataPassing = DataPassing.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Date date = new Date();
    private List<Cart> carts;
    private ArrayList<String> foodID = new ArrayList<>();
    private String [] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final Intent intent = getIntent();
        long price = intent.getIntExtra("paymentPrice", 0);

        carts = dataPassing.getCarts();

        for (Cart cart : carts){
            foodID.add(cart.getID());
        }

        item = foodID.toArray(new String[foodID.size()]);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        Random rand = new Random();
        int reservationID = rand.nextInt(9999) + 1;

        Map<String, Object> data = new HashMap<>();
        data.put("userID", firebaseAuth.getUid());
        data.put("totalPrice", price);
        data.put("dateAndTime", new Timestamp(date));
        data.put("menuOrdered", Arrays.asList(item));
        data.put("standID", dataPassing.getStandID());
        data.put("seatNumber", dataPassing.getSeatNumber());
        data.put("reservationID", reservationID);
        data.put("locationID", dataPassing.getLocation());

        DocumentReference ref = db.collection("HistoryList").document();
        ref.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PaymentReceipt.this, "Something went wrong when do your payment, please try again in a few minutes", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
                finish();
            }
        });



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
