package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.databinding.ViewArticleFeedBinding;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.ui.activity.ArticleActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class ArticleAdapter extends LoadmoreFooterViewAdapter {
    private static final String TAG = ArticleAdapter.class.getSimpleName();
    private static final int ANIMATED_ITEMS_COUNT = 5;

    private Context mContext;
    private ArrayList<ArticleSnapShot> mSnapShots;
    private DisplayImageOptions mDisImageOptions = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .displayer(new FadeInBitmapDisplayer(300))
            .cacheOnDisk(true)
            .build();
    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArticleSnapShot snapShot = (ArticleSnapShot) v.getTag();
            Intent intent = new Intent(mContext, ArticleActivity.class);
            intent.putExtra(ArticleActivity.ARTICLE_SNAPSHOT_KEY, snapShot);
            mContext.startActivity(intent);
        }
    };
    private int lastAnimatedPosition = -1;

    public ArticleAdapter(Context context, ArrayList<ArticleSnapShot> articleList) {
        mContext = context;
        mSnapShots = articleList;

    }

    public void setArticleList(ArrayList<ArticleSnapShot> articleList) {
        this.mSnapShots = articleList;
    }

    @Override
    protected int getContentItemCount() {
        return mSnapShots.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        ViewArticleFeedBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_article_feed,
                parent,
                false);

        SnapShotViewHolder holder = new SnapShotViewHolder(binding.getRoot());
        holder.setBinding(binding);
        binding.cardView.setOnClickListener(mOnClick);
        return holder;
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        runEnterAnimation(contentViewHolder.itemView, position);
        ArticleSnapShot snapShot = mSnapShots.get(position);
        SnapShotViewHolder viewHolder = (SnapShotViewHolder) contentViewHolder;
        viewHolder.getBinding().setVariable(BR.snapshot, snapShot);
        viewHolder.getBinding().setVariable(BR.context, mContext);
        viewHolder.getBinding().executePendingBindings();
        viewHolder.getBinding().cardView.setTag(snapShot);
    }

    /**
     * snapshot view holder
     */
    public static class SnapShotViewHolder extends RecyclerView.ViewHolder {
        private ViewArticleFeedBinding mBinding;

        SnapShotViewHolder(View view) {
            super(view);
        }

        public ViewArticleFeedBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewArticleFeedBinding binding) {
            this.mBinding = binding;
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
