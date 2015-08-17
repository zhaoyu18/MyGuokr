package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentSetttingsBinding;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.network.HttpService;

import rx.Observer;
import rx.Subscription;

/**
 * @author wangzhaoyu
 */
public class SettingsFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = SettingsFragment.class.getSimpleName();
    private FragmentSetttingsBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setttings, container, false);
        mBinding = DataBindingUtil.bind(rootView);
        mBinding.logoutLayout.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_layout:
                new MaterialDialog.Builder(getActivity())
                        .title("退出登录")
                        .content("确定要退出当前账号么")
                        .positiveText("退出")
                        .negativeText("取消")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                Subscription subscription = HttpService.getInstance().getUserService().getNotifiCount().subscribe(new Observer<NotificationCount>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NotificationCount count) {
                        Toast.makeText(getActivity(), count.getResult().getN() + "", Toast.LENGTH_SHORT).show();
                    }
                });

                mSubscriptions.add(subscription);
                break;

            default:
                break;
        }
    }
}
