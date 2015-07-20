package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentGroupPostListBinding;

/**
 * @author wangzhaoyu
 */
public class GroupHotPostFragment extends Fragment {
    private static final String TAG = GroupHotPostFragment.class.getSimpleName();
    private FragmentGroupPostListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_post_list, container, false);
        mBinding = DataBindingUtil.bind(rootView);
        return rootView;
    }
}
