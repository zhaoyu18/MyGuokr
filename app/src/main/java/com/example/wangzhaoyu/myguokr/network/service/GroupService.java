package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;
import com.example.wangzhaoyu.myguokr.network.callback.GuokrCallback;

import java.util.ArrayList;

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
    private ArrayList<GuokrCallback> mCallbacks = new ArrayList<>();

    public GroupService(GuokrAPI guokrAPI) {
        mGuokrAPI = guokrAPI;
    }

    /**
     * get post list
     *
     * @param offset
     * @param tag
     */
    public void getGroupPostList(int offset, String tag) {
        GuokrCallback<GroupPosts> callback = new GuokrCallback<GroupPosts>(tag, new Callback<GroupPosts>() {
            @Override
            public void success(GroupPosts posts, Response response) {
                EventBus.getDefault().post(posts);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        }) {
            @Override
            public void response() {
                //remove callback from list, when response
                mCallbacks.remove(this);
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
     * @param tag
     */
    public void getGroupPostCommentList(int offset, int postId, String tag) {
        GuokrCallback<GroupPostComments> callback = new GuokrCallback<GroupPostComments>(tag, new Callback<GroupPostComments>() {
            @Override
            public void success(GroupPostComments comment, Response response) {
                EventBus.getDefault().post(comment);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        }) {
            @Override
            public void response() {
                //remove callback from list, when response
                mCallbacks.remove(this);
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
     * @param tag
     */
    public void getGroupPost(int postId, String tag) {
        GuokrCallback<PostDetail> callback = new GuokrCallback<PostDetail>(tag, new Callback<PostDetail>() {
            @Override
            public void success(PostDetail detail, Response response) {
                EventBus.getDefault().post(detail);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        }) {
            @Override
            public void response() {
                //remove callback from list, when response
                mCallbacks.remove(this);
            }
        };

        mCallbacks.add(callback);
        mGuokrAPI.getGroupPost(postId, callback);
    }

    /**
     * get user favorite groups, 100 groups at most
     *
     * @param tag
     */
    public void getGroupFavorite(String tag) {
        GuokrCallback<FavoriteGroup> callback = new GuokrCallback<FavoriteGroup>(tag, new Callback<FavoriteGroup>() {
            @Override
            public void success(FavoriteGroup group, Response response) {
                EventBus.getDefault().post(group);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        }) {
            @Override
            public void response() {
                //remove callback from list, when response
                mCallbacks.remove(this);
            }
        };

        mCallbacks.add(callback);
        mGuokrAPI.getGroupFavorite(
                HttpService.getInstance().getUserService().getAccessToken(),
                100,
                callback
        );
    }

    /**
     * cancel request by blocking callback -- temp solution because retrofit not support cancel
     *
     * @param tag
     */
    public void cancelRequest(String tag) {
        for (GuokrCallback callback : mCallbacks) {
            if (callback.getTag().equals(tag)) {
                callback.cancel();
            }
        }
    }
}
