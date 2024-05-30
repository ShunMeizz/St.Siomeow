package com.example.stsiomeow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DeathArchive extends AppCompatActivity {
    ImageView menu, profile;

    // search patay
    EditText search_bar;
    ImageButton search_button;

    // database change
    TextView txtDisplayFullName, txtDisplayFullName1, txtDisplayFullName2;
    TextView txtDeadDate, txtDeadDate1, txtDeadDate2;
    TextView txtDeadAbout, txtDeadAbout1, txtDeadAbout2;

    // button pages
    Button[] btnVisitDead;
    Button[] btnSendflower;
    Button[] btnSendLove;
    int[] heartCounts = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_archive);

        menu = findViewById(R.id.menu_bar);
        profile = findViewById(R.id.user_profile);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeathArchive.this, UserProfile.class));
            }
        });

        btnVisitDead = new Button[3];
        btnVisitDead[0] = findViewById(R.id.btnVisitDead);
        btnVisitDead[1] = findViewById(R.id.btnVisitDead1);
        btnVisitDead[2] = findViewById(R.id.btnVisitDead2);

        for (Button button : btnVisitDead) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DeathArchive.this, DeceasedProfile.class);
                    startActivity(intent);
                }
            });
        }

        btnSendflower = new Button[3];
        btnSendflower[0] = findViewById(R.id.btnSendflower);
        btnSendflower[1] = findViewById(R.id.btnSendflower1);
        btnSendflower[2] = findViewById(R.id.btnSendflower2);

        for (Button button : btnSendflower) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DeathArchive.this, SendFlowers.class);
                    startActivity(intent);
                }
            });
        }

        btnSendLove = new Button[3];
        btnSendLove[0] = findViewById(R.id.btnSendLove);
        btnSendLove[1] = findViewById(R.id.btnSendLove1);
        btnSendLove[2] = findViewById(R.id.btnSendLove2);

        for (int i = 0; i < heartCounts.length; i++) {
            heartCounts[i] = 0;
        }

        for (int i = 0; i < btnSendLove.length; i++) {
            btnSendLove[i].setText("❤️ " + heartCounts[i]);
        }

        for (int i = 0; i < btnSendLove.length; i++) {
            final int index = i;
            btnSendLove[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    heartCounts[index]++;
                    btnSendLove[index].setText("❤️ " + heartCounts[index]);
                }
            });
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.choice_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return handleMenuItemClick(item);
            }
        });
        popupMenu.show();
    }

//    @SuppressLint("NonConstantResourceId")
//    @SuppressLint("NonConstantResourceId")
    private boolean handleMenuItemClick(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mHome:
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeathArchive.this, HomePage.class));
                return true;
            case R.id.cCremation:
                Toast.makeText(this, "Cremation Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeathArchive.this, CremationProduct.class));
                return true;
            case R.id.cFuneralRite:
                Toast.makeText(this, "Funeral Rite", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeathArchive.this, ImmediateNeedForm.class));
                return true;
            case R.id.cSendFlowers:
                Toast.makeText(this, "Send Flowers Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeathArchive.this, SendFlowers.class));
                return true;
            case R.id.mOrbituary:
                Toast.makeText(this, "Death Archive Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeathArchive.this, DeathArchive.class));
                return true;
            case R.id.mLogOut:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return false;
        }
    }
}