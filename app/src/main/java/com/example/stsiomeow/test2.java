package com.example.stsiomeow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class test2 extends AppCompatActivity {
    Button submit;
    EditText text_2, text_3;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        submit = (Button) findViewById(R.id.btnSubmit);
        text_2 = (EditText)findViewById(R.id.editTextText3);
        text_3 = (EditText)findViewById(R.id.editTextText4);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user");
                String user = text_2.getText().toString().trim();
                String pass = text_3.getText().toString().trim();

                if(TextUtils.isEmpty(user)){
                    Toast.makeText(test2.this, "No user", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(test2.this, "No pass", Toast.LENGTH_SHORT).show();
                    return;
                }

//                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(
//                        new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(task.isSuccessful()){
//                                    Toast.makeText(test2.this, "test success", Toast.LENGTH_SHORT).show();
//                                    Intent in = new Intent(test2.this, MainActivity.class);
//                                    startActivity(in);
//                                    finish();
//                                } else{
//                                    Toast.makeText(test2.this, "test failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                );
                HelperClass h = new HelperClass(user, pass);
                myRef.child("user").setValue(h);
//                myRef.setValue("Hello, World!");
                Printer();
            }
        });
    }

    public void Printer(){
        DatabaseReference out = FirebaseDatabase.getInstance().getReference().child("user");
        out.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        String user = ds.child("user").getValue(String.class);
                        System.out.println(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}