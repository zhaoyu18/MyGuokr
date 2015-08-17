package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;
import com.example.wangzhaoyu.myguokr.network.HttpService;
import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;
import com.example.wangzhaoyu.myguokr.network.api.GuokrAPI;
import com.example.wangzhaoyu.myguokr.network.api.GuokrHtmlAPI;

import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public Observable<ArticleList> getArticleList(int offset) {
        return mGuokrAPI.getArticleList(
                ApiConfig.Query.RetrieveType.BY_SUBJECT,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArticleReplies> getArticleCommentList(int articleId, int offset) {
        return mGuokrAPI.getArticleCommentList(
                ApiConfig.Query.RetrieveType.BY_ARTICLE,
                articleId,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArticleSendComment> postArticleComment(int articleId, String content) {
        return mGuokrAPI.postArticleComment(
                articleId,
                content,
                HttpService.getInstance().getUserService().getAccessToken(),
                "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response> getArticleContent(int articleId) {
        return mGuokrHtmlAPI.getArticleContent(
                articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
