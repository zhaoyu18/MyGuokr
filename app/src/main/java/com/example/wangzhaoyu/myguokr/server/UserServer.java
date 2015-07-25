package com.example.wangzhaoyu.myguokr.server;

import android.text.TextUtils;

import com.android.volley.Request;
import com.example.wangzhaoyu.myguokr.core.SPUtils;
import com.example.wangzhaoyu.myguokr.core.net.GuokrJsonRequest;
import com.example.wangzhaoyu.myguokr.core.net.NetManager;
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.User;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class UserServer {
    private static final String TAG = UserServer.class.getSimpleName();
    private User mUser;

    private static class InstanceHolder {
        public static UserServer holder = new UserServer();
    }

    public static UserServer getInstance() {
        return InstanceHolder.holder;
    }

    public String getAccessToken() {
        return SPUtils.getInstance().getString(SPUtils.KEYS.ACCESS_TOKEN);
    }

    public String getUserUkey() {
        return SPUtils.getInstance().getString(SPUtils.KEYS.USER_UKEY);
    }

    public void getUserInfo(String ukey, final ServerHandler<User> handler) {
        if (TextUtils.isEmpty(getUserUkey())) {
            handler.onRequestError();
            return;
        }

        String url = "http://apis.guokr.com/community/user/" + ukey + ".json";
        NetManager.getInstance().addToRequestQueue(
                new GuokrJsonRequest(Request.Method.GET, url, "", new DataListener<User>() {
                    @Override
                    public void onRequestSuccess(User user) {
                        handler.onRequestSuccess(user);
                        mUser = user;
                    }

                    @Override
                    public void onRequestError() {

                    }
                }));
    }

    public User getUser() {
        return mUser;
    }

    /**
     * get user notification count
     *
     * @param serverHandler
     */
    public void getNotifCount(final ServerHandler<NotificationCount> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.CURRENT_TIME, System.currentTimeMillis() + "");
        params.put(Network.Parameters.ACCESS_TOKEN, getAccessToken());
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.NOTIFICATION_COUNT,
                params, new DataListener<NotificationCount>() {
                    @Override
                    public void onRequestSuccess(NotificationCount data) {
                        serverHandler.onRequestSuccess(data);
                    }

                    @Override
                    public void onRequestError() {

                    }
                });
    }
}
