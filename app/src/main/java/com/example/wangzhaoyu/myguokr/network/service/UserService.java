package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.core.SPUtils;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wangzhaoyu
 */
public class UserService {
    private final static int LIMIT = 20;
    private GuokrAPI mGuokrAPI;

    public UserService(GuokrAPI guokrAPI) {
        mGuokrAPI = guokrAPI;
    }

    public String getAccessToken() {
        return SPUtils.getInstance().getString(SPUtils.KEYS.ACCESS_TOKEN);
    }

    public String getUserUkey() {
        return SPUtils.getInstance().getString(SPUtils.KEYS.USER_UKEY);
    }

    public Observable<NotificationCount> getNotifiCount() {
        return mGuokrAPI.getNotifCount(
                System.currentTimeMillis(),
                getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> getUserInfo(String ukey) {
        return mGuokrAPI.getUserInfo(ukey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
