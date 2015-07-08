package com.example.wangzhaoyu.myguokr.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 文章详情页面
 *
 * @author wangzhaoyu
 */
public class ArticleActivity extends AppCompatActivity {
    @InjectView(R.id.article_web)
    GuokrWebView mArticleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);
        String html = getIntent().getStringExtra("html");
        mArticleWebView.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html", "charset=UTF-8", null);
    }
}
