package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;
import com.example.wangzhaoyu.myguokr.network.api.GuokrHtmlAPI;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * @author wangzhaoyu
 */
public class ArticleService {
    private final static int LIMIT = 20;
    private GuokrAPI mGuokrAPI;
    private GuokrHtmlAPI mGuokrHtmlAPI;

    public ArticleService(GuokrAPI guokrAPI, GuokrHtmlAPI guokrHtmlAPI) {
        mGuokrHtmlAPI = guokrHtmlAPI;
        mGuokrAPI = guokrAPI;
    }

    public void getArticleList(int offset, Callback<ArticleList> callback) {
        mGuokrAPI.getArticleList(
                ApiConfig.Query.RetrieveType.BY_SUBJECT,
                LIMIT,
                offset,
                callback
        );
    }

    public void getArticleCommentList(int articleId, int offset, Callback<ArticleReplies> callback) {
        mGuokrAPI.getArticleCommentList(
                ApiConfig.Query.RetrieveType.BY_ARTICLE,
                articleId,
                offset,
                callback
        );
    }

    public void postArticleComment(int articleId, String content, Callback<ArticleSendComment> callback) {
        mGuokrAPI.postArticleComment(
                articleId,
                content,
                HttpService.getInstance().getUserService().getAccessToken(),
                "", //body required..
                callback
        );
    }

    public void getArticleContent(int articleId, Callback<Response> callback) {
        mGuokrHtmlAPI.getArticleContent(
                articleId,
                callback
        );
    }
}
