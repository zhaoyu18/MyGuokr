package com.example.wangzhaoyu.myguokr;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

    private RequestQueue mReqQueue;
    private Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initImageLoader();
    }

    /**
     * 初始化imageloader
     */
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(5)
                .memoryCache(new WeakMemoryCache())
                .writeDebugLogs()
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
//                .memoryCacheSize(20 * 1024 * 1024)
//                .memoryCacheSizePercentage(20)
//                .memoryCacheExtraOptions(480, 800)
                .build();

        ImageLoader.getInstance().init(config);
    }

    /**
     * 返回单例
     *
     * @return AppController单例
     */
    public static synchronized AppController getInstance() {
        return sInstance;
    }

    /**
     * 获取request队列
     *
     * @return 请求队列
     */
    public RequestQueue getRequestQueue() {
        if (mReqQueue == null) {
            mReqQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mReqQueue;
    }

    /**
     * 添加请求到队列
     *
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        //如果为空，设置默认tag
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * 按照tag取消请求
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mReqQueue != null) {
            mReqQueue.cancelAll(tag);
        }
    }

    public Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }
}
