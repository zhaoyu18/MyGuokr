package com.example.wangzhaoyu.myguokr.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageButton;
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
import butterknife.OnClick;

/**
 * 文章详情页面
 *
 * @author wangzhaoyu
 */
public class ArticleActivity extends AppCompatActivity {
    public static final String TAG = ArticleActivity.class.getSimpleName();
    public static final String ARTICLE_SNAPSHOT_KEY = "article_snapshot_key";
    private static final int ANIM_DURATION_TOOLBAR = 250;
    private static final int ANIM_DURATION_BOTTOMBAR = 100;
    private static final int ANIM_DURATION_WEB = 600;

    @InjectView(R.id.article_image)
    ImageView mArticleImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingBar;
    @InjectView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @InjectView(R.id.article_bottom_bar)
    LinearLayout mBootombar;
    @InjectView(R.id.article_scoll_view)
    NestedScrollView mScrollView;
    @InjectView(R.id.article_author_avatar)
    ImageView mArticleAuthorAvatar;
    @InjectView(R.id.article_author_name)
    TextView mArticleAuthorName;
    @InjectView(R.id.article_author)
    LinearLayout mArticleAuthor;
    @InjectView(R.id.article_web)
    GuokrWebView mArticleWeb;
    @InjectView(R.id.article_content)
    LinearLayout mArticleContent;
    @InjectView(R.id.btnLike)
    ImageButton mBtnLike;
    @InjectView(R.id.btnComments)
    ImageButton mBtnComments;
    @InjectView(R.id.btnMore)
    ImageButton mBtnMore;

    private int mPreScrollY = 0;
    private boolean mIsBottombarShow = true;
    private ArticleSnapShot mSnapShot;

    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.inject(this);

        mSnapShot = (ArticleSnapShot) getIntent().getSerializableExtra(ARTICLE_SNAPSHOT_KEY);

        //init tool bar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingBar.setTitle(mSnapShot.getTitle());

        //init tool bar image
        ImageLoader.getInstance().displayImage(mSnapShot.getImage_info().getUrl(), mArticleImage,
                mDisImageOptions);

        //on scroll
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        int deltaY = mScrollView.getScrollY() - mPreScrollY;
                        mPreScrollY = mScrollView.getScrollY();

                        if (deltaY < -24 && !mIsBottombarShow) {
                            //显示
                            bottomBarAnimation();
                        } else if (deltaY > 24 && mIsBottombarShow) {
                            //隐藏
                            bottomBarAnimation();
                        }
                    }
                });

        //init author
        ImageLoader.getInstance().displayImage(
                mSnapShot.getAuthor().getAvatar().getNormal(),
                mArticleAuthorAvatar,
                ImageServer.getAvatarDisplayOptions(
                        getResources().getDimensionPixelSize(R.dimen.article_avatar_size)));
        mArticleAuthorName.setText(mSnapShot.getAuthor().getNickname());

        //init web content
        ArticleServer.getInstance().getArticleDetail(mSnapShot.getUrl(), new HtmlDataListener() {
            @Override
            public void onRequestSuccess(String html) {
                mArticleWeb.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html",
                        "charset=UTF-8", null);
                startWebViewAnimation();
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

    @OnClick(R.id.btnComments)
    public void OnCommentsClicked(View view) {
    }

    /**
     * 启动时toolbar动画
     */
    private void startIntroAnimation() {
        int barSize = mAppBarLayout.getHeight();
        mAppBarLayout.setTranslationY(-barSize);
        mBootombar.setTranslationY(mBootombar.getHeight());
        mArticleContent.setTranslationY(Utils.getScreenHeight(this));
        mAppBarLayout.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mBootombar.animate()
                                .translationY(0)
                                .setDuration(ANIM_DURATION_BOTTOMBAR)
                                .start();
                    }
                })
                .start();
    }

    private void startWebViewAnimation() {
        mArticleWeb.setPageLoadListener(new GuokrWebView.PageLoadListener() {
            @Override
            public void onFinished(WebView view, String url) {
                mArticleContent.animate()
                        .translationY(0)
                        .setInterpolator(new DecelerateInterpolator(3.f))
                        .setDuration(ANIM_DURATION_WEB)
                        .start();
            }
        });
    }

    private void bottomBarAnimation() {
        int startPos;
        int destPos;
        if (mIsBottombarShow) {
            startPos = 0;
            destPos = mBootombar.getHeight();
        } else {
            startPos = mBootombar.getHeight();
            destPos = 0;
        }

        mIsBottombarShow = !mIsBottombarShow;
        mBootombar.setTranslationY(startPos);
        mBootombar.animate()
                .translationY(destPos)
                .setDuration(ANIM_DURATION_BOTTOMBAR)
                .start();
    }

    public ArticleSnapShot getSnapShot() {
        return mSnapShot;
    }
}
