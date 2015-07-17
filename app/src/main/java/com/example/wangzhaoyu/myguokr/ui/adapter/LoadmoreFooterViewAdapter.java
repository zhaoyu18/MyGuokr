package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzhaoyu.myguokr.BR;
import com.example.wangzhaoyu.myguokr.R;
import com.example.wangzhaoyu.myguokr.databinding.ViewListFooterBinding;

/**
 * @author wangzhaoyu
 */
public abstract class LoadmoreFooterViewAdapter extends HeaderFooterRecyclerViewAdapter {
    private FooterModel mFooterModel = new FooterModel();

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 1;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        ViewListFooterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_list_footer,
                parent,
                false);

        FooterHolder holder = new FooterHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {

    }

    @Override
    protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position) {
        FooterHolder viewHolder = (FooterHolder) footerViewHolder;
        viewHolder.getBinding().setVariable(BR.footer, mFooterModel);
        viewHolder.getBinding().executePendingBindings();
    }

    /**
     * footer view holder
     */
    public static class FooterHolder extends RecyclerView.ViewHolder {
        private ViewListFooterBinding mBinding;

        FooterHolder(View itemView) {
            super(itemView);
        }

        public ViewListFooterBinding getBinding() {
            return mBinding;
        }

        public void setBinding(ViewListFooterBinding binding) {
            this.mBinding = binding;
        }
    }

    /**
     * set footer text
     *
     * @param text
     */
    public void setFooterText(String text) {
        mFooterModel.setText(text);
    }
}
