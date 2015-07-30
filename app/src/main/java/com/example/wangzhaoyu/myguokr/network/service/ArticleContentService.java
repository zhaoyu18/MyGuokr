package com.example.wangzhaoyu.myguokr.network.service;

import com.example.wangzhaoyu.myguokr.network.api.ApiConfig;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * @author wangzhaoyu
 */
public interface ArticleContentService {

    @GET(ApiConfig.HtmlAPI.ARTICLE_CONTENT)
    public void getArticleContent(
            @Path(ApiConfig.Path.ID) int articleId,
            Callback<Response> callback);
}
