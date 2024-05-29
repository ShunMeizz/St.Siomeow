package com.example.stsiomeow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText regFname, regLname, regAdd, regEmail, regPass;
    CheckBox cbAlum;
    Button regSignIn, regSignUp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regFname = (EditText) findViewById(R.id.eRFname);
        regLname = (EditText) findViewById(R.id.eRLname);
        regAdd = (EditText) findViewById(R.id.eRAddress);
        regEmail = (EditText) findViewById(R.id.eREmailAdd);
        regPass = (EditText) findViewById(R.id.eRPassword);
        cbAlum = (CheckBox) findViewById(R.id.cbRAlumni);
        regSignIn = (Button) findViewById(R.id.btn_rSignin);
        regSignUp = (Button) findViewById(R.id.btn_rSignup);

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database;
        DatabaseReference myRef;

        regSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LogInPage.class);
                startActivity(intent);
            }
        });

        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    @SuppressLint("NotConstructor")
    public void Register(){
        String firstName = regFname.getText().toString().trim();
        String lastName = regLname.getText().toString().trim();
        String remail = regEmail.getText().toString().trim();
        String address = regAdd.getText().toString().trim();
        String password = regPass.getText().toString().trim();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String uid = firebaseUser.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(uid);

        if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)){
            Toast.makeText(Register.this, "User's name is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(Register.this, "Password field is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(remail)){
            Toast.makeText(Register.this, "Email address is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(address)){
            Toast.makeText(Register.this, "Address is empty!", Toast.LENGTH_SHORT).show();
            return;
        }


        HelperClass hc = new HelperClass(uid, remail, password, address, firstName, lastName);
        myRef.child(uid).setValue(hc);

        Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Register.this, HomePage.class);
        startActivity(intent);
//        finish();

//        auth.createUserWithEmailAndPassword(remail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    FirebaseUser firebaseUser = auth.getCurrentUser();
//
//                    if(firebaseUser != null){
//                        String uid = firebaseUser.getUid();
//
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database.getReference("Users").child(uid);
//
////                        HelperClass hcb = new HelperClassBuilder("User")
////                                .firstName(firstName)
////                                .lastName(lastName)
////                                .email(remail)
////                                .password(password)
////                                .address(address)
////                                .createHelperClass();
////                        myRef.setValue(hcb).addOnCompleteListener(new OnCompleteListener<Void>() {
////                            @Override
////                            public void onComplete(@NonNull Task<Void> task) {
////                                if(task.isSuccessful()){
//                                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(Register.this, LogInPage.class);
//                                    startActivity(intent);
//                                    finish();
////                                } else{
////                                    Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_SHORT).show();
////                                    return;
////                                }
////                            }
////                        });
//                    }
//                }
//            }
//        });
    }
}