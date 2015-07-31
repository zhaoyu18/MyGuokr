package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class GroupService {
    private static final int LIMIT = 20;
    private GuokrAPI mGuokrAPI;

    public GroupService(GuokrAPI guokrAPI) {
        mGuokrAPI = guokrAPI;
    }

    public void getGroupPostList(int offset) {
        mGuokrAPI.getGroupPostList(
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset,
                new Callback<GroupPosts>() {
                    @Override
                    public void success(GroupPosts posts, Response response) {
                        EventBus.getDefault().post(posts);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void getGroupPostCommentList(int offset, int postId) {
        mGuokrAPI.getGroupPostCommentList(
                ApiConfig.Query.RetrieveType.BY_POST,
                LIMIT,
                offset,
                postId,
                new Callback<GroupPostComments>() {
                    @Override
                    public void success(GroupPostComments comments, Response response) {
                        EventBus.getDefault().post(comments);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void getGroupPost(int postId) {
        mGuokrAPI.getGroupPost(
                postId,
                new Callback<PostDetail>() {
                    @Override
                    public void success(PostDetail detail, Response response) {
                        EventBus.getDefault().post(detail);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }
}
