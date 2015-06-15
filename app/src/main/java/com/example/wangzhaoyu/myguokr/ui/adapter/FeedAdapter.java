package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.ui.view.SquaredImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    public FeedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CellFeedViewHolder viewHolder = (CellFeedViewHolder) holder;
        viewHolder.mIvTitle.setImageResource(R.drawable.img_feed_center_1);
    }

    @Override
    public int getItemCount() {
        return 10;
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
