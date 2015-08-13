package com.example.wangzhaoyu.myguokr.network.api;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.NotificationCount;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.model.response.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author wangzhaoyu
 */
public interface GuokrAPI {

    //article
    @GET(ApiConfig.API.MINISITE_ARTICLE)
    public void getArticleList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<ArticleList> callback);

    @GET(ApiConfig.API.ARTICLE_REPLY)
    public void getArticleCommentList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.ARTICLE_ID) int articleId,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<ArticleReplies> callback);

    @POST(ApiConfig.API.ARTICLE_REPLY)
    public void postArticleComment(
            @Query(ApiConfig.Query.ARTICLE_ID) int articleId,
            @Query(ApiConfig.Query.CONTENT) String content,
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            @Body String body,
            Callback<ArticleSendComment> callBack);

    //group
    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupHotPostList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupHotPostList(
            @Header(ApiConfig.Header.CACHE_CONTROL) String cacheControl,
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupPostList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.GROUP_ID) int groupId,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupPostList(
            @Header(ApiConfig.Header.CACHE_CONTROL) String cacheControl,
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.GROUP_ID) int groupId,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupUserPostList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupUserPostList(
            @Header(ApiConfig.Header.CACHE_CONTROL) String cacheControl,
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<GroupPosts> callback);

    @GET(ApiConfig.API.GROUP_POST_DETAIL)
    public void getGroupPost(
            @Path(ApiConfig.Path.ID) int postId,
            Callback<PostDetail> callback);

    @GET(ApiConfig.API.GROUP_POST_COMMENT)
    public void getGroupPostCommentList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            @Query(ApiConfig.Query.POST_ID) int postId,
            Callback<GroupPostComments> callback);

    @GET(ApiConfig.API.GROUP_FAVORITE)
    public void getGroupFavorite(
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            @Query(ApiConfig.Query.LIMIT) int offset,
            Callback<FavoriteGroup> callback);

    //user
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
