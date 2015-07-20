package com.example.wangzhaoyu.myguokr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.net.NetUtils;
import com.example.wangzhaoyu.myguokr.databinding.ActivityPostDetailBinding;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.server.GroupServer;
import com.example.wangzhaoyu.myguokr.server.handler.DefaultServerHandler;

/**
 * @author wangzhaoyu
 */
public class PostActivity extends AppCompatActivity {
    private final static String TAG = PostActivity.class.getSimpleName();
    public final static String POST_ID_KEY = "post_id_key";
    private ActivityPostDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail);

        int postId = getIntent().getIntExtra(POST_ID_KEY, 0);
        GroupServer.getInstance().getPostDetail(
                postId,
                new DefaultServerHandler<PostDetail>(this) {
                    @Override
                    public void onRequestSuccess(PostDetail detail) {
                        super.onRequestSuccess(detail);
                        String html = "<div id=\"postContent\" class=\"html-text-mixin gbbcode-content\">" +
                                detail.getResult().getHtml() + "</div>";
                        html = NetUtils.getPostHtml(html);
                        mBinding.postWeb.loadDataWithBaseURL(
                                "http://www.guokr.com/",
                                html,
                                "text/html",
                                "charset=UTF-8",
                                null);
                    }
                });
    }
}
