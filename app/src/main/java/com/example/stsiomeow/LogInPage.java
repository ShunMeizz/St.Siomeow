package com.example.stsiomeow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInPage extends AppCompatActivity {
    //ALL VARIABLES NAMES STARTS WITH LIN aside from the Buttons
    EditText linEmail, linPassword;
    Button loginSubmit, registerSubmit;
    CheckBox linrememberMe;
    FirebaseAuth auth;
    private SharedPreferences sph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        linEmail = (EditText) findViewById(R.id.eLEmail);
        linPassword = (EditText) findViewById(R.id.eLPassword);
        loginSubmit = (Button) findViewById(R.id.btn_llogin);
        linrememberMe = (CheckBox) findViewById(R.id.cb_remember);
        registerSubmit = (Button) findViewById(R.id.btn_lsignup);
        auth = FirebaseAuth.getInstance();
        sph = getSharedPreferences("RememberMePref", Context.MODE_PRIVATE);

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser(){
        String email = linEmail.getText().toString().trim();
        String password = linPassword.getText().toString().trim();
        Boolean rememberMe = linrememberMe.isChecked();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LogInPage.this, "All fields are needed!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(linrememberMe.isChecked()){
                        sph.edit().putBoolean("rememberMe", true);
                    }
                }
            }
        });
    }
}