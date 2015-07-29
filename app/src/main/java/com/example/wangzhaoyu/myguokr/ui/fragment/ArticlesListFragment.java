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
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.network.HttpClient;
import com.example.wangzhaoyu.myguokr.network.api.ArticleService;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.ProgressWheel;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
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

    @InjectView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;

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
        mArticleService = HttpClient.getInstance().getArticleService();
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
//        MoocGlassesHeaderView header = new MoocGlassesHeaderView(getActivity());
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
//        header.setUp(mRefreshLayout);
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.initWithString("Guokr");

        mRefreshLayout.setLoadingMinTime(750);
        mRefreshLayout.setDurationToCloseHeader(1000);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
                mProgressWheel.spin();
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
//        ArticleServer.getInstance().refreshArticleList(
//                new DefaultServerHandler<ArrayList<ArticleSnapShot>>(getActivity()) {
//
//                    @Override
//                    public void onRequestSuccess(ArrayList<ArticleSnapShot> articleList) {
//                        super.onRequestSuccess(articleList);
//                        mArticleList.clear();
//                        mArticleList.addAll(articleList);
//                        mAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onResponse() {
//                        mRefreshLayout.refreshComplete();
//                        mProgressWheel.stopSpinning();
//                    }
//                });

        mArticleService.getArticleList(
                Network.Parameters.RetrieveType.BY_SUBJECT,
                20,
                0,
                new Callback<ArticleList>() {
                    @Override
                    public void success(ArticleList list, Response response) {
                        mArticleList.clear();
                        mArticleList.addAll(list.getResult());
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.refreshComplete();
                        mProgressWheel.stopSpinning();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mRefreshLayout.refreshComplete();
                        mProgressWheel.stopSpinning();
                    }
                });
    }

    /**
     * 加载更多
     * TODO 如何防止重复
     */
    private void loadMore() {
        mAdapter.loadStart();
//        ArticleServer.getInstance().loadMoreArticleList(mArticleList.size(),
//                new DefaultServerHandler<ArrayList<ArticleSnapShot>>(getActivity()) {
//                    @Override
//                    public void onRequestSuccess(ArrayList<ArticleSnapShot> articleSnapShots) {
//                        super.onRequestSuccess(articleSnapShots);
//                        int beforeSize = mArticleList.size();
//                        mArticleList.addAll(articleSnapShots);
//                        mAdapter.notifyContentItemRangeInserted(beforeSize, articleSnapShots.size());
//                    }
//
//                    @Override
//                    public void onResponse() {
//                        mRefreshLayout.refreshComplete();
//                        mAdapter.loadComplete();
//                    }
//                }
//        );

        mArticleService.getArticleList(
                Network.Parameters.RetrieveType.BY_SUBJECT,
                20,
                mArticleList.size(),
                new Callback<ArticleList>() {
                    @Override
                    public void success(ArticleList list, Response response) {
                        int beforeSize = mArticleList.size();
                        mArticleList.addAll(list.getResult());
                        mAdapter.notifyContentItemRangeInserted(beforeSize, list.getResult().size());
                        mRefreshLayout.refreshComplete();
                        mProgressWheel.stopSpinning();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mRefreshLayout.refreshComplete();
                        mProgressWheel.stopSpinning();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
