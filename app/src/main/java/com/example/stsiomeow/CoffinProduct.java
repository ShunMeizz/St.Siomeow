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

public class CoffinProduct extends AppCompatActivity {
    ImageView backButton, userProfile, coffinImageView;
    ArrayList<ImageView> allCoffins;
    Dialog dialogConfirm, dialogPayment, dialogSuccess;
    Button btnDialogCancel, btnDialogConfirm , btnDialogOk;
    ImageButton btnBank1, btnBank2, btnBank3, btnBank4;
    TextView coffinPriceTag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffin_product);
        backButton = findViewById(R.id.backButtonCoffin);
        userProfile = findViewById(R.id.userProfileCoffin);
        setupDialogs();
        initializeCoffins();
        setupBankButtonListeners();
        setupCoffinClickListeners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoffinProduct.this, HomePage.class));
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoffinProduct.this, UserProfile.class));
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
                startActivity(new Intent(CoffinProduct.this, HomePage.class));
            }
        });


    }
    private void setupCoffinClickListeners() {
        for (ImageView flower : allCoffins) {
            flower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView clickedCoffin = (ImageView) view;
                    int drawableRes = (int) clickedCoffin.getTag();
                    double price = (double) clickedCoffin.getTag(R.id.dynamicProdPrice);
                    coffinImageView.setImageResource(drawableRes);
                    coffinPriceTag.setText(String.format("$%.2f", price));
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
        coffinImageView = dialogConfirm.findViewById(R.id.dynamicProdImage);
        coffinPriceTag = dialogConfirm.findViewById(R.id.dynamicProdPrice);

        dialogPayment = createDialog(R.layout.custom_dialog_paymentbox);
        btnBank1 = dialogPayment.findViewById(R.id.btnGcash);
        btnBank2 = dialogPayment.findViewById(R.id.btnPaypal);
        btnBank3 = dialogPayment.findViewById(R.id.btnPaymaya);
        btnBank4 = dialogPayment.findViewById(R.id.btnBPI);

        dialogSuccess = createDialog(R.layout.custom_dialog_success);
        btnDialogOk = dialogSuccess.findViewById(R.id.btnDialogOk);
    }
    private Dialog createDialog(int layoutResId) {
        Dialog dialog = new Dialog(CoffinProduct.this);
        dialog.setContentView(layoutResId);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);
        return dialog;
    }

    public void initializeCoffins(){
        int[] coffinBtnIds = {R.id.coffin1, R.id.coffin2, R.id.coffin3, R.id.coffin4, R.id.coffin5,
                R.id.coffin6};
        int[] coffinDrawables = {
                R.drawable.coffin1_artisan, R.drawable.coffin2_farmer,
                R.drawable.coffin3_prayinghands_spruce, R.drawable.coffin4_primrose, R.drawable.coffin5_sterling_copper_casket,
                R.drawable.coffin6_white_cross_lords_prayer
        };
        double[] coffinPrices = {
                1024.00, 1114.00, 1124.00, 1244.00, 1118.00, 1100.00
        };
        allCoffins = new ArrayList<>();
        for (int i = 0; i < coffinBtnIds.length; i++) {
            ImageView coffinView = findViewById(coffinBtnIds[i]);
            coffinView.setTag(coffinDrawables[i]);
            coffinView.setTag(R.id.dynamicProdPrice, coffinPrices[i]);
            allCoffins.add(coffinView);
        }
    }
}