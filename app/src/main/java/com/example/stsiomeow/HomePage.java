package com.example.stsiomeow;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.stsiomeow.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    ImageView menu, profile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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
                startActivity(new Intent(HomePage.this, UserProfile.class));
            }
        });
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
    private boolean handleMenuItemClick(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mHome:
                Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomePage.this, HomePage.class));
                return true;
            case R.id.cCremation:
                Toast.makeText(this, "Cremation Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomePage.this, CremationProduct.class));
                return true;
            case R.id.cFuneralRite:
                Toast.makeText(this, "Funeral Rite", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomePage.this, ImmediateNeedForm.class));
                return true;
            case R.id.cSendFlowers:
                Toast.makeText(this, "Send Flowers Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomePage.this, SendFlowers.class));
                return true;
            case R.id.mOrbituary:
                Toast.makeText(this, "Death Archive Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomePage.this, DeathArchive.class));
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