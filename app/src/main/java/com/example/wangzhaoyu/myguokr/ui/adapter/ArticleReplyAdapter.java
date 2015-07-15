package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ArticleReplyItemBinding;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReply;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ArticleReplyAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<ArticleReply> mReplies;
    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();

    public ArticleReplyAdapter(Context context, ArrayList<ArticleReply> replies) {
        this.mContext = context;
        this.mReplies = replies;
    }

    public void setReplyList(ArrayList<ArticleReply> replies) {
        this.mReplies = replies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleReplyItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.article_reply_item,
                parent,
                false);

        ReplyViewHolder holder = new ReplyViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleReply reply = mReplies.get(position);
        ReplyViewHolder viewHolder = (ReplyViewHolder) holder;
        viewHolder.getBinding().setVariable(BR.reply, reply);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mReplies.size();
    }

    public static class ReplyViewHolder extends RecyclerView.ViewHolder {
        private ArticleReplyItemBinding mBinding;

        ReplyViewHolder(View view) {
            super(view);
        }

        public ArticleReplyItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ArticleReplyItemBinding binding) {
            this.mBinding = binding;
        }
    }
}
