package com.app.myapp.abstracts;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class PurchasingActivity extends AppCompatActivity {
    Button btnBackToYourLists;
    TextView textViewMessageAboutGPlay;
    TextView textViewExpandedListPriceAndTitle;
    TextView textViewExpandedListDescription;
    TextView textView1988OriginalListPriceAndTitle;
    TextView textView1988OriginalListDescription;
    TextView textViewSportsListPriceAndTitle;
    TextView textViewSportsListDescription;
    TextView textViewKiddieListPriceAndTitle;
    TextView textViewKiddieListDescription;
    Button btnBuyExpandedList;
    Button btnBuy1988OriginalList;

    BillingManager billingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_purchasing);

        btnBackToYourLists = (Button) findViewById(R.id.btnBackToPickPPTs);
        textViewMessageAboutGPlay = (TextView) findViewById(R.id.textViewMessageAboutGPlay);
        textViewExpandedListPriceAndTitle = (TextView) findViewById(R.id.textViewExpandedListPriceAndTitle);
        textViewExpandedListDescription = (TextView) findViewById(R.id.textViewExpandedListDescription);
        textView1988OriginalListPriceAndTitle = (TextView) findViewById(R.id.textView1988OriginalListPriceAndTitle);
        textView1988OriginalListDescription = (TextView) findViewById(R.id.textView1988OriginalListDescription);
        textViewSportsListPriceAndTitle = (TextView) findViewById(R.id.textViewSportsListPriceAndTitle);
        textViewSportsListPriceAndTitle = (TextView) findViewById(R.id.textViewSportsListDescription);
        textViewKiddieListPriceAndTitle = (TextView) findViewById(R.id.textViewKiddieListPriceAndTitle);
        textViewKiddieListPriceAndTitle = (TextView) findViewById(R.id.textViewKiddieListDescription);
        btnBuyExpandedList = (Button) findViewById(R.id.btnBuyExpandedList);
        btnBuy1988OriginalList = (Button) findViewById(R.id.btnBuy1988OriginalList);

        textViewMessageAboutGPlay.setText("");

        billingManager = new BillingManager(this);
        textViewMessageAboutGPlay.setText(getResources().getString(billingManager.messageForGUI));
        billingManager.querySkuDetailsAsync();
        textViewMessageAboutGPlay.setText(getResources().getString(billingManager.messageForGUI));
        if (billingManager.messageForGUI == 32){
            for (SkuDetails skuDetails : billingManager.mSkuDetailsList) {
                String sku = skuDetails.getSku();
                String price = skuDetails.getPrice();
                String description = skuDetails.getDescription();
                if ("expanded_list".equals(sku)) {
                    textViewExpandedListPriceAndTitle.setText(getString(R.string.price_and_title_with_placeholders, price, textViewExpandedListPriceAndTitle.getText()));
                    textView1988OriginalListDescription.setText(description);
                } else if ("1988_original_list".equals(sku)) {
                    textView1988OriginalListPriceAndTitle.setText(getString(R.string.price_and_title_with_placeholders, price, textView1988OriginalListPriceAndTitle.getText()));
                    textView1988OriginalListDescription.setText(description);
                } else if ("sports_list".equals(sku)) {
                    textViewSportsListPriceAndTitle.setText(getString(R.string.price_and_title_with_placeholders, price, textViewSportsListPriceAndTitle.getText()));
                    textViewSportsListDescription.setText(description);
                } else if ("kiddie_list".equals(sku)) {
                    textViewKiddieListPriceAndTitle.setText(getString(R.string.price_and_title_with_placeholders, price, textViewKiddieListPriceAndTitle.getText()));
                    textViewKiddieListDescription.setText(description);
                }
            }
        }

        btnBackToYourLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billingManager.endConn();
                startActivity(new Intent(PurchasingActivity.this, PickPPTListActivity.class));
            }
        });
        btnBuyExpandedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billingManager.initiatePurchase("expanded_list");
                textViewMessageAboutGPlay.setText(billingManager.messageForGUI);
            }
        });
        btnBuy1988OriginalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billingManager.initiatePurchase("1988_original_list");
                textViewMessageAboutGPlay.setText(billingManager.messageForGUI);
            }
        });
    }
}