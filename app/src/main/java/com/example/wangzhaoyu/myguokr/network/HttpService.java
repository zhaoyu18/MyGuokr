package com.example.wangzhaoyu.myguokr.network;

import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.ArticleContentService;
import com.example.wangzhaoyu.myguokr.network.api.ArticleService;
import com.example.wangzhaoyu.myguokr.network.api.GroupService;
import com.example.wangzhaoyu.myguokr.network.api.UserService;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @author wangzhaoyu
 */
public class HttpService {
    private final ArticleService mArticleService;

    private final ArticleContentService mArticleContentService;

    private static HttpService ourInstance = new HttpService();
    private final UserService mUserService;
    private final GroupService mGroupService;

    public static HttpService getInstance() {
        return ourInstance;
    }

    private HttpService() {
        File httpCacheDir = new File(AppController.getInstance().getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDir, 10 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ApiConfig.GUOKR_APIS_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json;versions=1");
                        if (Utils.isNetworkConnectionAvailable(AppController.getInstance())) {
                            int maxAge = 60; // read from cache for 1 minute
                            request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                        } else {
                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                            request.addHeader("Cache-Control",
                                    "public, only-if-cached, max-stale=" + maxStale);
                        }
                    }
                })
                .build();
        mArticleService = restAdapter.create(ArticleService.class);

        mUserService = restAdapter.create(UserService.class);

        mGroupService = restAdapter.create(GroupService.class);

        RestAdapter rootUrlAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ApiConfig.GUOKR_ROOT_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json;versions=1");
                        if (Utils.isNetworkConnectionAvailable(AppController.getInstance())) {
                            int maxAge = 60; // read from cache for 1 minute
                            request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                        } else {
                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                            request.addHeader("Cache-Control",
                                    "public, only-if-cached, max-stale=" + maxStale);
                        }
                    }
                })
                .build();
        mArticleContentService = rootUrlAdapter.create(ArticleContentService.class);

    }

    public ArticleService getArticleService() {
        return mArticleService;
    }

    public ArticleContentService getArticleContentService() {
        return mArticleContentService;
    }

    public UserService getUserService() {
        return mUserService;
    }

    public GroupService getGroupService() {
        return mGroupService;
    }
}
