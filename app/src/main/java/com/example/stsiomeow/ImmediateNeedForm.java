package com.example.stsiomeow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ImmediateNeedForm extends AppCompatActivity {
    ImageView menu, profile;
    Button btnSubmit;

    // info sa namatay
    EditText dead_lname;
    EditText dead_mname;
    EditText dead_fname;
    EditText dead_birthdate;
    EditText dead_deathdate;
    EditText dead_deathtime;

    // info sa doctor
    EditText doctor_fullname;
    EditText doctor_emailadd;
    EditText doctor_birthdate;


    //info sa guardian
    EditText guard_lname;
    EditText guard_mname;
    EditText guard_fname;
    EditText guard_relationship;
    EditText guard_address;
    EditText guard_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_need_form);
        btnSubmit = (Button) findViewById(R.id.btnFormSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImmediateNeedForm.this, CoffinProduct.class));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImmediateNeedForm.this, UserProfile.class));
            }
        });

        dead_lname = (EditText) findViewById(R.id.dead_lname);
        dead_mname = (EditText) findViewById(R.id.dead_mname);
        dead_fname = (EditText) findViewById(R.id.dead_fname);
        dead_birthdate = (EditText) findViewById(R.id.dead_birthdate);
        dead_deathdate = (EditText) findViewById(R.id.dead_deathdate);
        dead_deathtime = (EditText) findViewById(R.id.dead_deathtime);

        doctor_fullname = (EditText) findViewById(R.id.doctor_fullname);
        doctor_emailadd = (EditText) findViewById(R.id.doctor_emailadd);
        doctor_birthdate = (EditText) findViewById(R.id.doctor_birthdate);

        guard_lname = (EditText) findViewById(R.id.guard_lname);
        guard_mname = (EditText) findViewById(R.id.guard_mname);
        guard_fname = (EditText) findViewById(R.id.guard_fname);
        guard_relationship = (EditText) findViewById(R.id.guard_relationship);
        guard_address = (EditText) findViewById(R.id.guard_address);
        guard_number = (EditText) findViewById(R.id.guard_number);
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
                startActivity(new Intent(ImmediateNeedForm.this, HomePage.class));
                return true;
            case R.id.cCremation:
                Toast.makeText(this, "Cremation Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ImmediateNeedForm.this, CremationProduct.class));
                return true;
            case R.id.cFuneralRite:
                Toast.makeText(this, "Funeral Rite", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ImmediateNeedForm.this, ImmediateNeedForm.class));
                return true;
            case R.id.cSendFlowers:
                Toast.makeText(this, "Send Flowers Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ImmediateNeedForm.this, SendFlowers.class));
                return true;
            case R.id.mOrbituary:
                Toast.makeText(this, "Death Archive Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ImmediateNeedForm.this, DeathArchive.class));
                return true;
            case R.id.mLogOut:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return false;
        }
    }
}import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImmediateNeedForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_need_form);
    }
}