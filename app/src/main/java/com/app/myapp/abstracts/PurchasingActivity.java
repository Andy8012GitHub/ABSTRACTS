//package com.app.myapp.abstracts;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.android.billingclient.api.BillingClient;
//import com.android.billingclient.api.BillingClientStateListener;
//import com.android.billingclient.api.Purchase;
//import com.android.billingclient.api.PurchasesUpdatedListener;
//import com.android.billingclient.api.SkuDetailsParams;
//import com.android.billingclient.api.SkuDetailsResponseListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PurchasingActivity extends AppCompatActivity implements PurchasesUpdatedListener {
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
//    TextView textViewMessageAboutGPlay;
//
//    private BillingClient mBillingClient;
//    private Activity mActivity = this;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(com.app.myapp.abstracts.R.layout.activity_purchasing);
//
//        textViewMessageAboutGPlay = (TextView) findViewById(R.id.textViewMessageAboutGPlay);
//
//        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
//        mBillingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
//                if (billingResponseCode == BillingClient.BillingResponse.OK) {
//                    // The billing client is ready. You can query purchases here.
//                    System.out.println("Billing client is ready! :)");
//                }
//                switch(billingResponseCode) {
//                    case BillingClient.BillingResponse.BILLING_UNAVAILABLE:
//                        textViewMessageAboutGPlay.setText(R.string.g_play_unavailable);
//                        break;
//                    case BillingClient.BillingResponse.OK:
//                        List skuList = new ArrayList<>();
//                        skuList.add("ppt_full");
//                        skuList.add("ppt_1988");
//                        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
//                        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
//                        mBillingClient.querySkuDetailsAsync(params.build(),
//                                new SkuDetailsResponseListener() {
//                                    @Override
//                                    public void onSkuDetailsResponse(int responseCode, List skuDetailsList) {
//                                        // Process the result.
//                                    }
//                                });
//                }
//            }
//            @Override
//            public void onBillingServiceDisconnected() {
//                // Try to restart the connection on the next request to
//                // app Play by calling the startConnection() method.
//            }
//        });
//    }
//
//    @Override
//    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
//
//    }
//}
