package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.ArticleServer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wangzhaoyu
 */
public class ScientificFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<ArticleSnapShot> mSnapShots;
    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();

    public ScientificFeedAdapter(Context mContext, ArrayList<ArticleSnapShot> articleList) {
        this.mContext = mContext;
        this.mSnapShots = articleList;
    }

    public void setArticleList(ArrayList<ArticleSnapShot> articleList) {
        this.mSnapShots = articleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.scientfic_item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleSnapShot snapShot = mSnapShots.get(position);
        CellFeedViewHolder viewHolder = (CellFeedViewHolder) holder;
        ImageLoader.getInstance().displayImage(snapShot.getSmall_image(),
                viewHolder.mIvTitle, mDisImageOptions);
        viewHolder.mTvTitle.setText(snapShot.getTitle());
        //用于onclick
        viewHolder.mItemView.setTag(snapShot.getUrl());
    }

    @Override
    public int getItemCount() {
        return mSnapShots.size();
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        @InjectView(R.id.iv_title)
        ImageView mIvTitle;
        @InjectView(R.id.tv_title)
        TextView mTvTitle;

        public CellFeedViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.inject(this, itemView);
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = (String) v.getTag();
                    ArticleServer.getInstance().getArticleDetail(url);
                }
            });
        }
    }
}
