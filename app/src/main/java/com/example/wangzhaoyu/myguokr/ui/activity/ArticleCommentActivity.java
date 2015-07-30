package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.KeyBoardUtils;
import com.example.wangzhaoyu.myguokr.databinding.ActivityArticleCommentsBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.service.ArticleService;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleCommentAdapter;
import com.example.wangzhaoyu.myguokr.ui.view.SendCommentButton;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class ArticleCommentActivity extends AppCompatActivity
        implements SendCommentButton.OnSendClickListener, ArticleCommentAdapter.ReplyCallback {
    private static final String TAG = ArticleCommentActivity.class.getSimpleName();
    public static final String ARTICLE_SNAPSHOT_KEY = "article_snapshot_key";
    private ActivityArticleCommentsBinding mBinding;
    private ArticleSnapShot mSnapShot;
    private ArrayList<ArticleReply> mReplies;
    private ArticleCommentAdapter mAdapter;
    private ArticleReply mReply;
    private ArticleService mArticleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_comments);
        mSnapShot = (ArticleSnapShot) getIntent().getSerializableExtra(ARTICLE_SNAPSHOT_KEY);

        mArticleService = HttpService.getInstance().getArticleService();

        //init tool bar
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("点评");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //init recycler view
        mReplies = new ArrayList<>();
        mAdapter = new ArticleCommentAdapter(this, mReplies, this);
        mBinding.replyRecycler.setHasFixedSize(true);
        mBinding.replyRecycler.setLayoutManager(
                new LinearLayoutManager(this));
        mBinding.replyRecycler.setAdapter(mAdapter);

        //init pull to refresh
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.initWithString("Guokr");

        mBinding.refeshLayout.setLoadingMinTime(500);
        mBinding.refeshLayout.setDurationToCloseHeader(750);
        mBinding.refeshLayout.setHeaderView(header);
        mBinding.refeshLayout.addPtrUIHandler(header);

        mBinding.refeshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 500);

        mBinding.refeshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                refresh();
            }
        });

        mBinding.replyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && manager.findFirstCompletelyVisibleItemPosition() > 0
                        && manager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1
                        && !mBinding.refeshLayout.isRefreshing()) {
                    loadMore();
                }
            }
        });

        //init send btn
        mBinding.btnSendComment.setOnSendClickListener(this);
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

    @Override
    public void onSendClickListener(View v) {
        if (validateComment()) {
//            commentsAdapter.addItem();
//            commentsAdapter.setAnimationsLocked(false);
//            commentsAdapter.setDelayEnterAnimation(false);
//            rvComments.smoothScrollBy(0, rvComments.getChildAt(0).getHeight() * commentsAdapter.getItemCount());

            String content = mBinding.editComment.getText().toString();
            //set reply
            if (mReply != null) {
                String replyContent = mReply.getContent();
                replyContent = replyContent.replaceAll("\\[blockquote\\].+\\[/blockquote\\]", "");
                replyContent = replyContent.replaceAll("\n", "");
                content = "[blockquote]" + "引用@" + mReply.getAuthor().getNickname() + " 的话：" + replyContent + "[/blockquote]"
                        + "\n" + content;
            }
            mArticleService.postArticleComment(mSnapShot.getId(),
                    content,
                    new Callback<ArticleSendComment>() {
                        @Override
                        public void success(ArticleSendComment comment, Response response) {
                            mBinding.editComment.setText(null);
                            mBinding.btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
        }
    }

    @Override
    public void onReplyClicked(ArticleReply reply) {
        mReply = reply;
        mBinding.editComment.setHint("回复" + mReply.getAuthor().getNickname() + ":");
        //wait edit text is ok
        mBinding.editComment.postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyBoardUtils.showSoftKeyboard(mBinding.editComment, ArticleCommentActivity.this);
            }
        }, 150);
    }

    private void refresh() {
        mArticleService.getArticleCommentList(
                mSnapShot.getId(),
                0,
                new Callback<ArticleReplies>() {
                    @Override
                    public void success(ArticleReplies replies, Response response) {
                        mReplies.clear();
                        mReplies.addAll(replies.getResult());
                        mAdapter.notifyDataSetChanged();
                        mBinding.refeshLayout.refreshComplete();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mBinding.refeshLayout.refreshComplete();
                    }
                });

    }

    private void loadMore() {
        mAdapter.loadStart();
        mArticleService.getArticleCommentList(
                mSnapShot.getId(),
                mReplies.size(),
                new Callback<ArticleReplies>() {
                    @Override
                    public void success(ArticleReplies replies, Response response) {
                        int beforeSize = mReplies.size();
                        mReplies.addAll(replies.getResult());
                        mAdapter.notifyContentItemRangeInserted(beforeSize, replies.getResult().size());
                        mBinding.refeshLayout.refreshComplete();
                        mAdapter.loadComplete();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mBinding.refeshLayout.refreshComplete();
                        mAdapter.loadComplete();
                    }
                });

    }

    private boolean validateComment() {
        if (TextUtils.isEmpty(mBinding.editComment.getText())) {
            mBinding.btnSendComment.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_error));
            return false;
        }

        return true;
    }
}
