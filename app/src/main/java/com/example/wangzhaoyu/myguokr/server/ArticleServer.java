package com.example.wangzhaoyu.myguokr.server;

import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;
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
    private static final int LOAD_LIMIT = 20;

    //单例
    private static class InstanceHolder {
        public static ArticleServer holder = new ArticleServer();
    }

    public static ArticleServer getInstance() {
        return InstanceHolder.holder;
    }

    /**
     * 刷新科学人文章列表
     *
     * @param serverHandler
     */
    public void refreshArticleList(final ServerHandler<ArrayList<ArticleSnapShot>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.BY_SUBJECT);
        params.put(Network.Parameters.LIMIT, LOAD_LIMIT + "");
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

    /**
     * 加载更多article
     *
     * @param serverHandler
     */
    public void loadMoreArticleList(int offset, final ServerHandler<ArrayList<ArticleSnapShot>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.BY_SUBJECT);
        params.put(Network.Parameters.LIMIT, LOAD_LIMIT + "");
        params.put(Network.Parameters.OFFSET, offset + "");
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

    /**
     * 根据文章id加载文章内容
     *
     * @param articleUrl
     */
    public void getArticleDetail(String articleUrl) {
        NetManager.getInstance().requestHtml(articleUrl);
    }
}
