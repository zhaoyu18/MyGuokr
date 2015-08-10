package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewGroupChannelItemBinding;
import com.example.wangzhaoyu.myguokr.model.response.Group;
import com.example.wangzhaoyu.myguokr.ui.widget.touchhelper.ItemTouchHelperAdapter;
import com.example.wangzhaoyu.myguokr.ui.widget.touchhelper.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * @author wangzhaoyu
 */
public class GroupChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperAdapter {

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

    //for drag and drop
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mGroups, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    //for swipe to dismiss
    @Override
    public void onItemDismiss(int position) {
        mGroups.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * group channel view holder
     */
    public static class ChannelViewHolder extends RecyclerView.ViewHolder
            implements ItemTouchHelperViewHolder {
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

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
