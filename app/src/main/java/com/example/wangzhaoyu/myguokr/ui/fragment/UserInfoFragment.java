package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentUserProfileMainBinding;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
import com.example.wangzhaoyu.myguokr.ui.activity.UserInfoActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author wangzhaoyu
 */
public class UserInfoFragment extends Fragment {
    private static final String TAG = UserInfoFragment.class.getSimpleName();
    private FragmentUserProfileMainBinding mBinding;
    private User mUser;
    private DisplayImageOptions mDisplayImageOptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_profile_main, container, false);
        mBinding = DataBindingUtil.bind(rootView);
        mUser = ((UserInfoActivity) getActivity()).getUser();
        mDisplayImageOptions = ImageServer.getAvatarDisplayOptions(
                getActivity().getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size));
        mBinding.setUser(mUser);
        mBinding.setOption(mDisplayImageOptions);
        return rootView;
    }

    @BindingAdapter({"bind:imageUrl", "bind:imageOption"})
    public static void loadAvatar(ImageView view, String url, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, view, options);
    }
}
