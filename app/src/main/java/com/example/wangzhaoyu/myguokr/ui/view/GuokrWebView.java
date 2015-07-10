package com.example.wangzhaoyu.myguokr.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author wangzhaoyu
 */
public class GuokrWebView extends WebView {
    private PageLoadListener mPageLoadListener;

    public GuokrWebView(Context context) {
        super(context);
        init();
    }

    public GuokrWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuokrWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GuokrWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWebViewClient(client);//一旦设置了webViewClient，默认情况下链接就会在本页打开了……
        getSettings().setAllowFileAccess(true);
        getSettings().setAllowContentAccess(true);
        getSettings().setBlockNetworkImage(true);//暂时不加载图片，因为要延迟加载，只渲染文字还是比较快的
        getSettings().setDefaultTextEncodingName("UTF-8");
    }

    private WebViewClient client = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mPageLoadListener != null) {
                mPageLoadListener.onFinished(view, url);
            }
            //图片在此进行延迟加载
            getSettings().setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //TODO
            return true;
        }
    };

    public void setPageLoadListener(PageLoadListener listener) {
        mPageLoadListener = listener;
    }

    public interface PageLoadListener {
        void onFinished(WebView view, String url);
    }
}
