package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewGroupChannelItemBinding;
import com.example.wangzhaoyu.myguokr.model.response.Group;

import java.util.List;

/**
 * @author wangzhaoyu
 */
public class GroupChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final List<Group> mGroups;

    public GroupChannelAdapter(Context context, List<Group> groups) {
        mContext = context;
        mGroups = groups;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroupChannelItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_group_channel_item,
                parent,
                false);
        ChannelViewHolder holder = new ChannelViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelViewHolder viewHolder = (ChannelViewHolder) holder;
        Group group = mGroups.get(position);
        viewHolder.getBinding().setVariable(BR.group, group);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    /**
     * group channel view holder
     */
    public static class ChannelViewHolder extends RecyclerView.ViewHolder {
        private ViewGroupChannelItemBinding mBinding;

        public ViewGroupChannelItemBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewGroupChannelItemBinding binding) {
            mBinding = binding;
        }

        public ChannelViewHolder(View itemView) {
            super(itemView);
        }
    }
}
