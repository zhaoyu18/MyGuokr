package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
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
public class ScientificFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ScientificFeedAdapter.class.getSimpleName();
    private static final int ANIMATED_ITEMS_COUNT = 5;

    private Context mContext;
    private ArrayList<ArticleSnapShot> mSnapShots;
    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();
    private int lastAnimatedPosition = -1;

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
        view.setOnClickListener(mOnClick);
        return new CellFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        ArticleSnapShot snapShot = mSnapShots.get(position);
        CellFeedViewHolder viewHolder = (CellFeedViewHolder) holder;
        ImageLoader.getInstance().displayImage(snapShot.getSmall_image(),
                viewHolder.mIvTitle, mDisImageOptions);
        viewHolder.mTvTitle.setText(snapShot.getTitle());
        //用于onclick
        viewHolder.mItemView.setTag(snapShot);
    }

    @Override
    public int getItemCount() {
        return mSnapShots.size();
    }

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArticleSnapShot snapShot = (ArticleSnapShot) v.getTag();
            //test
//            String url = mSnapShots.get(pos).getResource_url();
//            ArticleServer.getInstance().getArticle(url, new DataListener<Article>() {
//                @Override
//                public void onRequestSuccess(Article data) {
//                    Log.i(TAG, data.getResult().getTitle());
//                    Intent intent = new Intent(mContext, ArticleActivity.class);
////                    String html = NetUtils.getArticleHtml(data.getResult().getContent());
//                    String html = data.getResult().getContent();
//                    intent.putExtra("html", html);
//                    mContext.startActivity(intent);
//                }
//
//                @Override
//                public void onRequestError() {
//
//                }
//            });
            Intent intent = new Intent(mContext, ArticleActivity.class);
            intent.putExtra(ArticleActivity.ARTICLE_SNAPSHOT_KEY, snapShot);
            mContext.startActivity(intent);
        }
    };

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
        }
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }
}
