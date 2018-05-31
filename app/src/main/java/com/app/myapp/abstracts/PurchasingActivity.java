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
//    /*
//    Steps:
//    - Connect to app Play app
//    - Query purchases
//    - Initiate a purchase
//        - Query again
//        - Close connection
//    - Make process secure
//    - Test on alpha/beta channel
//     */
//    Button btnBackToYourLists;
//    TextView textViewMessageAboutGPlay;
//    TextView textViewExpandedListTitleAndPrice;
//    TextView textViewExpandedListDescription;
//    TextView textView1988OriginalListTitleAndPrice;
//    TextView textView1988OriginalListDescription;
//    Button btnBuyExpandedList;
//    Button btnBuy1988OriginalList;
//
//    private BillingClient mBillingClient;
//    private Activity mActivity = this;
//    private boolean mServiceIsConnected = false;
//    public final List<Purchase> mPurchases = new ArrayList<>();
//    private String base64EncodedPublicKey;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(com.app.myapp.abstracts.R.layout.activity_purchasing);
//
//        btnBackToYourLists = (Button) findViewById(R.id.btnBackToPickPPTs);
//        textViewMessageAboutGPlay = (TextView) findViewById(R.id.textViewMessageAboutGPlay);
//        textViewExpandedListTitleAndPrice = (TextView) findViewById(R.id.textViewExpandedListTitleAndPrice);
//        textViewExpandedListDescription = (TextView) findViewById(R.id.textViewExpandedListDescription);
//        textView1988OriginalListTitleAndPrice = (TextView) findViewById(R.id.textView1988OriginalListTitleAndPrice);
//        textView1988OriginalListDescription = (TextView) findViewById(R.id.textView1988OriginalListDescription);
//        btnBuyExpandedList = (Button) findViewById(R.id.btnBuyExpandedList);
//        btnBuy1988OriginalList = (Button) findViewById(R.id.btnBuy1988OriginalList);
//
//        btnBackToYourLists.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(PurchasingActivity.this, PickPPTListActivity.class));
//            }
//        });
//        btnBuyExpandedList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initiatePurchase("expanded_list");
//            }
//        });
//        btnBuy1988OriginalList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initiatePurchase("1988_original_list");
//            }
//        });
//
//        textViewMessageAboutGPlay.setText("");
//
//        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
//        queryPurchases();
//    }
//
//    public void queryPurchases() {
//        mPurchases.clear();
//        Runnable queryToExecute = new Runnable() {
//            @Override
//            public void run() {
//                Purchase.PurchasesResult purchases = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
//                mPurchases.addAll(purchases.getPurchasesList());
//            }
//        };
//        executeServiceRequest(queryToExecute);
//    }
//
//    public void executeServiceRequest(Runnable runnable) {
//        if(mServiceIsConnected)
//            runnable.run();
//        else
//            startConnection(runnable);
//    }
//
//    public void startConnection(final Runnable runnable) {
//        mBillingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
//                if (billingResponseCode == BillingClient.BillingResponse.OK) {
//                    mServiceIsConnected = true;
//                    runnable.run();
//                }
//                switch(billingResponseCode) {
//                    case BillingClient.BillingResponse.BILLING_UNAVAILABLE:
//                        textViewMessageAboutGPlay.setText(R.string.g_play_unavailable);
//                }
//            }
//            @Override
//            public void onBillingServiceDisconnected() {
//                mServiceIsConnected = false;
//            }
//        });
//    }
//
//    public void querySkuDetailsAsync() {
//        Runnable queryToExecute = new Runnable() {
//            @Override
//            public void run() {
//                List<String> skuList = new ArrayList<>();
//                skuList.add("expanded_list");
//                skuList.add("1988_original_list");
//                SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//                params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
//                mBillingClient.querySkuDetailsAsync(params.build(),
//                        new SkuDetailsResponseListener() {
//                            @Override
//                            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
//                                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
//                                    for (SkuDetails skuDetails : skuDetailsList) {
//                                        String sku = skuDetails.getSku();
//                                        String price = skuDetails.getPrice();
//                                        String description = skuDetails.getDescription();
//                                        if ("expanded_list".equals(sku)) {
//                                            textViewExpandedListTitleAndPrice.setText(textViewExpandedListTitleAndPrice.getText() + "\t" + price);
//                                            textView1988OriginalListDescription.setText(description);
//                                        } else if ("1988_original_list".equals(sku)) {
//                                            textView1988OriginalListTitleAndPrice.setText(textView1988OriginalListTitleAndPrice.getText() + "\t" + price);
//                                            textView1988OriginalListDescription.setText(description);
//                                        }
//                                    }
//                                }
//                            }
//                        });
//            }
//        };
//        executeServiceRequest(queryToExecute);
//    }
//
//    public void initiatePurchase(final String skuId) {
//        Runnable purchaseAction = new Runnable() {
//            @Override
//            public void run() {
//                BillingFlowParams flowParams = BillingFlowParams.newBuilder()
//                        .setSku(skuId)
//                        .setType(BillingClient.SkuType.INAPP) // SkuType.SUB for subscription
//                        .build();
//                int responseCode = mBillingClient.launchBillingFlow(flowParams);
//                BillingClient.BillingResponse billingResponseCode;
//                switch (billingResponseCode) {
//                    case BillingClient.BillingResponse.ITEM_ALREADY_OWNED:
//                        textViewMessageAboutGPlay.setText(R.string.g_play_item_already_owned);
//                        break;
//                }
//            }
//        };
//        executeServiceRequest(purchaseAction);
//    }

    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
//        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
//            for(Purchase p : purchases) {
//                try {
//                    if(verifyPurchase(base64EncodedPublicKey, p.getOriginalJson(), p.getSignature()))
//                        mPurchases.add(p);
//                } catch(IOException e) {
//                    textViewMessageAboutGPlay.setText(R.string.g_play_purchase_not_verified);
//                }
//            }
//        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
//            // Handle an error caused by a user cancelling the purchase flow.
//        } else {
//            // Handle any other error codes.
//        }
    }

//    public boolean verifyPurchase(String base64EncodedPublicKey, String signedData, String signature) throws IOException {
//        if (TextUtils.isEmpty(signedData) || TextUtils.isEmpty(base64EncodedPublicKey) || TextUtils.isEmpty(signature)) {
//            return false;
//        }
//        Signature signatureAlgorithm = Signature.getInstance()
//
//    }
//
//    public static PublicKey constructPublicKey() {
//
//    }
//
//    public void endConn() {
//        mBillingClient.endConnection();
//        mBillingClient = null;
//    }
}