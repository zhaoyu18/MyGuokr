package com.example.wangzhaoyu.myguokr;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.wangzhaoyu.myguokr.core.SPUtils;
import com.example.wangzhaoyu.myguokr.core.net.NetManager;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author wangzhaoyu
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController sInstance;

    /**
     * 返回单例
     *
     * @return AppController单例
     */
    public static synchronized AppController getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        // 初始化SharedPreferences
        SPUtils.getInstance().init(getApplicationContext());

        //init image loader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(5)
                .memoryCache(new WeakMemoryCache())
//                .writeDebugLogs()
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
//                .memoryCacheSize(20 * 1024 * 1024)
//                .memoryCacheSizePercentage(20)
//                .memoryCacheExtraOptions(480, 800)
                .build();

        ImageLoader.getInstance().init(config);

        //init net manager
        NetManager.getInstance().init(getApplicationContext());
    }
}
