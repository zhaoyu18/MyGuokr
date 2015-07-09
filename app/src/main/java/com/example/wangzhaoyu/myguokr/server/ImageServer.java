package com.example.wangzhaoyu.myguokr.server;

import com.example.wangzhaoyu.myguokr.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author wangzhaoyu
 */
public class ImageServer {
    private static final String TAG = ImageServer.class.getSimpleName();

    private static class InstanceHolder {
        public static ImageServer holder = new ImageServer();
    }

    public static ImageServer getInstance() {
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
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(size)).build();
    }
}
