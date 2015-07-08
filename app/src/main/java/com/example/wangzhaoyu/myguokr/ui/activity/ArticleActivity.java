package com.example.wangzhaoyu.myguokr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.net.callback.HtmlDataListener;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 文章详情页面
 *
 * @author wangzhaoyu
 */
public class ArticleActivity extends AppCompatActivity {
    public static final String TAG = ArticleActivity.class.getSimpleName();
    @InjectView(R.id.article_web)
    GuokrWebView mArticleWebView;
    @InjectView(R.id.article_image)
    ImageView mArticleImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingBar;

    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);

        ArticleSnapShot snapShot = (ArticleSnapShot) getIntent().getSerializableExtra("snapShot");

        //init tool bar
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(snapShot.getTitle());
        mCollapsingBar.setTitle(snapShot.getTitle());

        //init tool bar image
        ImageLoader.getInstance().displayImage(snapShot.getImage_info().getUrl(), mArticleImage,
                mDisImageOptions);

        //init web content
        ArticleServer.getInstance().getArticleDetail(snapShot.getUrl(), new HtmlDataListener() {
            @Override
            public void onRequestSuccess(String html) {
                mArticleWebView.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html",
                        "charset=UTF-8", null);
                Log.i(TAG, html);
            }

            @Override
            public void onRequestError() {

            }
        });
    }
}
