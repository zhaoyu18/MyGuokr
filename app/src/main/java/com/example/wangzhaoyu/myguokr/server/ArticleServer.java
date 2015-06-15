package com.example.wangzhaoyu.myguokr.server;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.core.net.DataListener;
import com.example.wangzhaoyu.myguokr.core.net.GuokrJsonRequest;
import com.example.wangzhaoyu.myguokr.core.net.NetUtil;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleList;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class ArticleServer {
    private static final String TAG = ArticleServer.class.getSimpleName();
    private static Gson gson = AppController.getInstance().getGson();

    public static void test2() {
        String url = "http://www.guokr.com/apis/minisite/article.json";
        Map<String, String> params = new HashMap<>();
        params.put("retrieve_type", "by_subject");
        params.put("limit", "20");
        params.put("offset", "0");
        url = NetUtil.generateFullUrl(url, params);

//        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
////                Log.d(TAG, response.toString());
//                ArticleList articles = gson.fromJson(response.toString(), ArticleList.class);
//                Log.d(TAG, articles.getResult().get(0).getTitle());
//            }
//        };
//        Response.ErrorListener errorListener = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//            }
//        };

        GuokrJsonRequest jsonRequest = new GuokrJsonRequest(Request.Method.GET, url, "",
                new DataListener<ArticleList>() {
                    @Override
                    public void onRequestSuccess(ArticleList data) {
                        Log.i(TAG, data.getResult().get(0).getTitle());
                    }

                    @Override
                    public void onRequestError() {

                    }
                });

//        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, "",
//                listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonRequest, TAG);
    }

    /**
     * 获取科学人文章列表
     *
     * @param dataListener
     */
    public static void getArticleList(DataListener<ArticleList> dataListener) {
        String url = "http://www.guokr.com/apis/minisite/article.json";
        Map<String, String> params = new HashMap<>();
        params.put("retrieve_type", "by_subject");
        params.put("limit", "20");
        params.put("offset", "0");
        url = NetUtil.generateFullUrl(url, params);
        GuokrJsonRequest jsonRequest = new GuokrJsonRequest(Request.Method.GET, url, "", dataListener);
        AppController.getInstance().addToRequestQueue(jsonRequest, TAG);
    }
}
