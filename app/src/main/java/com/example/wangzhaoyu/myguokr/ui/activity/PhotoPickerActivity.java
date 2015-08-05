package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPhotoPickerBinding;
import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.network.HttpService;

import de.greenrobot.event.EventBus;

/**
 * @author wangzhaoyu
 */
public class PhotoPickerActivity extends AppCompatActivity {
    private static final String TAG = PhotoPickerActivity.class.getSimpleName();
    private ActivityPhotoPickerBinding mBinding;
    private EventBus mEventBus = EventBus.builder()
            .logNoSubscriberMessages(true)
            .sendNoSubscriberEvent(false)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_picker);
        Glide.with(this)
                .load("http://goo.gl/gEgYUd")
                .asBitmap()
                .into(mBinding.testImage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this);
        HttpService.getInstance().getGroupService().getGroupFavorite(mEventBus);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        mEventBus.unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getInstance().getRefWatcher().watch(mEventBus);
    }

    // This method will be called when a MessageEvent is posted
    public void onEvent(FavoriteGroup group){
        Toast.makeText(this, group.getNow(), Toast.LENGTH_SHORT).show();
    }
}
