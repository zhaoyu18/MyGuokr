package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import rx.Observer;
import rx.Subscription;

/**
 * @author wangzhaoyu
 */
public class GroupPostListFragment extends BaseFragment {
    private static final String TAG = GroupPostListFragment.class.getSimpleName();
    private FragmentGroupPostListBinding mBinding;
    private GroupPostAdapter mAdapter;
    private ArrayList<PostSnapShot> mPostSnapShots;
    private GroupService mGroupService;
    private Mode mMode = Mode.GROUPS_HOT_POST;
    private int mGroupId;
    private Observer mObserver = new Observer<GroupPosts>() {
        @Override
        public void onCompleted() {
            mAdapter.loadComplete();
            mBinding.refreshLayout.refreshComplete();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(GroupPosts posts) {
            if (posts.getOffset() == 0) {
                mPostSnapShots.clear();
                mPostSnapShots.addAll(posts.getResult());
                mAdapter.notifyDataSetChanged();
            } else {
                int beforeSize = mPostSnapShots.size();
                mPostSnapShots.addAll(posts.getResult());
                mAdapter.notifyContentItemRangeInserted(beforeSize, posts.getResult().size());
            }
        }
    };

    public enum Mode {
        GROUPS_HOT_POST,
        USER_GROUP_POST,
        GROUP_POST,
    }

    public GroupPostListFragment() {

    }

    public static GroupPostListFragment newInstance(Mode mode) {
        GroupPostListFragment fragment = new GroupPostListFragment();
        fragment.setMode(mode);
        return fragment;
    }

    public static GroupPostListFragment newInstance(int groupId) {
        GroupPostListFragment fragment = new GroupPostListFragment();
        fragment.setMode(Mode.GROUP_POST);
        fragment.setGroupId(groupId);
        return fragment;
    }

    public void setMode(Mode mode) {
        mMode = mode;
    }

    public void setGroupId(int groupId) {
        this.mGroupId = groupId;
    }

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
        header.setTextColor(Color.parseColor("#01bcd5"));
        header.setLineWidth(6);

        mBinding.refreshLayout.setLoadingMinTime(750);
        mBinding.refreshLayout.setDurationToCloseHeader(1000);
        mBinding.refreshLayout.setHeaderView(header);
        mBinding.refreshLayout.addPtrUIHandler(header);

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

        requestCacheData();

        return rootView;
    }

    private void requestCacheData() {
        Subscription subscribe = null;
        //request data
        switch (mMode) {
            case GROUPS_HOT_POST:
                subscribe = mGroupService.getGroupHotPostListCacheFirst(0).subscribe(mObserver);
                break;
            case USER_GROUP_POST:
                subscribe = mGroupService.getGroupUserPostListCacheFirst(0).subscribe(mObserver);
                break;
            case GROUP_POST:
                subscribe = mGroupService.getGroupPostListCacheFirst(0, mGroupId).subscribe(mObserver);
                break;

            default:
                break;
        }

        if (subscribe != null) {
            mSubscriptions.add(subscribe);
        }

        refresh();
    }

    private void refresh() {
        Subscription subscribe = null;
        //request data
        switch (mMode) {
            case GROUPS_HOT_POST:
                subscribe = mGroupService.getGroupHotPostList(0).subscribe(mObserver);
                break;
            case USER_GROUP_POST:
                subscribe = mGroupService.getGroupUserPostList(0).subscribe(mObserver);
                break;
            case GROUP_POST:
                subscribe = mGroupService.getGroupPostList(0, mGroupId).subscribe(mObserver);
                break;

            default:
                break;
        }

        if (subscribe != null) {
            mSubscriptions.add(subscribe);
        }
    }

    private void loadMore() {
        Subscription subscribe = null;

        mAdapter.loadStart();
        switch (mMode) {
            case GROUPS_HOT_POST:
                subscribe = mGroupService.getGroupHotPostList(mPostSnapShots.size()).subscribe(mObserver);
                break;
            case USER_GROUP_POST:
                subscribe = mGroupService.getGroupUserPostList(mPostSnapShots.size()).subscribe(mObserver);
                break;
            case GROUP_POST:
                subscribe = mGroupService.getGroupPostList(mPostSnapShots.size(), mGroupId).subscribe(mObserver);
                break;

            default:
                break;
        }

        if (subscribe != null) {
            mSubscriptions.add(subscribe);
        }
    }
}
