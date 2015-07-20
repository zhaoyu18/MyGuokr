package com.example.wangzhaoyu.myguokr.server;

import com.example.wangzhaoyu.myguokr.core.net.NetManager;
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;
import com.example.wangzhaoyu.myguokr.model.response.GroupPosts;
import com.example.wangzhaoyu.myguokr.model.response.PostDetail;
import com.example.wangzhaoyu.myguokr.model.response.PostSnapShot;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class GroupServer {
    private static final String TAG = GroupServer.class.getSimpleName();
    private static final int LOAD_LIMIT = 20;

    //单例
    private static class InstanceHolder {
        public static GroupServer holder = new GroupServer();
    }

    public static GroupServer getInstance() {
        return InstanceHolder.holder;
    }

    /**
     * 刷新果壳小组post列表
     *
     * @param serverHandler
     */
    public void refreshPostList(final ServerHandler<ArrayList<PostSnapShot>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.HOT_POST);
        params.put(Network.Parameters.LIMIT, LOAD_LIMIT + "");
        params.put(Network.Parameters.OFFSET, 0 + "");
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.GROUP_POST,
                params, new DataListener<GroupPosts>() {
                    @Override
                    public void onRequestSuccess(GroupPosts data) {
                        serverHandler.onRequestSuccess(data.getResult());
                    }

                    @Override
                    public void onRequestError() {
                        serverHandler.onRequestError();
                    }
                });
    }

    /**
     * load more
     *
     * @param offset
     * @param serverHandler
     */
    public void loadMorePostList(int offset, final ServerHandler<ArrayList<PostSnapShot>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.HOT_POST);
        params.put(Network.Parameters.LIMIT, LOAD_LIMIT + "");
        params.put(Network.Parameters.OFFSET, offset + "");
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.GROUP_POST,
                params, new DataListener<GroupPosts>() {
                    @Override
                    public void onRequestSuccess(GroupPosts data) {
                        serverHandler.onRequestSuccess(data.getResult());
                    }

                    @Override
                    public void onRequestError() {
                        serverHandler.onRequestError();
                    }
                });
    }

    /**
     * request post detail
     *
     * @param id
     */
    public void getPostDetail(int id, final ServerHandler<PostDetail> serverHandler) {
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.GROUP_POST_DETAIL,
                null, id, new DataListener<PostDetail>() {
                    @Override
                    public void onRequestSuccess(PostDetail data) {
                        serverHandler.onRequestSuccess(data);
                    }

                    @Override
                    public void onRequestError() {

                    }
                });
    }

}