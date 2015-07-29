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
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.net.KeyBoardUtils;
import com.example.wangzhaoyu.myguokr.databinding.ActivityArticleCommentsBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleCommentAdapter;
import com.example.wangzhaoyu.myguokr.ui.view.SendCommentButton;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_comments);
        mSnapShot = (ArticleSnapShot) getIntent().getSerializableExtra(ARTICLE_SNAPSHOT_KEY);

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
//        MoocGlassesHeaderView header = new MoocGlassesHeaderView(this);
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
//        header.setUp(mBinding.refeshLayout);
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

    //TODO 上拉加载更多
    private void loadMore() {
        mAdapter.loadStart();
        ArticleServer.getInstance().loadMoreArticleReplies(mSnapShot.getId(), mReplies.size(),
                new DefaultServerHandler<ArrayList<ArticleReply>>(this) {
                    @Override
                    public void onRequestSuccess(ArrayList<ArticleReply> articleReplies) {
                        super.onRequestSuccess(articleReplies);
                        int beforeSize = mReplies.size();
                        mReplies.addAll(articleReplies);
                        mAdapter.notifyContentItemRangeInserted(beforeSize, articleReplies.size());
                    }

                    @Override
                    public void onResponse() {
                        mBinding.refeshLayout.refreshComplete();
                        mAdapter.loadComplete();
                    }
                }
        );
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
            ArticleServer.getInstance().sendArticleComment(mSnapShot.getId(),
                    content,
                    new DefaultServerHandler<ArticleSendComment>(ArticleCommentActivity.this) {

                        @Override
                        public void onResponse() {
                            super.onResponse();
                            mBinding.editComment.setText(null);
                            mBinding.btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
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
        ArticleServer.getInstance().getArticleReplies(mSnapShot.getId(),
                new DefaultServerHandler<ArrayList<ArticleReply>>(this) {
                    @Override
                    public void onRequestSuccess(ArrayList<ArticleReply> replies) {
                        super.onRequestSuccess(replies);
                        mReplies.clear();
                        mReplies.addAll(replies);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onResponse() {
                        mBinding.refeshLayout.refreshComplete();
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
