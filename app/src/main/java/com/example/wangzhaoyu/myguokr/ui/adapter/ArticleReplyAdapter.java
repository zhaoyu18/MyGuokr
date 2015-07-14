package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ImageServer;
import com.example.wangzhaoyu.myguokr.ui.activity.ArticleActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

    public void setCommentList(ArrayList<ArticleReply> replies) {
        this.mReplies = replies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_reply_item, parent, false);
        view.setOnClickListener(mOnClick);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleReply reply = mReplies.get(position);
        ReplyViewHolder viewHolder = (ReplyViewHolder) holder;
        ImageLoader.getInstance().displayImage(
                reply.getAuthor().getAvatar().getNormal(),
                viewHolder.mReplyItemAvatar,
                ImageServer.getAvatarDisplayOptions(
                        mContext.getResources().getDimensionPixelSize(R.dimen.article_avatar_size)));
        viewHolder.mReplyItemNickname.setText(reply.getAuthor().getNickname());
        viewHolder.mReplyContent.setText(reply.getContent());
    }

    @Override
    public int getItemCount() {
        return mReplies.size();
    }

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArticleSnapShot snapShot = (ArticleSnapShot) v.getTag();
            Intent intent = new Intent(mContext, ArticleActivity.class);
            intent.putExtra(ArticleActivity.ARTICLE_SNAPSHOT_KEY, snapShot);
            mContext.startActivity(intent);
        }
    };

    public static class ReplyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.reply_item_avatar)
        ImageView mReplyItemAvatar;
        @InjectView(R.id.reply_item_nickname)
        TextView mReplyItemNickname;
        @InjectView(R.id.reply_like_btn)
        ImageButton mReplyLikeBtn;
        @InjectView(R.id.reply_header)
        RelativeLayout mReplyHeader;
        @InjectView(R.id.reply_content)
        TextView mReplyContent;

        ReplyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
