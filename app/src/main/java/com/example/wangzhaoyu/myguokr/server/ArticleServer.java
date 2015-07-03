package com.example.wangzhaoyu.myguokr.server;

import com.example.wangzhaoyu.myguokr.core.net.DataListener;
import com.example.wangzhaoyu.myguokr.core.net.NetManager;
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleList;
import com.example.wangzhaoyu.myguokr.model.reply.ArticleSnapShot;
import com.example.wangzhaoyu.myguokr.server.handler.ServerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class ArticleServer {
    private static final String TAG = ArticleServer.class.getSimpleName();

    //单例
    private static class InstanceHolder {
        public static ArticleServer holder = new ArticleServer();
    }

    public static ArticleServer getInstance() {
        return InstanceHolder.holder;
    }

    /**
     * 获取科学人文章列表
     *
     * @param serverHandler
     */
    public void getArticleList(final ServerHandler<ArrayList<ArticleSnapShot>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.BY_SUBJECT);
        params.put(Network.Parameters.LIMIT, 20 + "");
        params.put(Network.Parameters.OFFSET, 0 + "");
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.MINISITE_ARTICLE,
                params, new DataListener<ArticleList>() {
                    @Override
                    public void onRequestSuccess(ArticleList data) {
                        serverHandler.onRequestSuccess(data.getResult());
                    }

                    @Override
                    public void onRequestError() {

                    }
                });
    }
}
