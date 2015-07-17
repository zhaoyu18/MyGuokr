package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivitySingleFragmentBinding;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.server.UserServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;
import com.example.wangzhaoyu.myguokr.ui.fragment.UserProfileFragment;

/**
 * @author wangzhaoyu
 */
public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = UserProfileActivity.class.getSimpleName();
    public static final String ARG_UKEY = "ukey";
    private ActivitySingleFragmentBinding mBinding;
    private String mUkey;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        setContentView(R.layout.activity_single_fragment);

        //init tool bar
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mUkey = getIntent().getStringExtra(ARG_UKEY);
        UserServer.getInstance().getUserInfo(mUkey, new DefaultServerHandler<User>(this) {
            @Override
            public void onRequestSuccess(User user) {
                super.onRequestSuccess(user);
                mUser = user;
                addMainFragment(new UserProfileFragment());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction
//                .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void addMainFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    public User getUser() {
        return mUser;
    }
}
