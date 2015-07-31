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
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostSnapShot;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.service.GroupService;
import com.example.wangzhaoyu.myguokr.ui.adapter.GroupPostAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrDefaultHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrFrameLayout;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.PtrHandler;
import com.example.wangzhaoyu.myguokr.ui.widget.pulltorefresh.header.StoreHouseHeader;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class GroupHotPostFragment extends Fragment {
    private static final String TAG = GroupHotPostFragment.class.getSimpleName();
    private FragmentGroupPostListBinding mBinding;
    private GroupPostAdapter mAdapter;
    private ArrayList<PostSnapShot> mPostSnapShots;
    private GroupService mGroupService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_post_list, container, false);
        mBinding = DataBindingUtil.bind(rootView);

        mGroupService = HttpService.getInstance().getGroupService();

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
        mGroupService.getGroupPostList(0);
    }

    private void loadMore() {
        mAdapter.loadStart();
        mGroupService.getGroupPostList(mPostSnapShots.size());
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

    public void onEvent(GroupPosts posts) {
        if (posts.getOffset() == 0) {
            mPostSnapShots.clear();
            mPostSnapShots.addAll(posts.getResult());
            mAdapter.notifyDataSetChanged();
            mBinding.refreshLayout.refreshComplete();
        } else {
            int beforeSize = mPostSnapShots.size();
            mPostSnapShots.addAll(posts.getResult());
            mAdapter.notifyContentItemRangeInserted(beforeSize, posts.getResult().size());
            mAdapter.loadComplete();
        }
    }

    public void onEvent(RetrofitError error) {
        mAdapter.loadComplete();
        mBinding.refreshLayout.refreshComplete();
    }
}
