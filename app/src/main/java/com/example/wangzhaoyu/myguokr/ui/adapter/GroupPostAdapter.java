package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewGroupPostItemBinding;
import com.example.wangzhaoyu.myguokr.model.response.PostSnapShot;
import com.example.wangzhaoyu.myguokr.ui.activity.PostActivity;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class GroupPostAdapter extends LoadmoreFooterViewAdapter {
    private static final String TAG = GroupPostAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<PostSnapShot> mPostSnapShots;

    public GroupPostAdapter(Context context, ArrayList<PostSnapShot> postSnapShots) {
        mContext = context;
        mPostSnapShots = postSnapShots;
    }

    @Override
    protected int getContentItemCount() {
        return mPostSnapShots.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        ViewGroupPostItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_group_post_item,
                parent,
                false);

        SnapShotViewHolder viewHolder = new SnapShotViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postId = (int) v.getTag();
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra(PostActivity.POST_ID_KEY, postId);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {
        PostSnapShot snapShot = mPostSnapShots.get(position);
        SnapShotViewHolder viewHolder = (SnapShotViewHolder) contentViewHolder;
        viewHolder.getBinding().setVariable(BR.postSnapShot, snapShot);
        viewHolder.getBinding().executePendingBindings();
        viewHolder.itemView.setTag(snapShot.getId());
    }

    public static class SnapShotViewHolder extends RecyclerView.ViewHolder {
        private ViewGroupPostItemBinding mBinding;

        public ViewGroupPostItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewGroupPostItemBinding binding) {
            mBinding = binding;
        }

        public SnapShotViewHolder(View itemView) {
            super(itemView);
        }
    }
}
