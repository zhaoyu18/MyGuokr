package com.example.wangzhaoyu.myguokr.core;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author wangzhaoyu
 */
public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    private static class InstanceHolder {
        public static ImageUtils holder = new ImageUtils();
    }

    public static ImageUtils getInstance() {
        return InstanceHolder.holder;
    }

    /**
     * 头像圆形图片option
     *
     * @param size
     * @return
     */
    public static DisplayImageOptions getAvatarDisplayOptions(int size) {
        return new DisplayImageOptions.Builder()
//                .showImageOnFail(R.mipmap.ic_launcher)
//                .showImageForEmptyUri(R.mipmap.ic_launcher)
//                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(size)).build();
    }
}
