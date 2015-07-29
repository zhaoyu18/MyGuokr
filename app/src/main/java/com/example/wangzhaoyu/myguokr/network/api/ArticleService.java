package com.example.wangzhaoyu.myguokr.network.api;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;
import com.example.wangzhaoyu.myguokr.model.response.ArticleSendComment;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * @author wangzhaoyu
 */
public interface ArticleService {

    @GET(ApiConfig.API.MINISITE_ARTICLE)
    public void getArticleList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.LIMIT) int limit,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<ArticleList> callback);

    @GET(ApiConfig.API.ARTICLE_REPLY)
    public void getArticleCommentList(
            @Query(ApiConfig.Query.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Query.ARTICLE_ID) int articleId,
            @Query(ApiConfig.Query.OFFSET) int offset,
            Callback<ArticleReplies> callback);

    @POST(ApiConfig.API.ARTICLE_REPLY)
    public void postArticleComment(
            @Query(ApiConfig.Query.ARTICLE_ID) int articleId,
            @Query(ApiConfig.Query.CONTENT) String content,
            @Query(ApiConfig.Query.ACCESS_TOKEN) String accessToken,
            Callback<ArticleSendComment> callBack);
}
