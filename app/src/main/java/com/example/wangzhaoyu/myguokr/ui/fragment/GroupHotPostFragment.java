package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentGroupPostListBinding;
import com.example.wangzhaoyu.myguokr.model.response.PostSnapShot;
import com.example.wangzhaoyu.myguokr.server.GroupServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;
import com.example.wangzhaoyu.myguokr.ui.adapter.GroupPostAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class GroupHotPostFragment extends Fragment {
    private static final String TAG = GroupHotPostFragment.class.getSimpleName();
    private FragmentGroupPostListBinding mBinding;
    private GroupPostAdapter mAdapter;
    private ArrayList<PostSnapShot> mPostSnapShots;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_post_list, container, false);
        mBinding = DataBindingUtil.bind(rootView);

        //init recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mBinding.postList.setLayoutManager(layoutManager);
        mPostSnapShots = new ArrayList<>();
        mAdapter = new GroupPostAdapter(getActivity(), mPostSnapShots);
        mBinding.postList.setAdapter(mAdapter);

        //init load more
        mBinding.postList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && manager.findFirstCompletelyVisibleItemPosition() > 0
                        && manager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1
                        && !mBinding.refreshLayout.isRefreshing()) {
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

        mBinding.refreshLayout.setLoadingMinTime(750);
        mBinding.refreshLayout.setDurationToCloseHeader(1000);
        mBinding.refreshLayout.setHeaderView(header);
        mBinding.refreshLayout.addPtrUIHandler(header);

        mBinding.refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 500);

        mBinding.refreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                refresh();
            }
        });
        return rootView;
    }

    private void refresh() {
        //request data
        GroupServer.getInstance().refreshPostList(new DefaultServerHandler<ArrayList<PostSnapShot>>(getActivity()) {
            @Override
            public void onRequestSuccess(ArrayList<PostSnapShot> postSnapShots) {
                super.onRequestSuccess(postSnapShots);
                mPostSnapShots.clear();
                mPostSnapShots.addAll(postSnapShots);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onResponse() {
                super.onResponse();
                mBinding.refreshLayout.refreshComplete();
            }
        });
    }

    private void loadMore() {
        mAdapter.setFooterText("正在加载...");
        GroupServer.getInstance().loadMorePostList(
                mPostSnapShots.size(),
                new DefaultServerHandler<ArrayList<PostSnapShot>>(getActivity()) {
                    @Override
                    public void onResponse() {
                        super.onResponse();
                        mAdapter.setFooterText("");
                    }

                    @Override
                    public void onRequestSuccess(ArrayList<PostSnapShot> shots) {
                        super.onRequestSuccess(shots);
                        int beforeSize = mPostSnapShots.size();
                        mPostSnapShots.addAll(shots);
                        mAdapter.notifyContentItemRangeInserted(beforeSize, shots.size());
                    }
                });
    }
}
