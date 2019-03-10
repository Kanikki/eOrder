package com.niki.eorder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.niki.eorder.utility.BackendlessSettings;
import com.niki.eorder.utility.LoadingCallback;

public class SignIn extends AppCompatActivity {
    Button btnSignIn;
    ImageView ivSignIn;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // disable action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        BackendlessSettings backendlessSettings = new BackendlessSettings();
        Backendless.initApp(this, backendlessSettings.applicationID, backendlessSettings.androidApiKey);

        ivSignIn = findViewById(R.id.iv_sign_in);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_dont_have_sign_up);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        ivSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.equals("") || password.equals("")){
                    Toast.makeText(SignIn.this, "All forms must be filled!", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(email)){
                    Toast.makeText(SignIn.this, "Email invalid!", Toast.LENGTH_SHORT).show();
                    etEmail.setText("");
                    etPassword.setText("");
                }
                else{
                    LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();
                    loginCallback.showLoading();
                    loginUser(email, password, loginCallback);
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void loginUser(String email, String password, AsyncCallback<BackendlessUser> loginCallback){
        Backendless.UserService.login(email, password, loginCallback);
    }

    public LoadingCallback<BackendlessUser> createLoginCallback(){
        return new LoadingCallback<BackendlessUser>(this, getString(R.string.loading_login)){
            @Override
            public void handleResponse(BackendlessUser response) {
                super.handleResponse(response);
                Intent intent = new Intent(SignIn.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
