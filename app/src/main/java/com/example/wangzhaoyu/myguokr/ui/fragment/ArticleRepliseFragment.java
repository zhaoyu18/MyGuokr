package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;
import com.example.wangzhaoyu.myguokr.ui.activity.ArticleActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class ArticleRepliseFragment extends Fragment {

    @InjectView(R.id.article_comments)
    RecyclerView mReplyRecylerView;
    private View mRootView;
    private ArticleSnapShot mSnapShot;
    private ArrayList<ArticleReply> mReplies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_article_comments, container, false);
        ButterKnife.inject(this, mRootView);
        initView();
        return mRootView;
    }

    private void initView() {
        mSnapShot = ((ArticleActivity) getActivity()).getSnapShot();
        ArticleServer.getInstance().getArticleReplies(mSnapShot.getId(),
                new ServerHandler<ArrayList<ArticleReply>>() {
                    @Override
                    public void onRequestSuccess(ArrayList<ArticleReply> replies) {
                        mReplies = replies;
                        Toast.makeText(getActivity(), mReplies.get(0).getContent(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestError() {

                    }

                    @Override
                    public void onNetError() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
