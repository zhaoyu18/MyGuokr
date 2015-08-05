package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.network.HttpService;
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

    /**
     * get post list
     *
     * @param offset
     */
    public void getGroupPostList(int offset, final EventBus eventBus) {
        Callback<GroupPosts> callback = new Callback<GroupPosts>() {
            @Override
            public void success(GroupPosts posts, Response response) {
                eventBus.post(posts);
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(error);
            }
        };

        mGuokrAPI.getGroupPostList(
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset,
                callback);
    }

    /**
     * get post comment
     *
     * @param offset
     * @param postId
     */
    public void getGroupPostCommentList(int offset, int postId, final EventBus eventBus) {
        Callback<GroupPostComments> callback = new Callback<GroupPostComments>() {
            @Override
            public void success(GroupPostComments comment, Response response) {
                eventBus.post(comment);
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(error);
            }
        };

        mGuokrAPI.getGroupPostCommentList(
                ApiConfig.Query.RetrieveType.BY_POST,
                LIMIT,
                offset,
                postId,
                callback);
    }

    /**
     * get group post
     *
     * @param postId
     */
    public void getGroupPost(int postId, final EventBus eventBus) {
        Callback<PostDetail> callback = new Callback<PostDetail>() {
            @Override
            public void success(PostDetail detail, Response response) {
                eventBus.post(detail);
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(error);
            }
        };

        mGuokrAPI.getGroupPost(postId, callback);
    }

    /**
     * get user favorite groups, 100 groups at most
     *
     * @param eventBus
     */
    public void getGroupFavorite(final EventBus eventBus) {
        Callback<FavoriteGroup> callback = new Callback<FavoriteGroup>() {
            @Override
            public void success(FavoriteGroup group, Response response) {
                eventBus.post(group);
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(error);
            }
        };

        mGuokrAPI.getGroupFavorite(
                HttpService.getInstance().getUserService().getAccessToken(),
                100,
                callback
        );
    }
}
