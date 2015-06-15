package com.example.wangzhaoyu.myguokr.server;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wangzhaoyu.myguokr.AppController;

import org.json.JSONObject;

/**
 * @author wangzhaoyu
 */
public class ArticleServer {
    private static final String TAG = ArticleServer.class.getSimpleName();

    public static void test() {
        String url = "http://api.androidhive.info/volley/person_object.json";
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        };
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, "",
                listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonObjectReq, TAG);
    }

}
