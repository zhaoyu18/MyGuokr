package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.net.SimpleDataListener;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleList;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.ui.adapter.FeedAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class ScientificFragment extends Fragment {
    private View mRootView;
    @InjectView(R.id.rv_feed)
    RecyclerView mFeedRecycler;
    private FeedAdapter mFeedAdapter;

    public ScientificFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_scientific, container, false);
            ButterKnife.inject(this, mRootView);
            setupFeed();
        }
        return mRootView;
    }

    private void setupFeed() {
        ArticleServer.getArticleList(new SimpleDataListener<ArticleList>() {
            @Override
            public void onRequestSuccess(ArticleList data) {
                mFeedAdapter.setArticleList(data.getResult());
                mFeedAdapter.notifyDataSetChanged();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        mFeedRecycler.setLayoutManager(linearLayoutManager);

        mFeedAdapter = new FeedAdapter(getActivity(), new ArrayList<ArticleSnapShot>());
        mFeedRecycler.setAdapter(mFeedAdapter);
    }
}
