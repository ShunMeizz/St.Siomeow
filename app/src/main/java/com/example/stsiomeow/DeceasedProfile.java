package com.example.stsiomeow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeceasedProfile extends AppCompatActivity {
    ImageView backButtonOrbitiary;

    // for database changes
    TextView txtDeadHeader, txtDisplayFullName, txtDeadDate, txtDeadAbout;

    // button pages
    Button btnSendflower;
    Button btnSendLove;
    Button btnVisitShop;
    private int loveCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deceased_profile);

        btnSendflower = (Button) findViewById(R.id.btnSendflower);
        btnSendflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeceasedProfile.this, SendFlowers.class);
                startActivity(intent);
            }
        });


        btnSendLove = findViewById(R.id.btnSendLove);
        btnSendLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveCount++;
                btnSendLove.setText("❤️ GIVE LOVE (" + loveCount + ")");
            }
        });


        btnVisitShop = (Button) findViewById(R.id.btnVisitShop);
        btnVisitShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeceasedProfile.this, SendFlowers.class);
                startActivity(intent);
            }
        });
    }
}