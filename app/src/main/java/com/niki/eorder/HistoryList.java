package com.niki.eorder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.niki.eorder.adapter.HistoryAdapter;
import com.niki.eorder.model.History;

import java.util.ArrayList;

public class HistoryList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<History> histories;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        CollectionReference historyRef = db.collection("history");
        historyRef.whereEqualTo("userID", firebaseAuth.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

            }
        });


    }
}
