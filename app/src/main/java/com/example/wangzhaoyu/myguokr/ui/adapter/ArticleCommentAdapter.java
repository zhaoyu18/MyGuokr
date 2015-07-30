package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewArticleCommentItemBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReply;
import com.example.wangzhaoyu.myguokr.ui.activity.UserInfoActivity;
import com.example.wangzhaoyu.myguokr.ui.view.ReplyTextView;
import com.example.wangzhaoyu.myguokr.ui.widget.GlideCircleTransform;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleCommentAdapter extends LoadmoreFooterViewAdapter implements View.OnClickListener {
    private static final String TAG = ArticleCommentAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<ArticleReply> mComments;
    private ReplyCallback mCallback;

    public ArticleCommentAdapter(Context context, ArrayList<ArticleReply> comments, ReplyCallback callback) {
        mContext = context;
        mComments = comments;
        mCallback = callback;
    }

    @Override
    protected int getContentItemCount() {
        return mComments.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        ViewArticleCommentItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_article_comment_item,
                parent,
                false);

        ReplyViewHolder holder = new ReplyViewHolder(binding.getRoot());
        holder.setBinding(binding);
        binding.replyItemAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ukey = ((ArticleReply) v.getTag(R.id.reply_item_avatar)).getAuthor().getUkey();
                Intent intent = new Intent(mContext, UserInfoActivity.class);
                intent.putExtra(UserInfoActivity.ARG_UKEY, ukey);
                mContext.startActivity(intent);
            }
        });

        //onclick
        binding.getRoot().setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        ArticleReply comment = mComments.get(position);
        ReplyViewHolder viewHolder = (ReplyViewHolder) contentViewHolder;
        viewHolder.getBinding().setVariable(BR.comment, comment);
        viewHolder.getBinding().setVariable(BR.floor, position + 1);
        viewHolder.getBinding().setVariable(BR.context, mContext);
        viewHolder.getBinding().executePendingBindings();
        viewHolder.getBinding().replyItemAvatar.setTag(R.id.reply_item_avatar, comment);
        viewHolder.getBinding().getRoot().setTag(position);
    }

    @Override
    public void onClick(View v) {
        final int pos = (int) v.getTag();
        new MaterialDialog.Builder(mContext)
//                .title(R.string.title)
                .items(R.array.comment_operation)
                .itemColor(Color.parseColor("#000000"))
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        switch (which) {
                            //reply comment
                            case 0:
                                mCallback.onReplyClicked(mComments.get(pos));
                                break;
                        }
                    }
                })
                .show();
    }

    /**
     * content view holder
     */
    public static class ReplyViewHolder extends RecyclerView.ViewHolder {
        private ViewArticleCommentItemBinding mBinding;

        ReplyViewHolder(View view) {
            super(view);
        }

        public ViewArticleCommentItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewArticleCommentItemBinding binding) {
            this.mBinding = binding;
        }
    }

    public interface ReplyCallback {
        public void onReplyClicked(ArticleReply reply);
    }
}
