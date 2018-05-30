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
//    private boolean mServiceIsConnected = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(com.app.myapp.abstracts.R.layout.activity_purchasing);
//
//        textViewMessageAboutGPlay = (TextView) findViewById(R.id.textViewMessageAboutGPlay);
//        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
//
//        queryPurchases();
//    }
//
//    public void queryPurchases() {
//        Runnable queryToExecute = new Runnable() {
//            @Override
//            public void run() {
//                Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
//                for(Purchase p : purchasesResult.getPurchasesList()) {
//
//                }
//            }
//        }
//    }
//
//    public void startConnection(final Runnable runnable) {
//        mBillingClient.startConnection(new BillingClientStateListener() {
//            @Override
//            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
//                if (billingResponseCode == BillingClient.BillingResponse.OK) {
//                    // The billing client is ready. You can query purchases here.
//                    mServiceIsConnected = true;
//                    runnable.run();
//                }
//                switch(billingResponseCode) {
//                    case BillingClient.BillingResponse.BILLING_UNAVAILABLE:
//                        textViewMessageAboutGPlay.setText(R.string.g_play_unavailable);
//                        break;
//
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
