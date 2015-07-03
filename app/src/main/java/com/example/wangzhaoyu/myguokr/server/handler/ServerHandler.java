package com.example.wangzhaoyu.myguokr.server.handler;

/**
 * server回调
 *
 * @author wangzhaoyu
 */
public interface ServerHandler<T> {
    public void onRequestSuccess(T t);

    public void onRequestError();

    public void onNetError();
}
