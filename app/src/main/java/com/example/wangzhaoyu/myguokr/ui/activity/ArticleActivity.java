package com.example.wangzhaoyu.myguokr.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.ui.fragment.ArticleContentFragment;
import com.example.wangzhaoyu.myguokr.ui.fragment.PagerTextFragment;
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
            actionBar.setTitle(mSnapShot.getTitle());
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

        setUpMainFragment();
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
        replaceFragment(new PagerTextFragment(), PagerTextFragment.class.getSimpleName());
    }

    private void setUpMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new ArticleContentFragment()).commit();
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    /**
     * 启动时toolbar动画
     */
    private void startIntroAnimation() {
        int barSize = mAppBarLayout.getHeight();
        mAppBarLayout.setTranslationY(-barSize);
        mBootombar.setTranslationY(mBootombar.getHeight());
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
