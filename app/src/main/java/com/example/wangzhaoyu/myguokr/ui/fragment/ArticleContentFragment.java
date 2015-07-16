package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.core.net.callback.HtmlDataListener;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class ArticleContentFragment extends Fragment {
    private static final int ANIM_DURATION_AUTHOR = 150;
    private static final int ANIM_DURATION_WEB = 600;

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
    private View mRootView;
    private ArticleSnapShot mSnapShot;
    private boolean mPendingIntroAnimation = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_article_content, container, false);
            ButterKnife.inject(this, mRootView);
            initView();
        }
        return mRootView;
    }

    private void initView() {
//        mSnapShot = ((ArticleActivity) getActivity()).getSnapShot();
        ImageLoader.getInstance().displayImage(mSnapShot.getAuthor().getAvatar().getNormal(),
                mArticleAuthorAvatar,
                ImageServer.getAvatarDisplayOptions(getResources().getDimensionPixelSize(R.dimen.article_avatar_size)));
        mArticleAuthorName.setText(mSnapShot.getAuthor().getNickname());

        //init web content
        ArticleServer.getInstance().getArticleDetail(mSnapShot.getUrl(), new HtmlDataListener() {
            @Override
            public void onRequestSuccess(String html) {
                mArticleWeb.loadDataWithBaseURL("http://www.guokr.com/", html, "text/html",
                        "charset=UTF-8", null);
            }

            @Override
            public void onRequestError() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPendingIntroAnimation) {
            startIntroAnimation();
            mPendingIntroAnimation = false;
        }
    }

    private void startIntroAnimation() {
        mArticleContent.setTranslationY(Utils.getScreenHeight(getActivity()));

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
}
