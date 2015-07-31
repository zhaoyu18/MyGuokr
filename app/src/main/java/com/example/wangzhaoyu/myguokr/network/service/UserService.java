package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.core.SPUtils;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

    public void getUserInfo(String ukey) {
        mGuokrAPI.getUserInfo(
                ukey,
                new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        EventBus.getDefault().post(user);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void getNotifiCount() {
        mGuokrAPI.getNotifCount(
                System.currentTimeMillis(),
                getAccessToken(),
                new Callback<NotificationCount>() {
                    @Override
                    public void success(NotificationCount count, Response response) {
                        EventBus.getDefault().post(count);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }
}
