package com.app.myapp.abstracts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.billingclient.api.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PickPPTListActivity extends AppCompatActivity {
    Button btnPPTToMain;
    TextView textViewBasicFree;
    TextView textViewExpanded;
    TextView textView1988Original;
//    Button btnGetMoreLists;

//    static ArrayList<PPTList> lists = new ArrayList<>();
    static String fileNameOfListChosen = "ABSTRACTS PPT - basic";
//    PurchasingActivity purchasingActivity = new PurchasingActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_pick_pptlist);

//        lists.clear();

        btnPPTToMain = (Button) findViewById(R.id.btnPPTToMain);
        textViewBasicFree = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewBasicFreeList);
        textViewExpanded = (TextView) findViewById(R.id.textViewExpandedList);
        textView1988Original = (TextView) findViewById(R.id.textView1988OriginalList);
//        btnGetMoreLists = (Button) findViewById(R.id.btnGetMoreLists);

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
        textViewExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNameOfListChosen = "ABSTRACTS PPT - expanded";
                startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
            }
        });
        textView1988Original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNameOfListChosen = "ABSTRACTS PPT - 1988 original";
                startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
            }
        });
//        btnGetMoreLists.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PickPPTListActivity.this, PurchasingActivity.class);
//                startActivity(intent);
//            }
//        });

//        purchasingActivity.queryPurchases();
//        for(Purchase p : purchasingActivity.mPurchases) {
//            switch (p.getSku()) {
//                case "ppt_expanded_list":
//                    textViewExpanded.setVisibility(View.VISIBLE);
//                    break;
//                case "ppt_1988_original_list":
//                    textView1988Original.setVisibility(View.VISIBLE);
//            }
//        }
    }
}



//class PPTList {
//    Integer id;
//    String fileNameOfList = "";
////    String sku = "";
//    boolean isEnabled;
//
//    public Integer getImgBtnId() {
//        return id;
//    }
//    public String getFileNameOfList() {
//        return fileNameOfList;
//    }
////    public String getSKU() {
////        return sku;
////    }
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//
//    public PPTList(int id, String fileNameOfList,/* String sku,*/ boolean isEnabled) {
//        this.id = id;
//        this.fileNameOfList = fileNameOfList;
////        this.sku = sku;
//        this.isEnabled = isEnabled;
//    }
//}