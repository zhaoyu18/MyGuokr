package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.FavoriteGroup;
import com.example.wangzhaoyu.myguokr.model.response.GroupPostComments;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public Observable<GroupPosts> getGroupHotPostList(int offset) {
        return mGuokrAPI.getGroupHotPostList(
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }

    /**
     * get hot post list
     *
     * @param offset
     */
    public Observable<GroupPosts> getGroupHotPostListCacheFirst(int offset) {
        return mGuokrAPI.getGroupHotPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.HOT_POST,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get group post list
     *
     * @param offset
     * @param groupId
     */
    public Observable<GroupPosts> getGroupPostList(int offset, int groupId) {
        return mGuokrAPI.getGroupPostList(
                ApiConfig.Query.RetrieveType.BY_GROUP,
                groupId,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }

    /**
     * get group post list
     *
     * @param offset
     * @param groupId
     */
    public Observable<GroupPosts> getGroupPostListCacheFirst(int offset, int groupId) {
        return mGuokrAPI.getGroupPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.BY_GROUP,
                groupId,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get group user post list
     *
     * @param offset
     */
    public Observable<GroupPosts> getGroupUserPostList(int offset) {
        return mGuokrAPI.getGroupUserPostList(
                ApiConfig.Query.RetrieveType.RECENT_REPLIES,
                HttpService.getInstance().getUserService().getAccessToken(),
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }

    /**
     * get group user post list
     *
     * @param offset
     */
    public Observable<GroupPosts> getGroupUserPostListCacheFirst(int offset) {
        return mGuokrAPI.getGroupUserPostList(
                ApiConfig.Header.CacheControl.ONE_HOUR_STALE,
                ApiConfig.Query.RetrieveType.RECENT_REPLIES,
                HttpService.getInstance().getUserService().getAccessToken(),
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * get post comment
     *
     * @param offset
     * @param postId
     */
    public Observable<GroupPostComments> getGroupPostCommentList(int offset, int postId) {
        return mGuokrAPI.getGroupPostCommentList(
                ApiConfig.Query.RetrieveType.BY_POST,
                LIMIT,
                offset,
                postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }

    /**
     * get group post
     *
     * @param postId
     */
    public Observable<PostDetail> getGroupPost(int postId) {
        return mGuokrAPI.getGroupPost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }

    /**
     * get user favorite groups, 100 groups at most
     */
    public Observable<FavoriteGroup> getGroupFavorite() {
        return mGuokrAPI.getGroupFavorite(
                HttpService.getInstance().getUserService().getAccessToken(),
                100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3);
    }
}
