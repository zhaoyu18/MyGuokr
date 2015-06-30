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
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.RentalsSunHeaderView;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.util.PtrLocalDisplay;

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
    @InjectView(R.id.refeshlayout)
    PtrFrameLayout mRefreshLayout;

    private FeedAdapter mFeedAdapter;

    public ScientificFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_scientific, container, false);
            ButterKnife.inject(this, mRootView);
            initView();
        }
        return mRootView;
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        mFeedRecycler.setLayoutManager(linearLayoutManager);

        mFeedAdapter = new FeedAdapter(getActivity(), new ArrayList<ArticleSnapShot>());
        mFeedRecycler.setAdapter(mFeedAdapter);

        //init pull to refresh
        RentalsSunHeaderView header = new RentalsSunHeaderView(getActivity());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
        header.setUp(mRefreshLayout);

        mRefreshLayout.setLoadingMinTime(1000);
        mRefreshLayout.setDurationToCloseHeader(1500);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.autoRefresh(true);
            }
        }, 100);

        mRefreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                loadData();
            }
        });

    }

    private void loadData() {
        ArticleServer.getArticleList(new SimpleDataListener<ArticleList>() {
            @Override
            public void onRequestSuccess(ArticleList data) {
                mFeedAdapter.setArticleList(data.getResult());
                mFeedAdapter.notifyDataSetChanged();
                mRefreshLayout.refreshComplete();
            }
        });
    }
}
