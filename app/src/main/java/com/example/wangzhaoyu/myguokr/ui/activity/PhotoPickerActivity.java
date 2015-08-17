package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPhotoPickerBinding;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.network.HttpService;

import rx.Observer;

/**
 * @author wangzhaoyu
 */
public class PhotoPickerActivity extends BaseActivity {
    private static final String TAG = PhotoPickerActivity.class.getSimpleName();
    private ActivityPhotoPickerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_picker);
        Glide.with(this)
                .load("http://goo.gl/gEgYUd")
                .asBitmap()
                .into(mBinding.testImage);

        mBinding.testButton.setOnClickListener(v -> mSubscriptions.add(
                        HttpService.getInstance().getUserService().getUserInfo(
                                HttpService.getInstance().getUserService().getUserUkey())
                                .subscribe(new Observer<User>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(PhotoPickerActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(User user) {
                                        Toast.makeText(PhotoPickerActivity.this, user.getResult().getNickname(), Toast.LENGTH_SHORT).show();
                                    }
                                }))
        );
    }
}
