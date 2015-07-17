package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ArticleReplyItemBinding;
import com.example.wangzhaoyu.myguokr.databinding.ViewListFooterBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReply;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
import com.example.wangzhaoyu.myguokr.ui.view.ReplyTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleCommentAdapter extends HeaderFooterRecyclerViewAdapter {
    private static final String TAG = ArticleCommentAdapter.class.getSimpleName();
    private DisplayImageOptions mDisplayImageOptions;
    private Context mContext;
    private ArrayList<ArticleReply> mComments;

    public ArticleCommentAdapter(Context context, ArrayList<ArticleReply> comments) {
        mContext = context;
        mComments = comments;
        mDisplayImageOptions = ImageServer.getAvatarDisplayOptions(
                mContext.getResources().getDimensionPixelSize(R.dimen.article_avatar_size));
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 1;
    }

    @Override
    protected int getContentItemCount() {
        return mComments.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        ViewListFooterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_list_footer,
                parent,
                false);

        FooterHolder holder = new FooterHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
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
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {

    }

    @Override
    protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position) {
        FooterHolder viewHolder = (FooterHolder) footerViewHolder;
        viewHolder.getBinding().setVariable(BR.footerText, "正在加载...");
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        ArticleReply comment = mComments.get(position);
        ReplyViewHolder viewHolder = (ReplyViewHolder) contentViewHolder;
        viewHolder.getBinding().setVariable(BR.comment, comment);
        viewHolder.getBinding().setVariable(BR.option, mDisplayImageOptions);
        viewHolder.getBinding().executePendingBindings();
    }

    /**
     * content view holder
     */
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

    /**
     * footer view holder
     */
    public static class FooterHolder extends RecyclerView.ViewHolder {
        private ViewListFooterBinding mBinding;

        FooterHolder(View itemView) {
            super(itemView);
        }

        public ViewListFooterBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewListFooterBinding binding) {
            this.mBinding = binding;
        }
    }

    @BindingAdapter({"bind:imageUrl", "bind:imageOption"})
    public static void loadAvatar(ImageView view, String url, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, view, options);
    }

    @BindingAdapter({"bind:html"})
    public static void loadHtml(ReplyTextView textView, String html) {
        textView.loadHtml(html);
    }
}
