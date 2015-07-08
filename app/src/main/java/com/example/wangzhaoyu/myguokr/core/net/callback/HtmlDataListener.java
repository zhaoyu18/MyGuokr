package com.example.wangzhaoyu.myguokr.core.net.callback;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.wangzhaoyu.myguokr.core.net.NetUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 用于请求html
 *
 * @author wangzhaoyu
 */
public abstract class HtmlDataListener implements Response.Listener<String>, Response.ErrorListener {
    private static final String TAG = DataListener.class.getSimpleName();

    public abstract void onRequestSuccess(String data);

    public abstract void onRequestError();

    @Override
    public void onResponse(String response) {
        Document doc = Jsoup.parse(response);
        String articleContent = doc.getElementById("articleContent").outerHtml();//.replaceAll("line-height: normal;", "");
        String copyright = doc.getElementsByClass("copyright").outerHtml();
        String content = articleContent + copyright;
        onRequestSuccess(NetUtils.getArticleHtml(content));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onRequestError();
    }
}
