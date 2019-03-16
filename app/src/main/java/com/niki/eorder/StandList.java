package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.niki.eorder.adapter.StandAdapter;
import com.niki.eorder.model.Stand;

import java.util.ArrayList;
import java.util.List;

public class StandList extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private StandAdapter adapter;
    private TextView tvLocation, tvSeatNumber;
    private FirebaseFirestore db;
    private ArrayList<Stand> standList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_list);

        progressBar = findViewById(R.id.pb_stand_list);
        recyclerView = findViewById(R.id.rv_stand_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StandAdapter(StandList.this, standList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        standList = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String path = "";
        String location = "";
        String seatNumber = "";

        if (bundle != null){
            location = bundle.getString("location");
            seatNumber = bundle.getString("seatNumber");

        }


        path = "foodcourt/" + location + "/stand_list";
        db.collection(path).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                progressBar.setVisibility(View.GONE);
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list){
                        Stand f = d.toObject(Stand.class);
                        standList.add(f);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
