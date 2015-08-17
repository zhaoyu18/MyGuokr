package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentGroupTabBinding;
import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.Group;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.service.GroupService;
import com.example.wangzhaoyu.myguokr.ui.adapter.GroupChannelAdapter;
import com.example.wangzhaoyu.myguokr.ui.adapter.TabFragmentPagerAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.touchhelper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.Subscription;

/**
 * @author wangzhaoyu
 */
public class GroupTabFragment extends BaseFragment {
    private static final String TAG = GroupTabFragment.class.getSimpleName();
    private FragmentGroupTabBinding mBinding;
    private PopupWindow mPopupWindow;
    private GroupService mGroupService;
    private EventBus mEventBus = new EventBus();
    private List<Group> mGroups;
    private RecyclerView mChannelList;
    private GroupChannelAdapter mChannelAdapter;
    private TabFragmentPagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_tab, container, false);
        mBinding = DataBindingUtil.bind(rootView);

        //init tab layout
        mPagerAdapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mBinding.viewpager.setAdapter(mPagerAdapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewpager);
        mBinding.tabs.setTabsFromPagerAdapter(mPagerAdapter);

        //init popup channel
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_group_channel_popup, null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(android.R.color.transparent)));
        mChannelList = (RecyclerView) view.findViewById(R.id.group_channels);

        mBinding.groupChannelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAsDropDown(mBinding.tabBottomDivider);
            }
        });

        //request groups
        mGroupService = HttpService.getInstance().getGroupService();
        Subscription subscription = mGroupService.getGroupFavorite().subscribe(new Observer<FavoriteGroup>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FavoriteGroup group) {
                mGroups.clear();
                mGroups.addAll(group.getResult());
                mChannelAdapter.notifyItemRangeInserted(0, mGroups.size());
                //update tabs
                mPagerAdapter.setGroups(new ArrayList<>(group.getResult()));
                mPagerAdapter.notifyDataSetChanged();
                mBinding.tabs.setTabsFromPagerAdapter(mPagerAdapter);
            }
        });
        mSubscriptions.add(subscription);

        //init popup group channel recycler view
        mChannelList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mGroups = new ArrayList<>();
        mChannelAdapter = new GroupChannelAdapter(getActivity(), mGroups);
        mChannelList.setHasFixedSize(true);
        mChannelList.setAdapter(mChannelAdapter);
        //init drag & drop touch helper
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mChannelAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mChannelList);
        return rootView;
    }
}
