package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPostDetailBinding;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComment;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.server.GroupServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;
import com.example.wangzhaoyu.myguokr.ui.adapter.GroupPostDetailAdapter;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class PostActivity extends AppCompatActivity {
    private final static String TAG = PostActivity.class.getSimpleName();
    private static final int ANIM_DURATION_BOTTOMBAR = 100;

    public final static String POST_ID_KEY = "post_id_key";
    private ActivityPostDetailBinding mBinding;
    private ArrayList<GroupPostComment> mComments;
    private GroupPostDetailAdapter mAdapter;
    private PostDetail mPostDetail;
    private boolean mIsBottombarShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail);

        //init tool bar
        mBinding.toolbar.setTitle("小组热帖");
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //init recycler view
        mComments = new ArrayList<>();
        mAdapter = new GroupPostDetailAdapter(this, null, mComments);
        mBinding.postDetailRecycler.setHasFixedSize(true);
        mBinding.postDetailRecycler.setLayoutManager(new LinearLayoutManager(this));
        mBinding.postDetailRecycler.setAdapter(mAdapter);
        mBinding.postDetailRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && manager.findFirstCompletelyVisibleItemPosition() > 0
                        && manager.findLastVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < -24 && !mIsBottombarShow) {
                    //显示
                    bottomBarAnimation();
                } else if (dy > 24 && mIsBottombarShow) {
                    //隐藏
                    bottomBarAnimation();
                }
            }
        });

        //request data
        final int postId = getIntent().getIntExtra(POST_ID_KEY, 0);
        GroupServer.getInstance().getPostDetail(
                postId,
                new DefaultServerHandler<PostDetail>(this) {
                    @Override
                    public void onRequestSuccess(PostDetail detail) {
                        super.onRequestSuccess(detail);
                        mPostDetail = detail;
                        mAdapter.setPost(detail);
                        mAdapter.notifyHeaderItemInserted(0);
                        //load comments after post detail
                        GroupServer.getInstance().getPostComments(
                                postId,
                                new DefaultServerHandler<ArrayList<GroupPostComment>>(PostActivity.this) {
                                    @Override
                                    public void onRequestSuccess(ArrayList<GroupPostComment> comments) {
                                        super.onRequestSuccess(comments);
                                        mComments.clear();
                                        mComments.addAll(comments);
                                        mAdapter.notifyContentItemRangeInserted(0, comments.size());
                                    }
                                });
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

    private void loadMore() {
        mAdapter.setFooterText("正在加载...");
        GroupServer.getInstance().loadMorePostComments(
                mPostDetail.getResult().getId(),
                mComments.size(),
                new DefaultServerHandler<ArrayList<GroupPostComment>>(PostActivity.this) {
                    @Override
                    public void onRequestSuccess(ArrayList<GroupPostComment> comments) {
                        super.onRequestSuccess(comments);
                        int beforeSize = mComments.size();
                        mComments.addAll(comments);
                        mAdapter.notifyContentItemRangeInserted(beforeSize, comments.size());
                    }

                    @Override
                    public void onResponse() {
                        super.onResponse();
                        mAdapter.setFooterText("");
                    }
                });
    }

    public void onLikeBtnClicked(View view) {

    }

    public void onCommentBtnClicked(View view) {

    }

    private void bottomBarAnimation() {
        int startPos;
        int destPos;
        if (mIsBottombarShow) {
            startPos = 0;
            destPos = mBinding.postBottomBar.getHeight();
        } else {
            startPos = mBinding.postBottomBar.getHeight();
            destPos = 0;
        }

        mIsBottombarShow = !mIsBottombarShow;
        mBinding.postBottomBar.setTranslationY(startPos);
        mBinding.postBottomBar.animate()
                .translationY(destPos)
                .setDuration(ANIM_DURATION_BOTTOMBAR)
                .start();
    }
}
