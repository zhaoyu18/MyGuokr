package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentPagerBinding;

public class PagerTextFragment extends Fragment {
    private View mRootView;

    public PagerTextFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_pager, container, false);
            FragmentPagerBinding binding = FragmentPagerBinding.inflate(getLayoutInflater(savedInstanceState));
            User user = new User("Zhaoyu", "Wang");
            binding.setUser(user);
        }
        return mRootView;
    }

    public class User {
        private final String firstName;
        private final String lastName;
        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
    }
}