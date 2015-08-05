package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wangzhaoyu.myguokr.ui.fragment.GroupHotPostFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhaoyu
 */
public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = TabFragmentPagerAdapter.class.getSimpleName();
    private List<String> mTitles;

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mTitles = new ArrayList<>();
        mTitles.add("小组热帖");
        mTitles.add("我的小组");
        mTitles.add("小组排行");
        mTitles.add("小组热帖");
        mTitles.add("我的小组");
        mTitles.add("小组排行");
        mTitles.add("小组热帖");
        mTitles.add("我的小组");
        mTitles.add("小组排行");
    }

    @Override
    public Fragment getItem(int position) {
        return new GroupHotPostFragment();
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
