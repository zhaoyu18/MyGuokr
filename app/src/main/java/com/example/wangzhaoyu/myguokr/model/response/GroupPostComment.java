package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class GroupPostComment {
    /**
     * id : 6588691
     * content : 沙发\
     * \
     * <p/>
     * author : {"title":"","ukey":"wiqygn","is_exists":true,"nickname":"笨猫不笨","is_title_authorized":false,"gender":null,"resource_url":"http://apis.guokr.com/community/user/wiqygn.json","avatar":{"normal":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":9,"url":"http://www.guokr.com/i/1966408439/","amended_reliability":"0"}
     * level : 1
     * post : {"id":691260,"title":"【围海造填】2015年仲夏活动~活动结束，答案公布","resource_url":"http://apis.guokr.com/group/post/691260.json","url":"http://www.guokr.com/post/691260/"}
     * current_user_has_liked : false
     * date_created : 2015-07-18T18:00:05.989451+08:00
     * html : <p>沙发</p>
     * resource_url : http://apis.guokr.com/group/post_reply/6588691.json
     * likings_count : 1
     * url : http://www.guokr.com/post/reply/6588691/
     */
    private int id;
    private String content;
    private Author author;
    private int level;
    private PostEntity post;
    private boolean current_user_has_liked;
    private String date_created;
    private String html;
    private String resource_url;
    private int likings_count;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public void setCurrent_user_has_liked(boolean current_user_has_liked) {
        this.current_user_has_liked = current_user_has_liked;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public void setLikings_count(int likings_count) {
        this.likings_count = likings_count;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Author getAuthor() {
        return author;
    }

    public int getLevel() {
        return level;
    }

    public PostEntity getPost() {
        return post;
    }

    public boolean isCurrent_user_has_liked() {
        return current_user_has_liked;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getHtml() {
        return html;
    }

    public String getResource_url() {
        return resource_url;
    }

    public int getLikings_count() {
        return likings_count;
    }

    public String getUrl() {
        return url;
    }

    public static class PostEntity {
        /**
         * id : 691260
         * title : 【围海造填】2015年仲夏活动~活动结束，答案公布
         * resource_url : http://apis.guokr.com/group/post/691260.json
         * url : http://www.guokr.com/post/691260/
         */
        private int id;
        private String title;
        private String resource_url;
        private String url;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getResource_url() {
            return resource_url;
        }

        public String getUrl() {
            return url;
        }
    }
}
