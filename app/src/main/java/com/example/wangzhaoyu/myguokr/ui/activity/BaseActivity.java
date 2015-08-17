package com.example.wangzhaoyu.myguokr.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.wangzhaoyu.myguokr.core.RxUtils;

import rx.subscriptions.CompositeSubscription;

/**
 * @author wangzhaoyu
 */
public class BaseActivity extends AppCompatActivity {
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxUtils.unsubscribeIfNotNull(mSubscriptions);
    }
}
