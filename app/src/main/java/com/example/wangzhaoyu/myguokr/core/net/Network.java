package com.example.wangzhaoyu.myguokr.core.net;

import com.android.volley.Request;

/**
 * 网络请求api
 *
 * @author wangzhaoyu
 */
public class Network {
    //根节点
    public static final String GUOKR_ROOT_URL = "http://apis.guokr.com";
    public static final String GUOKR_CLIENT_ID = "32393";
    public static final String GUOKR_CLIENT_SECRET = "cmkevizuydunmcn0h5pu2tch3b7pz5zeh7ubjsp4";

    /**
     * HttpHeader信息配置类
     */
    public final class HttpHeader {
        public final class KEY {
            public static final String CLIENT_SOURCE = "client-source";
            public static final String AUTHORIZATION = "authorization";
        }

        public final class VALUE {
            public static final String CLIENT_SOURCE = "android";
        }
    }

    /**
     * 变量命名规则：
     */
    public final class API {
        // 科学人article list
        public static final String MINISITE_ARTICLE = "apis.guokr.com/minisite/article.json";
        public static final String ARTICLE_REPLY = "apis.guokr.com/minisite/article_reply.json";
        public static final String GROUP_POST = "apis.guokr.com/group/post.json";
        public static final String GROUP_POST_DETAIL = "apis.guokr.com/group/post/{id}.json";
        public static final String GROUP_POST_COMMENT = "apis.guokr.com/group/post_reply.json";
        public static final String NOTIFICATION_COUNT = "apis.guokr.com/community/rn_num.json";
    }

    /**
     * 参数
     */
    public final class Parameters {
        public static final String RETRIEVE_TYPE = "retrieve_type";
        public static final String LIMIT = "limit";
        public static final String OFFSET = "offset";
        public static final String SUBJECT_KEY = "subject_key";
        public static final String CHANNEL_KEY = "channel_key";
        public static final String ARTICLE_ID = "article_id";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String CONTENT = "content";
        public static final String POST_ID = "post_id";
        public static final String CURRENT_TIME = "_";

        public final class RetrieveType {
            public static final String BY_SUBJECT = "by_subject";
            public static final String BY_CHANNEL = "by_channel";
            public static final String BY_ARTICLE = "by_article";
            public static final String HOT_POST = "hot_post";
            public static final String BY_POST = "by_post";
        }
    }

    /**
     * http协议类型
     */
    public final class Protocol {
        public static final String HTTP = "http://";
        public static final String HTTPS = "https://";
    }

    /**
     * http方法
     */
    public final class HttpMethod {
        public static final int GET = Request.Method.GET;
        public static final int POST = Request.Method.POST;
        public static final int PUT = Request.Method.PUT;
        public static final int DELETE = Request.Method.DELETE;
        public static final int HEAD = Request.Method.HEAD;
    }

    /**
     * 根据给定协议返回当前api root url
     *
     * @param protocol 协议
     * @return root url
     */
    public static String getAPIProtocol(String protocol) {
//        String rootUrl = ROOT_URL;
        return protocol;
    }

    /**
     * 获取root url
     *
     * @return root url
     */
    public static String getAPIProtocol() {
        return getAPIProtocol(Protocol.HTTP);
    }
}
