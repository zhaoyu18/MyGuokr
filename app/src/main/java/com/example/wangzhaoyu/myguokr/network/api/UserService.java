package com.example.wangzhaoyu.myguokr.network.api;

import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author wangzhaoyu
 */
public interface UserService {

    @GET(ApiConfig.API.COMMUNITY_USER)
    public void getUserInfo(
            @Path(ApiConfig.Path.UKEY) String ukey,
            Callback<User> callback);

    @GET(ApiConfig.API.NOTIFICATION_COUNT)
    public void getNotifCount(
            @Query(ApiConfig.Query.CURRENT_TIME) long currentTime,
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            Callback<NotificationCount> callback);
}
