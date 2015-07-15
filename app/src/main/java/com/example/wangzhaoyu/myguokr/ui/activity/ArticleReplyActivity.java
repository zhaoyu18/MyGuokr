package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivityArticleReplyBinding;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleReplyAdapter;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleReplyActivity extends AppCompatActivity {
    private static final String TAG = ArticleReplyActivity.class.getSimpleName();
    public static final String ARTICLE_SNAPSHOT_KEY = "article_snapshot_key";
    private ActivityArticleReplyBinding mBinding;
    private ArticleSnapShot mSnapShot;
    private ArrayList<ArticleReply> mReplies;
    private ArticleReplyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_reply);
        mSnapShot = (ArticleSnapShot) getIntent().getSerializableExtra(ARTICLE_SNAPSHOT_KEY);

        //init tool bar
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //init recycler view
        mReplies = new ArrayList<>();
        mAdapter = new ArticleReplyAdapter(this, mReplies);
        mBinding.replyRecycler.setHasFixedSize(true);
        mBinding.replyRecycler.setLayoutManager(
                new LinearLayoutManager(this));
        mBinding.replyRecycler.setAdapter(mAdapter);

        //request replies
        ArticleServer.getInstance().getArticleReplies(mSnapShot.getId(),
                new ServerHandler<ArrayList<ArticleReply>>() {
                    @Override
                    public void onRequestSuccess(ArrayList<ArticleReply> replies) {
                        mReplies.addAll(replies);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onRequestError() {

                    }

                    @Override
                    public void onNetError() {

                    }
                });
    }
}
