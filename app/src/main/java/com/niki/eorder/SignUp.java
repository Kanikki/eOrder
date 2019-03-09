package com.niki.eorder;

import android.content.Intent;
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
import com.niki.eorder.Utility.BackendlessSettings;
import com.niki.eorder.Utility.LoadingCallback;

public class SignUp extends AppCompatActivity {
    Button btnSignIn;
    EditText etFullName, etEmail, etPassword;
    ImageView ivSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initialize connection to backendless
        BackendlessSettings backendlessSettings = new BackendlessSettings();
        Backendless.initApp(this, backendlessSettings.applicationID, backendlessSettings.androidApiKey);

        etFullName = findViewById(R.id.et_full_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_already_have_sign_in);
        ivSignUp = findViewById(R.id.iv_sign_up);

        ivSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (fullName.equals("") || email.equals("") || password.equals("")){
                    Toast.makeText(SignUp.this, "All forms must be filled!", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(email)){
                    Toast.makeText(SignUp.this, "Email invalid!", Toast.LENGTH_SHORT).show();
                    etEmail.setText("");
                    etFullName.setText("");
                    etPassword.setText("");
                }
                else{
                   LoadingCallback<BackendlessUser> registrationCallback = createRegistrationCallback();
                   registrationCallback.showLoading();
                   registerUser(fullName, email, password, registrationCallback);
                    Toast.makeText(SignUp.this, "Thankyou for registered on e-Order", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(SignUp.this, SignIn.class);
                   startActivity(intent);
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void registerUser(String name, String email, String password, AsyncCallback<BackendlessUser> registrationCallback){
        BackendlessUser user = new BackendlessUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setProperty("name", name);

        Backendless.UserService.register(user, registrationCallback);
    }

    public LoadingCallback<BackendlessUser> createRegistrationCallback(){
        return new LoadingCallback<BackendlessUser>(this, "Sending registration request..."){
            @Override
            public void handleResponse(BackendlessUser response) {
                super.handleResponse(response);
            }
        };
    }
}
