package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.niki.eorder.adapter.MenuAdapter;
import com.niki.eorder.model.Menu;
import com.niki.eorder.model.Stand;

import java.util.ArrayList;
import java.util.List;

public class MenuList extends AppCompatActivity {
    private DataPassing dataPassing = DataPassing.getInstance();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private TextView tvLocation, tvSeatNumber;
    private FirebaseFirestore db;
    private ArrayList<Menu> menus;
    private String location = "", standID = "", path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        progressBar = findViewById(R.id.pb_menu_list);
        recyclerView = findViewById(R.id.rv_menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menus = new ArrayList<>();
        adapter = new MenuAdapter(MenuList.this, menus);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        location = dataPassing.getLocation();
        standID = dataPassing.getStandID();

        // get data from fire store
        path = "foodcourt/" + location + "/stand_list/" + standID + "/menu";
        db.collection(path).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list){
                        Menu f = d.toObject(Menu.class);
                        f.setID(d.getId());
                        menus.add(f);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MenuList.this, "Aw, Snap!, please try again in a few minutes", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
