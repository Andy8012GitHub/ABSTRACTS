package com.app.myapp.abstracts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.aware.PublishConfig;
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

import java.io.IOException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

public class PurchasingActivity extends AppCompatActivity implements PurchasesUpdatedListener {
    /*
    Steps:
    - Connect to app Play app
    - Query purchases
    - Initiate a purchase
        - Query again
        - Close connection
    - Make process secure
    - Test on alpha/beta channel
     */
    Button btnBackToYourLists;
    TextView textViewMessageAboutGPlay;
    TextView textViewExpandedListTitleAndPrice;
    TextView textViewExpandedListDescription;
    TextView textView1988OriginalListTitleAndPrice;
    TextView textView1988OriginalListDescription;
    Button btnBuyExpandedList;
    Button btnBuy1988OriginalList;

    private BillingClient mBillingClient;
    private Activity mActivity = this;
    private boolean mServiceIsConnected = false;
    public final List<Purchase> mPurchases = new ArrayList<>();
    private String base64EncodedPublicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.app.myapp.abstracts.R.layout.activity_purchasing);

        btnBackToYourLists = (Button) findViewById(R.id.btnBackToPickPPTs);
        textViewMessageAboutGPlay = (TextView) findViewById(R.id.textViewMessageAboutGPlay);
        textViewExpandedListTitleAndPrice = (TextView) findViewById(R.id.textViewExpandedListTitleAndPrice);
        textViewExpandedListDescription = (TextView) findViewById(R.id.textViewExpandedListDescription);
        textView1988OriginalListTitleAndPrice = (TextView) findViewById(R.id.textView1988OriginalListTitleAndPrice);
        textView1988OriginalListDescription = (TextView) findViewById(R.id.textView1988OriginalListDescription);
        btnBuyExpandedList = (Button) findViewById(R.id.btnBuyExpandedList);
        btnBuy1988OriginalList = (Button) findViewById(R.id.btnBuy1988OriginalList);

        btnBackToYourLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PurchasingActivity.this, PickPPTListActivity.class));
            }
        });
        btnBuyExpandedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePurchase("expanded_list");
            }
        });
        btnBuy1988OriginalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePurchase("1988_original_list");
            }
        });

        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
        queryPurchases();
        querySkuDetailsAsync();
    }

    public void queryPurchases() {
        mPurchases.clear();
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {
                Purchase.PurchasesResult purchases = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
                mPurchases.addAll(purchases.getPurchasesList());
            }
        };
        executeServiceRequest(queryToExecute);
    }

    public void executeServiceRequest(Runnable runnable) {
        if(mServiceIsConnected)
            runnable.run();
        else
            startConnection(runnable);
    }

    public void startConnection(final Runnable runnable) {
        textViewMessageAboutGPlay.setText("");
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    mServiceIsConnected = true;
                    runnable.run();
                }
                switch(billingResponseCode) {
                    case BillingClient.BillingResponse.BILLING_UNAVAILABLE:
                        textViewMessageAboutGPlay.setText(R.string.g_play_unavailable);
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                mServiceIsConnected = false;
            }
        });
    }

    public void querySkuDetailsAsync() {
        textViewMessageAboutGPlay.setText("");
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {
                List<String> skuList = new ArrayList<>();
                skuList.add("expanded_list");
                skuList.add("1988_original_list");
                SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                mBillingClient.querySkuDetailsAsync(params.build(),
                        new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                                    for (SkuDetails skuDetails : skuDetailsList) {
                                        String sku = skuDetails.getSku();
                                        String price = skuDetails.getPrice();
                                        String description = skuDetails.getDescription();
                                        if ("expanded_list".equals(sku)) {
                                            textViewExpandedListTitleAndPrice.setText(textViewExpandedListTitleAndPrice.getText() + "\t" + price);
                                            textView1988OriginalListDescription.setText(description);
                                        } else if ("1988_original_list".equals(sku)) {
                                            textView1988OriginalListTitleAndPrice.setText(textView1988OriginalListTitleAndPrice.getText() + "\t" + price);
                                            textView1988OriginalListDescription.setText(description);
                                        }
                                    }
                                }
                            }
                        });
            }
        };
        executeServiceRequest(queryToExecute);
    }

    public void initiatePurchase(final String skuId) {
        Runnable purchaseAction = new Runnable() {
            @Override
            public void run() {
                BillingFlowParams flowParams = BillingFlowParams.newBuilder().setSku(skuId).setType(BillingClient.SkuType.INAPP).build();
                int responseCode = mBillingClient.launchBillingFlow(mActivity, flowParams);
            }
        };
        executeServiceRequest(purchaseAction);
    }

    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        textViewMessageAboutGPlay.setText("");
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            mPurchases.clear();
            constructPublicKey();
            for (Purchase p : purchases) {
                if (verifyPurchase(base64EncodedPublicKey, p.getOriginalJson(), p.getSignature()))
                    mPurchases.add(p);
                else {
                    textViewMessageAboutGPlay.setText(R.string.g_play_error);
                    return;
                }
            }
        }
        switch (responseCode) {
            case BillingClient.BillingResponse.ITEM_ALREADY_OWNED:
                textViewMessageAboutGPlay.setText(R.string.g_play_item_already_owned);
                break;
            case BillingClient.BillingResponse.USER_CANCELED:
                textViewMessageAboutGPlay.setText(R.string.g_play_user_canceled);
                break;
            default:
                textViewMessageAboutGPlay.setText(R.string.g_play_error);
                break;
        }
    }

    public void constructPublicKey() {
        String wrongKey = "HDD=Dj<I=gkqhkiB4w+=<Q@A<<J><Q3<HDD=>gF><Q@<hjUFu02*yqGUQFpe,ZjAm<R*g>.-qqr-d+&T0hglYZXDkireepmknqyK1yyz.*Fy0Gpl4.>?cl&?1qw@4&TdA1b/qSFDQUh/giq&+UuBtAo0SFTB2WdmdB@wru4fwrpDgzQ4cm4SdUfmUkWmq-kg,1KYIik2Q2a4kb/wud2krfSAomYqar.=-JIVIAQcUv2Apaty-k/EQpDGXyGUofqlhrDvi=nhK*+hRXTI0nQqwKtShjhG&jSVso1Eh,/vTv+pJTgd&SK.pJcCZa&ch/y02@FEb?0R*>qQlyfkZYD3S4j/J/fD?CE0ygXTk<JjHm?11WGomr4chHRjYe?A=rwS-wD?<Q<=";
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : wrongKey.toCharArray()) {
            if (ch <= 75)
                stringBuilder.append((char) (ch + 5));
            else
                stringBuilder.append((char) ch);
        }
        base64EncodedPublicKey = stringBuilder.toString();
    }

    public boolean verifyPurchase(String base64EncodedPublicKey, String signedData, String signature) {
        boolean isVerified = false;
        if (TextUtils.isEmpty(signedData) || TextUtils.isEmpty(base64EncodedPublicKey) || TextUtils.isEmpty(signature)) {
            return isVerified;
        }
        if (signature.contains(base64EncodedPublicKey)) {
            isVerified = true;
        }
        return isVerified;
    }

    public void endConn() {
        mBillingClient.endConnection();
        mBillingClient = null;
    }
}