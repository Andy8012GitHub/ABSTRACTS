package com.app.myapp.abstracts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class PickPPTListActivity extends AppCompatActivity {
//    Button btnGetMoreLists;
    Button btnPPTToMain;
    TextView textViewBasicFreeList;

    static ArrayList<PPTList> lists = new ArrayList<>();
    static String fileNameOfListChosen = "ABSTRACTS PPT - basic";

    //in place of boolean value isEnabled, call method to check whether user has made a purchase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_pick_pptlist);

        lists.clear();

//        btnGetMoreLists = (Button) findViewById(R.id.btnGetMoreLists);
        textViewBasicFreeList = (TextView) findViewById(com.app.myapp.abstracts.R.id.textViewBasicFreeList);
        btnPPTToMain = (Button) findViewById(R.id.btnPPTToMain);

        btnPPTToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickPPTListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
//        btnGetMoreLists.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PickPPTListActivity.this, PurchasingActivity.class);
//                startActivity(intent);
//            }
//        });
        textViewBasicFreeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
            }
        });

    }
}



class PPTList {
    Integer id;
    String fileNameOfList = "";
//    String sku = "";
    boolean isEnabled;

    public Integer getImgBtnId() {
        return id;
    }
    public String getFileNameOfList() {
        return fileNameOfList;
    }
//    public String getSKU() {
//        return sku;
//    }
    public boolean isEnabled() {
        return isEnabled;
    }

    public PPTList(int id, String fileNameOfList,/* String sku,*/ boolean isEnabled) {
        this.id = id;
        this.fileNameOfList = fileNameOfList;
//        this.sku = sku;
        this.isEnabled = isEnabled;
    }
}