package com.example.wangzhaoyu.myguokr.core;

import android.databinding.BindingAdapter;

import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;
import com.example.wangzhaoyu.myguokr.ui.view.ReplyTextView;

/**
 * @author wangzhaoyu
 */
public class TextUtils {

    @BindingAdapter({"bind:html"})
    public static void loadHtmlContent(GuokrWebView webView, String content) {
        webView.loadDataWithBaseURL(
                "http://www.guokr.com/",
                content,
                "text/html",
                "charset=UTF-8",
                null);
    }

    @BindingAdapter({"bind:html"})
    public static void loadHtml(ReplyTextView textView, String html) {
        textView.loadHtml(html);
    }
}
