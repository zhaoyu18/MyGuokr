package com.example.wangzhaoyu.myguokr.network.callback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public abstract class GuokrCallback<T> implements Callback<T> {
    private String mTag;
    private boolean mIsCanceled;
    private Callback<T> mCallback;

    public GuokrCallback(String tag, Callback<T> callback) {
        mTag = tag;
        mCallback = callback;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        this.mTag = tag;
    }

    public void cancel() {
        mIsCanceled = true;
    }

    @Override
    public void success(T t, Response response) {
        if (!mIsCanceled) {
            mCallback.success(t, response);
        }
        response();
    }

    @Override
    public void failure(RetrofitError error) {
        if (!mIsCanceled) {
            mCallback.failure(error);
        }
        response();
    }

    public abstract void response();
}
