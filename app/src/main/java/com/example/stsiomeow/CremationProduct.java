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

public class CremationProduct extends AppCompatActivity {
    ImageView backButton, userProfile, urnImageView;
    ArrayList<ImageView> allUrns;
    Dialog dialogConfirm, dialogPayment, dialogSuccess;
    Button btnDialogCancel, btnDialogConfirm , btnDialogOk;
    ImageButton btnBank1, btnBank2, btnBank3, btnBank4;
    TextView urnPriceTag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cremation_product);
        backButton = findViewById(R.id.backButtonUrn);
        userProfile = findViewById(R.id.userProfileUrn);
        setupDialogs();
        initializeCremation();
        setupBankButtonListeners();
        setupCoffinClickListeners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CremationProduct.this, HomePage.class));
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CremationProduct.this, UserProfile.class));
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
                startActivity(new Intent(CremationProduct.this, HomePage.class));
            }
        });


    }
    private void setupCoffinClickListeners() {
        for (ImageView flower : allUrns) {
            flower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView clickedUrn = (ImageView) view;
                    int drawableRes = (int) clickedUrn.getTag();
                    double price = (double) clickedUrn.getTag(R.id.dynamicProdPrice);
                    urnImageView.setImageResource(drawableRes);
                    urnPriceTag.setText(String.format("$%.2f", price));
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
        urnImageView = dialogConfirm.findViewById(R.id.dynamicProdImage);
        urnPriceTag = dialogConfirm.findViewById(R.id.dynamicProdPrice);

        dialogPayment = createDialog(R.layout.custom_dialog_paymentbox);
        btnBank1 = dialogPayment.findViewById(R.id.btnGcash);
        btnBank2 = dialogPayment.findViewById(R.id.btnPaypal);
        btnBank3 = dialogPayment.findViewById(R.id.btnPaymaya);
        btnBank4 = dialogPayment.findViewById(R.id.btnBPI);

        dialogSuccess = createDialog(R.layout.custom_dialog_success);
        btnDialogOk = dialogSuccess.findViewById(R.id.btnDialogOk);
    }
    private Dialog createDialog(int layoutResId) {
        Dialog dialog = new Dialog(CremationProduct.this);
        dialog.setContentView(layoutResId);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);
        return dialog;
    }

    public void initializeCremation(){
        int[] urnBtnIds = {R.id.urn1, R.id.urn2, R.id.urn3, R.id.urn4, R.id.urn5,
                R.id.urn6, R.id.urn7, R.id.urn8, R.id.urn9, R.id.urn10};
        int[] urnDrawables = {
                R.drawable.urn1_adultblessing, R.drawable.urn2_butterfly_landingtoken,
                R.drawable.urn3_grecian, R.drawable.urn4_silver, R.drawable.urn5_angel,
                R.drawable.urn6_flying_doves, R.drawable.urn7_keepsake_heart, R.drawable.urn8_hummingbird,
                R.drawable.urn9_serenity, R.drawable.urn10_teardrop
        };
        double[] urnPrices = {
                924.00, 844.00, 1124.00, 1244.00, 2118.00, 1100.00, 918.00, 900.00, 818.00, 910.00
        };
        allUrns = new ArrayList<>();
        for (int i = 0; i < urnBtnIds.length; i++) {
            ImageView urnView = findViewById(urnBtnIds[i]);
            urnView.setTag(urnDrawables[i]);
            urnView.setTag(R.id.dynamicProdPrice, urnPrices[i]);
            allUrns.add(urnView);
        }
    }
}