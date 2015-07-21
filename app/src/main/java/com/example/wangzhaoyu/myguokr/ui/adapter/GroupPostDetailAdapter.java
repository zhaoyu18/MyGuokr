package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewPostCommentItemBinding;
import com.example.wangzhaoyu.myguokr.databinding.ViewPostDetailItemBinding;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComment;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
import com.example.wangzhaoyu.myguokr.ui.view.GuokrWebView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class GroupPostDetailAdapter extends LoadmoreFooterViewAdapter {
    private static final String TAG = GroupPostDetailAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<GroupPostComment> mComments;
    private PostDetail mPost;
    private DisplayImageOptions mDisplayImageOptions;

    public GroupPostDetailAdapter(Context context, PostDetail post, ArrayList<GroupPostComment> comments) {
        mContext = context;
        mPost = post;
        mComments = comments;
        mDisplayImageOptions = ImageServer.getAvatarDisplayOptions(
                mContext.getResources().getDimensionPixelSize(R.dimen.article_avatar_size));
    }

    public void setPost(PostDetail post) {
        mPost = post;
    }

    public void setComments(ArrayList<GroupPostComment> comments) {
        mComments = comments;
    }

    @Override
    protected int getHeaderItemCount() {
        return (mPost == null ? 0 : 1);
    }

    @Override
    protected int getContentItemCount() {
        return mComments.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        ViewPostDetailItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_post_detail_item,
                parent,
                false);

        ContentViewHolder viewHolder = new ContentViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        ViewPostCommentItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_post_comment_item,
                parent,
                false);

        CommentViewHolder viewHolder = new CommentViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {
        ContentViewHolder holder = (ContentViewHolder) headerViewHolder;
        holder.getBinding().setVariable(BR.post, mPost);
        holder.getBinding().executePendingBindings();
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        GroupPostComment comment = mComments.get(position);
        CommentViewHolder holder = (CommentViewHolder) contentViewHolder;
        holder.getBinding().setVariable(BR.comment, comment);
        holder.getBinding().setVariable(BR.option, mDisplayImageOptions);
        holder.getBinding().executePendingBindings();
    }

    /**
     * post content view holder
     */
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private ViewPostDetailItemBinding mBinding;

        public ViewPostDetailItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewPostDetailItemBinding binding) {
            mBinding = binding;
        }

        public ContentViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * post comment view holder
     */
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private ViewPostCommentItemBinding mBinding;

        public ViewPostCommentItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewPostCommentItemBinding binding) {
            mBinding = binding;
        }

        public CommentViewHolder(View itemView) {
            super(itemView);
        }
    }

    @BindingAdapter({"bind:imageUrl", "bind:imageOption"})
    public static void loadAvatar(ImageView view, String url, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, view, options);
    }

    @BindingAdapter({"bind:html"})
    public static void loadPostContent(GuokrWebView webView, String content) {
        webView.loadDataWithBaseURL(
                "http://www.guokr.com/",
                content,
                "text/html",
                "charset=UTF-8",
                null);
    }
}
