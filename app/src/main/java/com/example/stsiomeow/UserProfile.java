package com.example.stsiomeow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SendFlowers extends AppCompatActivity {

    ImageView backButton, userProfile, flowerImageView;
    ArrayList<ImageView> allFlowers;
    Dialog dialogConfirm, dialogPayment, dialogSuccess;
    Button btnDialogCancel, btnDialogConfirm , btnDialogOk;
    ImageButton btnBank1, btnBank2, btnBank3, btnBank4;
    TextView flowerPriceTag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_flowers);
        backButton = findViewById(R.id.backButtonFlowers);
        userProfile = findViewById(R.id.userProfileFlowers);
        setupDialogs();
        initializeFlowers();
        setupBankButtonListeners();
        setupFlowerClickListeners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendFlowers.this, HomePage.class));
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendFlowers.this, UserProfile.class));
            }
        });
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirm.dismiss();
            }
        });
        btnDialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirm.dismiss();
                dialogPayment.show();
            }
        });
        btnDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSuccess.dismiss();
                startActivity(new Intent(SendFlowers.this, HomePage.class));
            }
        });


    }
    private void setupFlowerClickListeners() {
        for (ImageView flower : allFlowers) {
            flower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView clickedFlower = (ImageView) view;
                    int drawableRes = (int) clickedFlower.getTag();
                    double price = (double) clickedFlower.getTag(R.id.dynamicProdPrice);
                    flowerImageView.setImageResource(drawableRes);
                    flowerPriceTag.setText(String.format("$%.2f", price));
                    dialogConfirm.show();
                }
            });
        }
    }

    private void setupBankButtonListeners() {
        View.OnClickListener bankButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPayment.dismiss();
                dialogSuccess.show();
            }
        };

        btnBank1.setOnClickListener(bankButtonListener);
        btnBank2.setOnClickListener(bankButtonListener);
        btnBank3.setOnClickListener(bankButtonListener);
        btnBank4.setOnClickListener(bankButtonListener);
    }
    private void setupDialogs() {
        dialogConfirm = createDialog(R.layout.custom_dialog_confirmbox);
        btnDialogCancel = dialogConfirm.findViewById(R.id.btnDialogCancel);
        btnDialogConfirm = dialogConfirm.findViewById(R.id.btnDialogConfirm);
        flowerImageView = dialogConfirm.findViewById(R.id.dynamicProdImage);
        flowerPriceTag = dialogConfirm.findViewById(R.id.dynamicProdPrice);

        dialogPayment = createDialog(R.layout.custom_dialog_paymentbox);
        btnBank1 = dialogPayment.findViewById(R.id.btnGcash);
        btnBank2 = dialogPayment.findViewById(R.id.btnPaypal);
        btnBank3 = dialogPayment.findViewById(R.id.btnPaymaya);
        btnBank4 = dialogPayment.findViewById(R.id.btnBPI);

        dialogSuccess = createDialog(R.layout.custom_dialog_success);
        btnDialogOk = dialogSuccess.findViewById(R.id.btnDialogOk);
    }
    private Dialog createDialog(int layoutResId) {
        Dialog dialog = new Dialog(SendFlowers.this);
        dialog.setContentView(layoutResId);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);
        return dialog;
    }

    public void initializeFlowers(){
        int[] flowerBtnIds = {R.id.flower1, R.id.flower2, R.id.flower3, R.id.flower4, R.id.flower5,
                R.id.flower6, R.id.flower7, R.id.flower8, R.id.flower9, R.id.flower10,
                R.id.flower11, R.id.flower12};
        int[] flowerDrawables = {
                R.drawable.flower1_blossoming_heart, R.drawable.flower2_cherished_moments_red,
                R.drawable.flower3_cherished_moments_blue, R.drawable.flower4_divine_tribute_cross, R.drawable.flower5_eternal_friendship,
                R.drawable.flower6_love_and_honor_wreath, R.drawable.flower7_lush_greenery_basket, R.drawable.flower8_peace_lily,
                R.drawable.flower9_peach_blessings_garden, R.drawable.flower10_splendid_life_spray, R.drawable.flower11_sympathy_garden, R.drawable.flower12_tranquil_white_lillies_basket
        };
        double[] flowerPrices = {
                24.00, 14.00, 14.00, 94.00, 34.00, 118.00, 18.00, 19.00, 22.00, 110.00, 14.00, 23.00
        };
        allFlowers = new ArrayList<>();
        for (int i = 0; i < flowerBtnIds.length; i++) {
            ImageView flowerView = findViewById(flowerBtnIds[i]);
            flowerView.setTag(flowerDrawables[i]);
            flowerView.setTag(R.id.dynamicProdPrice, flowerPrices[i]);
            allFlowers.add(flowerView);
        }
    }
}
}