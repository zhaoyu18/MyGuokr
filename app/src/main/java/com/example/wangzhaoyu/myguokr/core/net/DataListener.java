package com.example.wangzhaoyu.myguokr.core.net;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.wangzhaoyu.myguokr.AppController;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 可以解析model的
 * @author wangzhaoyu
 */
public abstract class DataListener<T> implements Response.Listener<JSONObject>, Response.ErrorListener {
    private static final String TAG = DataListener.class.getSimpleName();
    private static Gson gson = AppController.getInstance().getGson();

    private Type typeOfT;

    public abstract void onRequestSuccess(T data);

    public abstract void onRequestError();

    //TODO 为啥？
    public DataListener() {
        Type type = getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type[] actualTypes = pType.getActualTypeArguments();
            typeOfT = actualTypes[0];
            Log.d("typeOfT", typeOfT.toString());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "Error: " + error.getMessage());
        onRequestError();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, response.toString());
        T articles = gson.fromJson(response.toString(), typeOfT);
        onRequestSuccess(articles);
    }
}
