package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPhotoPickerBinding;

/**
 * @author wangzhaoyu
 */
public class PhotoPickerActivity extends AppCompatActivity {
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
    }
}
