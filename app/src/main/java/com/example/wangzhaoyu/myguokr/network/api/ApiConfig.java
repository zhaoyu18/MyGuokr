package com.example.wangzhaoyu.myguokr.network.api;

/**
 * @author wangzhaoyu
 */
public class ApiConfig {
    //根节点
    public static final String GUOKR_APIS_URL = "http://apis.guokr.com";
    public static final String GUOKR_ROOT_URL = "http://www.guokr.com";

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
        public static final String GROUP_FAVORITE = "/group/favorite.json";

        public static final String NOTIFICATION_COUNT = "/community/rn_num.json";
        public static final String COMMUNITY_USER = "/community/user/{ukey}.json";
    }

    /**
     *
     */
    public final class HtmlAPI {
        public static final String ARTICLE_CONTENT = "/article/{id}/";
    }

    /**
     * 参数
     */
    public final class Query {
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
        public static final String GROUP_ID = "group_id";
        public static final String UKEY = "ukey";

        public final class RetrieveType {
            public static final String BY_SUBJECT = "by_subject";
            public static final String BY_CHANNEL = "by_channel";
            public static final String BY_ARTICLE = "by_article";
            public static final String HOT_POST = "hot_post";
            public static final String BY_GROUP = "by_group";
            public static final String BY_POST = "by_post";
            public static final String BY_USER = "by_user";
            public static final String RECENT_REPLIES = "recent_replies";
        }
    }

    /**
     *
     */
    public final class Path {
        public static final String ID = "id";
        public static final String UKEY = "ukey";
    }

    public final class Header {
        private static final int FOUR_WEEK_SECONDS = 60 * 60 * 24 * 28;
        private static final int ONE_HOUR_SECONDS = 60 * 60;
        public static final String CACHE_CONTROL = "Cache-Control";

        public final class CacheControl {
            public static final String FOUR_WEEK_STALE = "public, only-if-cached, max-stale=" +
                    FOUR_WEEK_SECONDS;
            public static final String ONE_HOUR_STALE = "public, only-if-cached, max-stale=" +
                    ONE_HOUR_SECONDS;
        }
    }
}
