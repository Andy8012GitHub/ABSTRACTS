package com.app.myapp.abstracts;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;

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

public class BillingManager implements PurchasesUpdatedListener {
    private BillingClient mBillingClient;
    private final Activity mActivity;
    private boolean mServiceIsConnected = false;
    private String base64EncodedPublicKey;
    public final List<Purchase> mPurchases = new ArrayList<>();
    public List<SkuDetails> mSkuDetailsList = new ArrayList<>();

    int messageForGUI = 32;

    public BillingManager(Activity activity) {
        mActivity = activity;
        queryPurchases();
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
        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    mServiceIsConnected = true;
                    runnable.run();
                } else { //case BillingClient.BillingResponse.BILLING_UNAVAILABLE: case BillingClient.BillingResponse.SERVICE_DISCONNECTED: default:
                    messageForGUI = R.string.g_play_unavailable;
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                mServiceIsConnected = false;
            }
        });
    }

    public void querySkuDetailsAsync() {
        messageForGUI = 32;
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {
                List<String> skuList = new ArrayList<>();
                skuList.add("expanded_list");
                skuList.add("1988_original_list");
                skuList.add("sports_list");
                skuList.add("kiddie_list");
                SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                mBillingClient.querySkuDetailsAsync(params.build(),
                        new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                                    mSkuDetailsList = skuDetailsList;
                                } else {
                                    messageForGUI = R.string.g_play_error;
                                }
                            }
                        });
            }
        };
        executeServiceRequest(queryToExecute);
    }

    public void initiatePurchase(final String skuId) {
        messageForGUI = 32;
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
        messageForGUI = 32;
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            mPurchases.clear();
            constructPublicKey();
            for (Purchase p : purchases) {
                if (verifyPurchase(base64EncodedPublicKey, p.getOriginalJson(), p.getSignature()))
                    mPurchases.add(p);
                else {
                    messageForGUI = R.string.g_play_not_verified;
                    return;
                }
            }
        }
        switch (responseCode) {
            case BillingClient.BillingResponse.ITEM_ALREADY_OWNED:
                messageForGUI = R.string.g_play_item_already_owned;
                break;
            case BillingClient.BillingResponse.USER_CANCELED:
                messageForGUI = R.string.g_play_user_canceled;
                break;
            default:
                messageForGUI = R.string.g_play_not_verified;
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
