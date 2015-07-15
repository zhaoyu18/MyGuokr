package com.example.wangzhaoyu.myguokr.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.FragmentArticleRepliesBinding;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;
import com.example.wangzhaoyu.myguokr.ui.activity.ArticleActivity;
import com.example.wangzhaoyu.myguokr.ui.adapter.ArticleReplyAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * @author wangzhaoyu
 */
public class ArticleRepliesFragment extends Fragment {

    private View mRootView;
    private ArticleSnapShot mSnapShot;
    private ArrayList<ArticleReply> mReplies;
    private FragmentArticleRepliesBinding mBinding;
    private ArticleReplyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_article_replies, container, false);
        mBinding = DataBindingUtil.bind(mRootView);
        initView();
        return mRootView;
    }

    private void initView() {
        mSnapShot = ((ArticleActivity) getActivity()).getSnapShot();
        mReplies = new ArrayList<>();
        mAdapter = new ArticleReplyAdapter(getActivity(), mReplies);
        mBinding.articleRecycler.setHasFixedSize(true);
        mBinding.articleRecycler.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        mBinding.articleRecycler.setAdapter(mAdapter);

        ArticleServer.getInstance().getArticleReplies(mSnapShot.getId(),
                new ServerHandler<ArrayList<ArticleReply>>() {
                    @Override
                    public void onRequestSuccess(ArrayList<ArticleReply> replies) {
                        mReplies.addAll(replies);
                        mAdapter.notifyDataSetChanged();
//                        Toast.makeText(getActivity(), mBinding.articleRecycler.toString(), Toast.LENGTH_SHORT).show();
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
