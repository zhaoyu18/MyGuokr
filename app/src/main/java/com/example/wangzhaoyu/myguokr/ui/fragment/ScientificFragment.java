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
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;
import com.example.wangzhaoyu.myguokr.ui.adapter.ScientificFeedAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.MoocGlassesHeaderView;
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

    private ScientificFeedAdapter mScientificFeedAdapter;

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

        mScientificFeedAdapter = new ScientificFeedAdapter(getActivity(), new ArrayList<ArticleSnapShot>());
        mFeedRecycler.setAdapter(mScientificFeedAdapter);

        //init pull to refresh
        MoocGlassesHeaderView header = new MoocGlassesHeaderView(getActivity());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
        header.setUp(mRefreshLayout);

        mRefreshLayout.setLoadingMinTime(500);
        mRefreshLayout.setDurationToCloseHeader(1000);
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
                refresh();
            }
        });

    }

    private void refresh() {
        ArticleServer.getInstance().getArticleList(
                new DefaultServerHandler<ArrayList<ArticleSnapShot>>(getActivity()) {

                    @Override
                    public void onRequestSuccess(ArrayList<ArticleSnapShot> articleList) {
                        mScientificFeedAdapter.setArticleList(articleList);
                        mScientificFeedAdapter.notifyDataSetChanged();
                        mRefreshLayout.refreshComplete();
                    }

                    @Override
                    public void onResponse() {
                        mRefreshLayout.refreshComplete();
                    }
                });
    }

    private void loadMore() {

    }
}
