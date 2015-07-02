package com.example.wangzhaoyu.myguokr.server;

import com.android.volley.Request;
import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.core.net.DataListener;
import com.example.wangzhaoyu.myguokr.core.net.GuokrJsonRequest;
import com.example.wangzhaoyu.myguokr.core.net.NetUtil;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleList;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class ArticleServer {
    private static final String TAG = ArticleServer.class.getSimpleName();
    private static Gson gson = AppController.getInstance().getGson();

    //单例
    private static class InstanceHolder {
        public static ArticleServer holder = new ArticleServer();
    }

    public static ArticleServer getInstance() {
        return InstanceHolder.holder;
    }


    /**
     * 获取科学人文章列表
     *
     * @param dataListener
     */
    public void getArticleList(DataListener<ArticleList> dataListener) {
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
