package com.app.myapp.abstracts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.billingclient.api.Purchase;

import java.util.ArrayList;


public class PickPPTListActivity extends AppCompatActivity {
    Button btnPPTToMain;
    TextView textViewBasicFree;
    TextView textViewExpanded;
    TextView textView1988Original;
    Button btnGetMoreLists;

    Dialog dialog;
    LayoutInflater inflater;
    View dialogView;
    Button btnDialogGetMoreLists;
    Button btnNotNow;

    static String fileNameOfListChosen = "ABSTRACTS PPT - basic";
    PurchasingActivity purchasingActivity = new PurchasingActivity();
    ArrayList<String> skus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_pick_pptlist);
        dialog = new Dialog(this);
        inflater = LayoutInflater.from(this);
        dialogView = inflater.inflate(R.layout.dialog_purchase_to_enable, null);

        btnPPTToMain = (Button) findViewById(R.id.btnPPTToMain);
        textViewBasicFree = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewBasicFreeList);
        textViewExpanded = (TextView) findViewById(R.id.textViewExpandedList);
        textView1988Original = (TextView) findViewById(R.id.textView1988OriginalList);
        btnGetMoreLists = (Button) findViewById(R.id.btnGetMoreLists);
        btnDialogGetMoreLists = (Button) dialogView.findViewById(R.id.btnDialogGetMoreLists);
        btnNotNow = (Button) dialogView.findViewById(R.id.btnNotNow);

        btnPPTToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PickPPTListActivity.this, MainActivity.class));
            }
        });
        textViewBasicFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
            }
        });

        purchasingActivity.queryPurchases();
        for (Purchase p : purchasingActivity.mPurchases) {
            skus.add(p.getSku());
        }

        textViewExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (skus.contains("ppt_expanded_list")) {
                    fileNameOfListChosen = "ABSTRACTS PPT - expanded";
                    startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
                } else
                    showYouMustBuyDialog(dialog, dialogView);
            }
        });
        textView1988Original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skus.contains("ppt_1988_original_list")) {
                    fileNameOfListChosen = "ABSTRACTS PPT - 1988 original";
                    startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
                } else
                    showYouMustBuyDialog(dialog, dialogView);
            }
        });
        btnGetMoreLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickPPTListActivity.this, PurchasingActivity.class);
                startActivity(intent);
            }
        });
        btnDialogGetMoreLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickPPTListActivity.this, PurchasingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showYouMustBuyDialog(final Dialog dialog, View dialogView) {
        dialog.setContentView(dialogView);
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}