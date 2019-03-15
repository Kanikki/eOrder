package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderMenu extends AppCompatActivity {
    private TextView tvLocation, tvSeatNumber;
    private FirebaseFirestore db;
    private ArrayList<String> foodcourtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        db = FirebaseFirestore.getInstance();

        foodcourtList = new ArrayList<>();


        db.collection("foodcourt").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list){

                    }
                }
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            String location = bundle.getString("location");
            String seatNumber = bundle.getString("seatNumber");

        }
    }

}
