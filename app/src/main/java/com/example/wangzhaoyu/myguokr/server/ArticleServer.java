package com.example.wangzhaoyu.myguokr.server;

import com.example.wangzhaoyu.myguokr.core.net.NetManager;
import com.example.wangzhaoyu.myguokr.core.net.Network;
import com.example.wangzhaoyu.myguokr.core.net.callback.DataListener;
import com.example.wangzhaoyu.myguokr.core.net.callback.HtmlDataListener;
import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReply;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSnapShot;
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
    public void getArticleDetail(String articleUrl, HtmlDataListener dataListener) {
        NetManager.getInstance().requestHtml(articleUrl, dataListener);
    }

    /**
     * 根据文章resource url获取文章详情
     *
     * @param resUrl
     * @param dataListener
     */
    public void getArticleDetail(String resUrl, DataListener dataListener) {
        NetManager.getInstance().request(Network.HttpMethod.GET, resUrl, dataListener);
    }

    /**
     * 刷新评论
     *
     * @param articleId
     * @param serverHandler
     */
    public void getArticleReplies(int articleId, final ServerHandler<ArrayList<ArticleReply>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.BY_ARTICLE);
        params.put(Network.Parameters.ARTICLE_ID, articleId + "");
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.ARTICLE_REPLY,
                params, new DataListener<ArticleReplies>() {
                    @Override
                    public void onRequestSuccess(ArticleReplies data) {
                        serverHandler.onRequestSuccess(data.getResult());
                    }

                    @Override
                    public void onRequestError() {
                        serverHandler.onRequestError();
                    }
                });
    }

    /**
     * 加载更多评论
     *
     * @param articleId
     * @param offset
     * @param serverHandler
     */
    public void loadMoreArticleReplies(int articleId,
                                       int offset,
                                       final ServerHandler<ArrayList<ArticleReply>> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.RETRIEVE_TYPE, Network.Parameters.RetrieveType.BY_ARTICLE);
        params.put(Network.Parameters.ARTICLE_ID, articleId + "");
        params.put(Network.Parameters.OFFSET, offset + "");
        NetManager.getInstance().request(Network.HttpMethod.GET, Network.API.ARTICLE_REPLY,
                params, new DataListener<ArticleReplies>() {
                    @Override
                    public void onRequestSuccess(ArticleReplies data) {
                        serverHandler.onRequestSuccess(data.getResult());
                    }

                    @Override
                    public void onRequestError() {
                        serverHandler.onRequestError();
                    }
                });
    }

    /**
     * 对文章发表点评
     *
     * @param articleId
     * @param content
     * @param serverHandler
     */
    public void sendArticleComment(int articleId,
                                   String content,
                                   final ServerHandler<ArticleSendComment> serverHandler) {
        Map<String, String> params = new HashMap<>();
        params.put(Network.Parameters.ARTICLE_ID, articleId + "");
        params.put(Network.Parameters.CONTENT, content);
        params.put(Network.Parameters.ACCESS_TOKEN, UserServer.getInstance().getAccessToken());

        NetManager.getInstance().request(Network.HttpMethod.POST, Network.API.ARTICLE_REPLY,
                params, new DataListener<ArticleSendComment>() {
                    @Override
                    public void onRequestSuccess(ArticleSendComment data) {
                        serverHandler.onRequestSuccess(data);
                    }

                    @Override
                    public void onRequestError() {
                        serverHandler.onRequestError();
                    }
                });
    }
}
