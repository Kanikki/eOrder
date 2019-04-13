package com.niki.eorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.niki.eorder.model.User;

import java.util.List;

public class Dashboard extends AppCompatActivity {
    private Button btnSignOut;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TextView tvUserEmail, tvUserName, tvUserEbalance;
    private CardView cvOrderMenu;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        DocumentReference ref = db.collection("users").document(firebaseAuth.getUid());
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.i("LOGGER", "name " + documentSnapshot.getString("name"));
                Log.i("LOGGER", "email " + documentSnapshot.getString("email"));
                Log.i("LOGGER", "eBalance " + documentSnapshot.getString("eBalance").toString());

                user = documentSnapshot.toObject(User.class);
                Toast.makeText(Dashboard.this, "Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Dashboard.this, "Error getting data, please try again", Toast.LENGTH_SHORT).show();
                // firebaseAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();

            }
        });

        btnSignOut = findViewById(R.id.btn_sign_out);
        tvUserEmail = findViewById(R.id.tv_user_email);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEbalance = findViewById(R.id.tv_user_ebalance);
        cvOrderMenu = findViewById(R.id.cv_order_menu);


        String email = user.getEmail();
        String name = user.getName();
        int eBalance = user.geteBalance();

        tvUserEmail.setText(email);
        tvUserName.setText("Welcome back, " + name);
        tvUserEbalance.setText("Your eBalance : IDR " + eBalance);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(Dashboard.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        cvOrderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, QrCodeScanner.class);
                startActivity(intent);
            }
        });
    }
}
