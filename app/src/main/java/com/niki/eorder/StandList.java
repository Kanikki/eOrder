package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.niki.eorder.adapter.StandAdapter;
import com.niki.eorder.model.History;
import com.niki.eorder.model.Stand;

import java.util.ArrayList;
import java.util.List;

public class StandList extends AppCompatActivity {
    private DataPassing dataPassing = DataPassing.getInstance();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private StandAdapter adapter;
    private TextView tvLocation, tvSeatNumber;
    private FirebaseFirestore db;
    private ArrayList<Stand> stands;
    private String path = "", location = "", seatNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_list);

        progressBar = findViewById(R.id.pb_stand_list);
        recyclerView = findViewById(R.id.rv_stand_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stands = new ArrayList<>();
        adapter = new StandAdapter(StandList.this, stands);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        location = dataPassing.getLocation();

        // get data from fire store
        path = "foodcourt/" + location + "/stand_list";
        db.collection(path).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list){
                        Stand f = d.toObject(Stand.class);
                        f.setID(d.getId());
                        stands.add(f);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(StandList.this, "Aw, Snap!, please try again in a few minutes", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivityForResult(intent, 0);

        return true;
    }
}
