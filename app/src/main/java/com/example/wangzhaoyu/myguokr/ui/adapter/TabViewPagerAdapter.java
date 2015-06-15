package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author wangzhaoyu
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragemnts;
    private List<String> mTitles;

    public TabViewPagerAdapter(FragmentManager fm, List<Fragment> fragemnts, List<String> titles) {
        super(fm);
        this.mFragemnts = fragemnts;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragemnts.get(position);
    }

    @Override
    public int getCount() {
        return mFragemnts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
