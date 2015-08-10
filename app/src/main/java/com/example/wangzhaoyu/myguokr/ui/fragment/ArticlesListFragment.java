package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.service.ArticleService;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.ProgressWheel;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class ArticlesListFragment extends Fragment {
    private View mRootView;
    private ArrayList<ArticleSnapShot> mArticleList;

    @InjectView(R.id.rv_feed)
    RecyclerView mFeedRecycler;

    @InjectView(R.id.refeshlayout)
    PtrFrameLayout mRefreshLayout;

    private ArticleAdapter mAdapter;
    private ArticleService mArticleService;

    public ArticlesListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_article_list, container, false);
            ButterKnife.inject(this, mRootView);
            initView();
        }
        return mRootView;
    }

    private void initView() {
        mArticleService = HttpService.getInstance().getArticleService();
        //init recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        mFeedRecycler.setLayoutManager(linearLayoutManager);
        mArticleList = new ArrayList<>();
        mAdapter = new ArticleAdapter(getActivity(), mArticleList);
        mFeedRecycler.setAdapter(mAdapter);

        //init load more
        mFeedRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && manager.findFirstCompletelyVisibleItemPosition() > 0
                        && manager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1
                        && !mRefreshLayout.isRefreshing()) {
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        //init pull to refresh
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.initWithString("Guokr");
        header.setTextColor(Color.parseColor("#01bcd5"));
        header.setLineWidth(6);

        mRefreshLayout.setLoadingMinTime(750);
        mRefreshLayout.setDurationToCloseHeader(1000);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 500);

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

    /**
     * 刷新列表
     */
    private void refresh() {
        mArticleService.getArticleList(0);
    }

    /**
     * 加载更多
     * TODO 如何防止重复
     */
    private void loadMore() {
        mAdapter.loadStart();
        mArticleService.getArticleList(mArticleList.size());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(ArticleList list) {
        if (list.getOffset() == 0) {
            mArticleList.clear();
            mArticleList.addAll(list.getResult());
            mAdapter.notifyDataSetChanged();
            mRefreshLayout.refreshComplete();

        } else {
            int beforeSize = mArticleList.size();
            mArticleList.addAll(list.getResult());
            mAdapter.notifyContentItemRangeInserted(beforeSize, list.getResult().size());
            mAdapter.loadComplete();
        }
    }

    public void onEvent(RetrofitError error) {
        mAdapter.loadComplete();
        mRefreshLayout.refreshComplete();
    }
}
