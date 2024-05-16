package com.example.stsiomeow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class test2 extends AppCompatActivity {
    Button submit;
    EditText text_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        submit = (Button) findViewById(R.id.btnSubmit);
        text_2 = (EditText)findViewById(R.id.editTextText3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user");
                String user = text_2.getText().toString();

                HelperClass h = new HelperClass(user);
                myRef.child(user).setValue(h);
                Toast.makeText(test2.this, "test success", Toast.LENGTH_SHORT).show();

                Intent in = new Intent(test2.this, MainActivity.class);
                startActivity(in);
//                myRef.setValue("Hello, World!");
            }
        });
    }
}