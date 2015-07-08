package com.example.wangzhaoyu.myguokr.core.net;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;

import org.json.JSONObject;

/**
 * @author wangzhaoyu
 */
public class GuokrJsonRequest extends JsonObjectRequest {
    public GuokrJsonRequest(int method, String url, String requestBody,
                            Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public GuokrJsonRequest(String url, Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public GuokrJsonRequest(int method, String url, Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public GuokrJsonRequest(int method, String url, JSONObject jsonRequest,
                            Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public GuokrJsonRequest(String url, JSONObject jsonRequest,
                            Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    /**
     * 增加一个DataListener的构造方法
     *
     * @param method
     * @param url
     * @param requestBody
     * @param dataListener
     */
    public GuokrJsonRequest(int method, String url, String requestBody, DataListener dataListener) {
        super(method, url, requestBody, dataListener, dataListener);
    }
}
