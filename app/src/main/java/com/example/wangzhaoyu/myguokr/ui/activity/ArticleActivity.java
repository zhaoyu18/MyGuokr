package com.example.wangzhaoyu.myguokr.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.core.net.callback.HtmlDataListener;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
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
    private static final int ANIM_DURATION_TOOLBAR = 250;
    private static final int ANIM_DURATION_AUTHOR = 150;

    @InjectView(R.id.article_web)
    GuokrWebView mArticleWebView;
    @InjectView(R.id.article_image)
    ImageView mArticleImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingBar;
    @InjectView(R.id.article_author_avatar)
    ImageView mAuthorAvatar;
    @InjectView(R.id.article_author_name)
    TextView mAuthorName;
    @InjectView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @InjectView(R.id.article_author)
    LinearLayout mAuthor;

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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(snapShot.getTitle());
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingBar.setTitle(snapShot.getTitle());

        //init tool bar image
        ImageLoader.getInstance().displayImage(snapShot.getImage_info().getUrl(), mArticleImage,
                mDisImageOptions);
        ImageLoader.getInstance().displayImage(snapShot.getAuthor().getAvatar().getNormal(),
                mAuthorAvatar,
                ImageServer.getAvatarDisplayOptions(getResources().getDimensionPixelSize(R.dimen.article_avatar_size)));
        mAuthorName.setText(snapShot.getAuthor().getNickname());

        //init web content
        ArticleServer.getInstance().getArticleDetail(snapShot.getUrl(), new HtmlDataListener() {
            @Override
            public void onRequestSuccess(String html) {
                mArticleWebView.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html",
                        "charset=UTF-8", null);
            }

            @Override
            public void onRequestError() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        startIntroAnimation();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 启动时toolbar动画
     */
    private void startIntroAnimation() {
        int barSize = mAppBarLayout.getHeight();
        mArticleWebView.setPageLoadListener(new GuokrWebView.PageLoadListener() {
            @Override
            public void onFinished(WebView view, String url) {
                view.animate()
                        .translationY(0)
                        .setInterpolator(new DecelerateInterpolator(3.f))
                        .setDuration(600)
                        .start();
            }
        });
        mArticleWebView.setTranslationY(Utils.getScreenHeight(this));
        mAuthor.setTranslationY(-2 * mAuthor.getHeight());
        mAppBarLayout.setTranslationY(-barSize);
        mAppBarLayout.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAuthor.animate()
                                .translationY(0)
                                .setDuration(ANIM_DURATION_AUTHOR)
                                .start();
                    }
                })
                .start();

    }
}
