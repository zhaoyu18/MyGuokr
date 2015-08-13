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
     * get hot post list
     *
     * @param offset
     */
    public void getGroupHotPostList(int offset, final EventBus eventBus) {
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

        mGuokrAPI.getGroupHotPostList(
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset,
                callback);
    }

    /**
     * get hot post list
     *
     * @param offset
     */
    public void getGroupHotPostListCacheFirst(int offset, final EventBus eventBus) {
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

        mGuokrAPI.getGroupHotPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset,
                callback);
    }

    /**
     * get group post list
     *
     * @param offset
     * @param groupId
     * @param eventBus
     */
    public void getGroupPostList(int offset, int groupId, final EventBus eventBus) {
        mGuokrAPI.getGroupPostList(
                ApiConfig.Query.RetrieveType.BY_GROUP,
                groupId,
                LIMIT,
                offset,
                new Callback<GroupPosts>() {
                    @Override
                    public void success(GroupPosts posts, Response response) {
                        eventBus.post(posts);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        eventBus.post(error);
                    }
                }
        );
    }

    /**
     * get group post list
     *
     * @param offset
     * @param groupId
     * @param eventBus
     */
    public void getGroupPostListCacheFirst(int offset, int groupId, final EventBus eventBus) {
        mGuokrAPI.getGroupPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.BY_GROUP,
                groupId,
                LIMIT,
                offset,
                new Callback<GroupPosts>() {
                    @Override
                    public void success(GroupPosts posts, Response response) {
                        eventBus.post(posts);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        eventBus.post(error);
                    }
                }
        );
    }

    /**
     * get group user post list
     *
     * @param offset
     * @param eventBus
     */
    public void getGroupUserPostList(int offset, final EventBus eventBus) {
        mGuokrAPI.getGroupUserPostList(
                ApiConfig.Query.RetrieveType.RECENT_REPLIES,
                HttpService.getInstance().getUserService().getAccessToken(),
                LIMIT,
                offset,
                new Callback<GroupPosts>() {
                    @Override
                    public void success(GroupPosts posts, Response response) {
                        eventBus.post(posts);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        eventBus.post(error);
                    }
                }
        );
    }

    /**
     * get group user post list
     *
     * @param offset
     * @param eventBus
     */
    public void getGroupUserPostListCacheFirst(int offset, final EventBus eventBus) {
        mGuokrAPI.getGroupUserPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.RECENT_REPLIES,
                HttpService.getInstance().getUserService().getAccessToken(),
                LIMIT,
                offset,
                new Callback<GroupPosts>() {
                    @Override
                    public void success(GroupPosts posts, Response response) {
                        eventBus.post(posts);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        eventBus.post(error);
                    }
                }
        );
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
