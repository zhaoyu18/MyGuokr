package com.example.wangzhaoyu.myguokr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.ui.adapter.TabViewPagerAdapter;
import com.example.wangzhaoyu.myguokr.ui.fragment.PagerTextFragment;
import com.example.wangzhaoyu.myguokr.ui.fragment.ScientificFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.viewpager)
    ViewPager mViewPager;

    @InjectView(R.id.tab)
    TabLayout mTabLayout;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.fab)
    FloatingActionButton mFab;

    @InjectView(R.id.main_drawer)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.nv_main_navigation)
    NavigationView mNaviView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //set up navigation view
        mNaviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                return true;
            }
        });

        //set up toolbar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("果壳");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //set up fab
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "here I am", Snackbar.LENGTH_SHORT)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        });

        //set up viewpager
        List<String> titiles = new ArrayList<>();
        titiles.add("科学人");
        titiles.add("果壳小组");
        titiles.add("果壳问答");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScientificFragment());
        fragments.add(new PagerTextFragment());
        fragments.add(new PagerTextFragment());
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(), fragments, titiles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
