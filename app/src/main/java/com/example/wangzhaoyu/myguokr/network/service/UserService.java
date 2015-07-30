package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.core.SPUtils;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import retrofit.Callback;

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

    public void getUserInfo(String ukey, Callback<User> callback) {
        mGuokrAPI.getUserInfo(
                ukey,
                callback
        );
    }

    public void getNotifiCount(Callback<NotificationCount> callback) {
        mGuokrAPI.getNotifCount(
                System.currentTimeMillis(),
                getAccessToken(),
                callback
        );
    }
}
