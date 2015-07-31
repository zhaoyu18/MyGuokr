package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;
import com.example.wangzhaoyu.myguokr.network.api.GuokrHtmlAPI;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
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

    public void getArticleList(int offset) {
        mGuokrAPI.getArticleList(
                ApiConfig.Query.RetrieveType.BY_SUBJECT,
                LIMIT,
                offset,
                new Callback<ArticleList>() {
                    @Override
                    public void success(ArticleList list, Response response) {
                        EventBus.getDefault().post(list);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void getArticleCommentList(int articleId, int offset) {
        mGuokrAPI.getArticleCommentList(
                ApiConfig.Query.RetrieveType.BY_ARTICLE,
                articleId,
                offset,
                new Callback<ArticleReplies>() {
                    @Override
                    public void success(ArticleReplies replies, Response response) {
                        EventBus.getDefault().post(replies);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void postArticleComment(int articleId, String content) {
        mGuokrAPI.postArticleComment(
                articleId,
                content,
                HttpService.getInstance().getUserService().getAccessToken(),
                "", //body required..
                new Callback<ArticleSendComment>() {
                    @Override
                    public void success(ArticleSendComment comment, Response response) {
                        EventBus.getDefault().post(comment);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(error);
                    }
                }
        );
    }

    public void getArticleContent(int articleId) {
        mGuokrHtmlAPI.getArticleContent(
                articleId,
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        EventBus.getDefault().post(response);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );
    }
}
