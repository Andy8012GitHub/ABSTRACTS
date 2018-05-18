package com.example.wilsoash005.abstracts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PickPPTListActivity extends AppCompatActivity {
//    Button btnGetMoreLists;
    TextView textViewBasicFreeList;

    static ArrayList<PPTList> lists = new ArrayList<>();
    static String fileNameOfListChosen = "ABSTRACTS PPT - basic";
//    boolean gPlayEnabled = false;

    //in place of boolean value isEnabled, call method to check whether user has made a purchase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_pptlist);

        lists.clear();
        fileNameOfListChosen = "";

//        btnGetMoreLists = (Button) findViewById(R.id.btnGetMoreLists);
        textViewBasicFreeList = (TextView) findViewById(R.id.textViewBasicFreeList);

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

    public void handleListPurchasedOrNot(Dialog dialog, ImageButton imgBtn, String fileNameOfList, boolean isEnabled) {
        if (!fileNameOfList.equals("ABSTRACTS PPT - basic")) {
//        if(!gPlayEnabled) {
//            textViewPurchaseToEnable.setText(R.string.you_need_g_play);
//            dialog.show();
//        }
//        else {
            int id = imgBtn.getId();
            lists.add(new PPTList(id, fileNameOfList, isEnabled));
            int index = 0;
            while (lists.get(index).id != id) {
                index++;
            }
            if (!lists.get(index).isEnabled) {
                dialog.show();
                return;
            }
//        }
        }
        fileNameOfListChosen = fileNameOfList;
        startActivity(new Intent(PickPPTListActivity.this, CreateTeams.class));
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