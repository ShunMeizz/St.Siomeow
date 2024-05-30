package com.example.stsiomeow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPage extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private CheckBox rememberMe;
    private Button loginButton, signUpButton;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        loginEmail = findViewById(R.id.eLEmail);
        loginPassword = findViewById(R.id.eLPassword);
        rememberMe = findViewById(R.id.cb_remember);
        loginButton = findViewById(R.id.btn_llogin);
        signUpButton = findViewById(R.id.btn_lsignup);
        auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);

        // Check if the user has previously opted for "Remember Me"
        if (sharedPreferences.getBoolean("rememberMe", false)) {
            loginEmail.setText(sharedPreferences.getString("email", ""));
            loginPassword.setText(sharedPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInPage.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LogInPage.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (rememberMe.isChecked()) {
                        sharedPreferences.edit()
                                .putBoolean("rememberMe", true)
                                .putString("email", email)
                                .putString("pass", password)
                                .apply();
                    } else {
                        sharedPreferences.edit()
                                .clear()
                                .apply();
                    }
                    Intent intent = new Intent(LogInPage.this, HomePage.class);
                    startActivity(intent);
//                    finish();
                } else {
                    Toast.makeText(LogInPage.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
