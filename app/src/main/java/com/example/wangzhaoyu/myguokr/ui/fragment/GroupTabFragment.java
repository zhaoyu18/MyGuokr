package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentGroupTabBinding;
import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.Group;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.service.GroupService;
import com.example.wangzhaoyu.myguokr.ui.adapter.GroupChannelAdapter;
import com.example.wangzhaoyu.myguokr.ui.adapter.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;

/**
 * @author wangzhaoyu
 */
public class GroupTabFragment extends Fragment {
    private static final String TAG = GroupTabFragment.class.getSimpleName();
    private FragmentGroupTabBinding mBinding;
    private PopupWindow mPopupWindow;
    private GroupService mGroupService;
    private EventBus mEventBus = new EventBus();
    private List<Group> mGroups;
    private RecyclerView mChannelList;
    private GroupChannelAdapter mChannelAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_tab, container, false);
        mBinding = DataBindingUtil.bind(rootView);

        //init tab layout
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mBinding.viewpager.setAdapter(adapter);
        mBinding.tabs.setupWithViewPager(mBinding.viewpager);
        mBinding.tabs.setTabsFromPagerAdapter(adapter);

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
        mGroupService.getGroupFavorite(mEventBus);

        //init group channel recycler view
        mChannelList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGroups = new ArrayList<>();
        mChannelAdapter = new GroupChannelAdapter(getActivity(), mGroups);
        mChannelList.setHasFixedSize(true);
        mChannelList.setAdapter(mChannelAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        mEventBus.unregister(this);
        super.onStop();
    }

    public void onEvent(FavoriteGroup group) {
        Toast.makeText(getActivity(), group.getNow(), Toast.LENGTH_SHORT).show();
        mGroups.clear();
        mGroups.addAll(group.getResult());
        mChannelAdapter.notifyItemRangeInserted(0, mGroups.size());
    }

    public void onEvent(RetrofitError error) {

    }
}
