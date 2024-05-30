package com.example.stsiomeow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    ImageView backButtonUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        backButtonUser = findViewById(R.id.backButtonUser);

        backButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserProfile.this, HomePage.class);
                startActivity(intent1);
            }
        });
    }
}