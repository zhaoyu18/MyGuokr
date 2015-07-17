package com.example.wangzhaoyu.myguokr.ui.adapter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.wangzhaoyu.myguokr.BR;

/**
 * @author wangzhaoyu
 */
public class FooterModel extends BaseObservable {
    private String text;

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
