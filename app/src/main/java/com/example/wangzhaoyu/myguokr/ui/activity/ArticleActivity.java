package com.example.wangzhaoyu.myguokr.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.NetUtils;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.databinding.ActivityArticleBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;
import com.example.wangzhaoyu.myguokr.ui.widget.GlideCircleTransform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import rx.Observer;
import rx.Subscription;

/**
 * 文章详情页面
 *
 * @author wangzhaoyu
 */
public class ArticleActivity extends BaseActivity {
    public static final String TAG = ArticleActivity.class.getSimpleName();
    public static final String ARTICLE_SNAPSHOT_KEY = "article_snapshot_key";
    private static final int ANIM_DURATION_TOOLBAR = 250;
    private static final int ANIM_DURATION_BOTTOMBAR = 100;
    private static final int ANIM_DURATION_WEB = 600;

    private int mPreScrollY = 0;
    private boolean mIsBottombarShow = true;
    private ArticleSnapShot mSnapShot;
    private ActivityArticleBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);

        mSnapShot = (ArticleSnapShot) getIntent().getSerializableExtra(ARTICLE_SNAPSHOT_KEY);

        //init tool bar
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mBinding.collapsingToolbar.setTitle(mSnapShot.getTitle());
        mBinding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        //init tool bar image
        Glide.with(this)
                .load(mSnapShot.getImage_info().getUrl())
                .into(mBinding.articleImage);

        //on scroll
        mBinding.articleScollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        int deltaY = mBinding.articleScollView.getScrollY() - mPreScrollY;
                        mPreScrollY = mBinding.articleScollView.getScrollY();

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
        Glide.with(this)
                .load(mSnapShot.getAuthor().getAvatar().getLarge())
                .transform(new GlideCircleTransform(this))
                .animate(android.R.anim.fade_in)
                .into(mBinding.articleAuthorAvatar);

        mBinding.articleAuthorName.setText(mSnapShot.getAuthor().getNickname());

        //init web content
        Subscription subscribe = HttpService.getInstance().getArticleService().getArticleContent(
                mSnapShot.getId()).subscribe(new Observer<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response response) {
                Document doc = Jsoup.parse(new String(((TypedByteArray) response.getBody()).getBytes()));
                String articleContent = doc.getElementById("articleContent").outerHtml();
                String copyright = doc.getElementsByClass("copyright").outerHtml();
                String content = articleContent + copyright;
                String html = NetUtils.getArticleHtml(content);
                mBinding.articleWeb.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html",
                        "charset=UTF-8", null);
                startWebViewAnimation();
            }
        });
        mSubscriptions.add(subscribe);
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
        int barSize = mBinding.appbar.getHeight();
        mBinding.appbar.setTranslationY(-barSize);
        mBinding.articleBottomBar.setTranslationY(mBinding.articleBottomBar.getHeight());
        mBinding.articleContent.setTranslationY(Utils.getScreenHeight(this));
        mBinding.appbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mBinding.articleBottomBar.animate()
                                .translationY(0)
                                .setDuration(ANIM_DURATION_BOTTOMBAR)
                                .start();
                    }
                })
                .start();
    }

    private void startWebViewAnimation() {
        mBinding.articleWeb.setPageLoadListener(new GuokrWebView.PageLoadListener() {
            @Override
            public void onFinished(WebView view, String url) {
                mBinding.articleContent.animate()
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
            destPos = mBinding.articleBottomBar.getHeight();
        } else {
            startPos = mBinding.articleBottomBar.getHeight();
            destPos = 0;
        }

        mIsBottombarShow = !mIsBottombarShow;
        mBinding.articleBottomBar.setTranslationY(startPos);
        mBinding.articleBottomBar.animate()
                .translationY(destPos)
                .setDuration(ANIM_DURATION_BOTTOMBAR)
                .start();
    }

    public void onReplyBtnClicked(View view) {
        Intent intent = new Intent(this, ArticleCommentActivity.class);
        intent.putExtra(ArticleActivity.ARTICLE_SNAPSHOT_KEY, mSnapShot);
        this.startActivity(intent);
    }

    public void onLikeBtnClicked(View view) {
        Snackbar snackbar = Snackbar.make(mBinding.articleBottomBar,
                "请先登录",
                Snackbar.LENGTH_SHORT);

        snackbar.setAction("登录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        snackbar.show();
    }

    public void onAuthorAvatarClicked(View view) {
        String ukey = mSnapShot.getAuthor().getUkey();
        if (!TextUtils.isEmpty(ukey)) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra(UserInfoActivity.ARG_UKEY, ukey);
            startActivity(intent);
        }
    }
}
