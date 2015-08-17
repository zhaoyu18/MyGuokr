package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.support.v4.app.Fragment;

import com.example.wangzhaoyu.myguokr.core.RxUtils;

import rx.subscriptions.CompositeSubscription;

/**
 * @author wangzhaoyu
 */
public class BaseFragment extends Fragment {
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void onResume() {
        super.onResume();
        mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
    }

    @Override
    public void onStop() {
        super.onStop();
        RxUtils.unsubscribeIfNotNull(mSubscriptions);
    }
}
