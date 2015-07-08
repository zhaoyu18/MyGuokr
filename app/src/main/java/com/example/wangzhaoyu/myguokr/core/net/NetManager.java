package com.example.wangzhaoyu.myguokr.core.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;
import com.example.wangzhaoyu.myguokr.core.net.callback.HtmlDataListener;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class NetManager {
    private final static String TAG = NetManager.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private Context mContext;
    private Gson mGson = new Gson();

    //单例
    private static class InstanceHolder {
        public static NetManager holder = new NetManager();
    }

    public static NetManager getInstance() {
        return InstanceHolder.holder;
    }

    public void init(Context context) {
        this.mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mRequestQueue.start();
    }

    /**
     * 添加请求到队列 TODO tag目前没用
     *
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        //如果为空，设置默认tag
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    /**
     * 添加请求到队列
     *
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        //如果为空，设置默认tag
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    /**
     * 按照tag取消请求
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Gson getGson() {
        return mGson;
    }

    public void request(int method, String api, Map<String, String> params, DataListener dataListener) {
        String url = parsedUrl(api, params);
        Request request = new GuokrJsonRequest(method, url, "", dataListener);
        mRequestQueue.add(request);
    }

    public void request(int method, String url, DataListener dataListener) {
        Request request = new GuokrJsonRequest(method, url, "", dataListener);
        mRequestQueue.add(request);
    }

    public void requestHtml(String url, HtmlDataListener dataListener) {
        Log.i(TAG, url);
        Request request = new StringRequest(Request.Method.GET, url, dataListener, dataListener);
        mRequestQueue.add(request);
    }

    /**
     * 通过参数拼接url，root url + api + ? + params
     *
     * @param api
     * @param params
     * @return
     */
    private String parsedUrl(String api, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(Network.getAPIRootUrl());
        sb.append(api);

        if (null == params) {
            return sb.toString();
        } else {
            sb.append("?");
            for (String key : params.keySet()) {
                try {
                    sb.append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString().substring(0, sb.length() - 1);
        }
    }
}
