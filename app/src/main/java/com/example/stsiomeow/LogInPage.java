package com.example.stsiomeow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
                if (!validateEmail() | !validatePassword()){
                    System.out.println("Wrong Email or Password");
                    return;
                } else {
                    checkUser();
                }
            }
        });

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInPage.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateEmail(){
        String val = linEmail.getText().toString();
        if (val.isEmpty()){
            linEmail.setError("Username cannot be empty");
            return false;
        } else {
            linEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = linPassword.getText().toString();
        if (val.isEmpty()){
            linPassword.setError("Password cannot be empty");
            return false;
        } else {
            linPassword.setError(null);
            return true;
        }
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
                        sph.edit().putBoolean("rememberMe", true).putString("Email", email).putString("Password", password).apply();
                    } else {
                        sph.edit().clear().apply();
                    }
                }

                Intent intent = new Intent(LogInPage.this, MainActivity.class);
            }
        });
    }

    public void checkUser(){
        String userEmail = linEmail.getText().toString().trim();
        String userPassword = linPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    linEmail.setError(null);
                    String passwordFromDB = snapshot.child(userEmail).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        linEmail.setError(null);

                        //Pass the data using intent

                        String nameFromDB = snapshot.child(userEmail).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userEmail).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEmail).child("username").getValue(String.class);

                        Intent intent = new Intent(LogInPage.this, UserProfile.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    } else {
                        linPassword.setError("Invalid Credentials");
                        linPassword.requestFocus();
                    }
                } else {
                    linEmail.setError("User does not exist");
                    linEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}