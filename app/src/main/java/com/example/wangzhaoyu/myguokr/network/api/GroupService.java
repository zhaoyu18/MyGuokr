package com.example.wangzhaoyu.myguokr.network.api;

import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author wangzhaoyu
 */
public interface GroupService {

    @GET(ApiConfig.API.GROUP_POST)
    public void getGroupPostList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
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
}
