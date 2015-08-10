package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wangzhaoyu.myguokr.model.response.Group;
import com.example.wangzhaoyu.myguokr.ui.fragment.GroupPostListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhaoyu
 */
public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = TabFragmentPagerAdapter.class.getSimpleName();
    private List<String> mTitles;
    private List<Group> mGroups;

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mTitles = new ArrayList<>();
        mTitles.add("小组热帖");
        mTitles.add("我的小组");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = GroupPostListFragment.newInstance(GroupPostListFragment.Mode.GROUPS_HOT_POST);
                break;

            case 1:
                fragment = GroupPostListFragment.newInstance(GroupPostListFragment.Mode.USER_GROUP_POST);
                break;

            default:
                fragment = GroupPostListFragment.newInstance(mGroups.get(position - 2).getId());
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void setGroups(List<Group> groups) {
        mGroups = groups;
        for (Group group : groups) {
            mTitles.add(group.getName());
        }
    }
}
