package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.ui.view.SquaredImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<ArticleSnapShot> mSnapShots;

    public FeedAdapter(Context mContext, ArrayList<ArticleSnapShot> articleList) {
        this.mContext = mContext;
        this.mSnapShots = articleList;
    }

    public void setArticleList(ArrayList<ArticleSnapShot> articleList) {
        this.mSnapShots = articleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleSnapShot snapShot = mSnapShots.get(position);
        CellFeedViewHolder viewHolder = (CellFeedViewHolder) holder;
        ImageLoader.getInstance().displayImage(snapShot.getSmall_image(),
                viewHolder.mIvTitle);
        viewHolder.mTvTitle.setText(snapShot.getTitle());
        viewHolder.mTvAbstract.setText(snapShot.getSummary());
    }

    @Override
    public int getItemCount() {
        return mSnapShots.size();
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_feed)
        SquaredImageView mIvTitle;
        @InjectView(R.id.tv_title)
        TextView mTvTitle;
        @InjectView(R.id.tv_abstract)
        TextView mTvAbstract;

        public CellFeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
