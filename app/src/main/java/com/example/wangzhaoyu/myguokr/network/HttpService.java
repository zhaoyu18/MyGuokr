package com.example.wangzhaoyu.myguokr.network;

import com.example.wangzhaoyu.myguokr.AppController;
import com.example.wangzhaoyu.myguokr.core.Utils;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;
import com.example.wangzhaoyu.myguokr.network.api.GuokrHtmlAPI;
import com.example.wangzhaoyu.myguokr.network.service.ArticleService;
import com.example.wangzhaoyu.myguokr.network.service.GroupService;
import com.example.wangzhaoyu.myguokr.network.service.UserService;
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
    private final UserService mUserService;
    private final GroupService mGroupService;
    private final GuokrAPI mGuokrAPI;
    private final GuokrHtmlAPI mGuokrHtmlAPI;
    private static HttpService ourInstance = new HttpService();

    public static HttpService getInstance() {
        return ourInstance;
    }

    private HttpService() {
        File httpCacheDir = new File(AppController.getInstance().getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDir, 10 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        okHttpClient.setRetryOnConnectionFailure(true);

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
        mGuokrAPI = restAdapter.create(GuokrAPI.class);

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
        mGuokrHtmlAPI = rootUrlAdapter.create(GuokrHtmlAPI.class);

        //init service
        mArticleService = new ArticleService(mGuokrAPI, mGuokrHtmlAPI);
        mUserService = new UserService(mGuokrAPI);
        mGroupService = new GroupService(mGuokrAPI);
    }

    public ArticleService getArticleService() {
        return mArticleService;
    }

    public UserService getUserService() {
        return mUserService;
    }

    public GroupService getGroupService() {
        return mGroupService;
    }
}
