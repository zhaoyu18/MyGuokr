package com.example.wangzhaoyu.myguokr.network.api;

/**
 * @author wangzhaoyu
 */
public class ApiConfig {
    //根节点
    public static final String GUOKR_ROOT_URL = "http://apis.guokr.com";

    /**
     * 变量命名规则：
     */
    public final class API {
        // 科学人article list
        public static final String MINISITE_ARTICLE = "/minisite/article.json";
        public static final String ARTICLE_REPLY = "/minisite/article_reply.json";
        public static final String GROUP_POST = "/group/post.json";
        public static final String GROUP_POST_DETAIL = "/group/post/{id}.json";
        public static final String GROUP_POST_COMMENT = "/group/post_reply.json";
        public static final String NOTIFICATION_COUNT = "/community/rn_num.json";
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
}
