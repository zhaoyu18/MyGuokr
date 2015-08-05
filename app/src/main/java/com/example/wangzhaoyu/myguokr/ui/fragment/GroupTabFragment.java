package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentGroupTabBinding;
import com.example.wangzhaoyu.myguokr.ui.adapter.TabFragmentPagerAdapter;

/**
 * @author wangzhaoyu
 */
public class GroupTabFragment extends Fragment {
    private static final String TAG = GroupTabFragment.class.getSimpleName();
    private FragmentGroupTabBinding mBinding;

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
        return rootView;
    }
}
