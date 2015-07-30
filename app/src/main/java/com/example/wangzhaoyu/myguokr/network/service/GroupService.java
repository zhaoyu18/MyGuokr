package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import retrofit.Callback;

/**
 * @author wangzhaoyu
 */
public class GroupService {
    private static final int LIMIT = 20;
    private GuokrAPI mGuokrAPI;

    public GroupService(GuokrAPI guokrAPI) {
        mGuokrAPI = guokrAPI;
    }

    public void getGroupPostList(int offset, Callback<GroupPosts> callback) {
        mGuokrAPI.getGroupPostList(
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset,
                callback
        );
    }

    public void getGroupPostCommentList(int offset, int postId, Callback<GroupPostComments> callback) {
        mGuokrAPI.getGroupPostCommentList(
                ApiConfig.Query.RetrieveType.BY_POST,
                LIMIT,
                offset,
                postId,
                callback
        );
    }

    public void getGroupPost(int postId, Callback<PostDetail> callback) {
        mGuokrAPI.getGroupPost(
                postId,
                callback
        );
    }
}
