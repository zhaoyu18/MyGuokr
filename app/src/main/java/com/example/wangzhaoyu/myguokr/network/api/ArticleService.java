package com.example.wangzhaoyu.myguokr.network.api;

import com.example.wangzhaoyu.myguokr.model.response.ArticleList;
import com.example.wangzhaoyu.myguokr.model.response.ArticleReplies;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author wangzhaoyu
 */
public interface ArticleService {

    @GET(ApiConfig.API.MINISITE_ARTICLE)
    public void getArticleList(
            @Query(ApiConfig.Parameters.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Parameters.LIMIT) int limit,
            @Query(ApiConfig.Parameters.OFFSET) int offset,
            Callback<ArticleList> callback);

    @GET(ApiConfig.API.ARTICLE_REPLY)
    public void getArticleCommentList(
            @Query(ApiConfig.Parameters.RETRIEVE_TYPE) String retrieveType,
            @Query(ApiConfig.Parameters.ARTICLE_ID) int articleId,
            @Query(ApiConfig.Parameters.OFFSET) int offset,
            Callback<ArticleReplies> callback);
}
