package com.example.wangzhaoyu.myguokr.server.handler;

import android.content.Context;
import android.widget.Toast;

/**
 * @author wangzhaoyu
 */
public class DefaultServerHandler<T> implements ServerHandler<T> {
    private Context mContext;

    public DefaultServerHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void onRequestSuccess(T t) {
        onResponse();
    }

    @Override
    public void onRequestError() {
        onResponse();
        Toast.makeText(mContext, "请求错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetError() {
        onResponse();
        Toast.makeText(mContext, "请求错误", Toast.LENGTH_SHORT).show();

    }

    public void onResponse() {

    }
}
