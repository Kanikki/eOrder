package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private Button btnSignOut;
    private FirebaseAuth firebaseAuth;
    private TextView tvUserEmail;
    private CardView cvOrderMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnSignOut = findViewById(R.id.btn_sign_out);
        tvUserEmail = findViewById(R.id.tv_user_email);
        cvOrderMenu = findViewById(R.id.cv_order_menu);
        firebaseAuth = FirebaseAuth.getInstance();

        String email;
        email = firebaseAuth.getCurrentUser().getEmail();

        tvUserEmail.setText(email);

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
                Intent intent = new Intent(Dashboard.this, StandList.class);
                startActivity(intent);
            }
        });
    }
}
