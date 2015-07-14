package com.example.wangzhaoyu.myguokr.model.reply;

/**
 * @author wangzhaoyu
 */
public class ArticleReply {
    /**
     * id : 2939947
     * content : 好\
     * \
     * <p/>
     * author : {"title":"","ukey":"cjgle2","is_exists":true,"nickname":"机动要塞","is_title_authorized":false,"gender":null,"resource_url":"http://apis.guokr.com/community/user/cjgle2.json","avatar":{"normal":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":2,"url":"http://www.guokr.com/i/0758281034/","amended_reliability":"0"}
     * current_user_has_liked : false
     * date_created : 2015-07-08T10:46:40.683878+08:00
     * html : <p>好</p>
     * liking_count : 0
     * resource_url : http://apis.guokr.com/minisite/article_reply/2939947.json
     * likings_count : 0
     * url : http://www.guokr.com/article/reply/2939947/
     */
    private int id;
    private String content;
    private Author author;
    private boolean current_user_has_liked;
    private String date_created;
    private String html;
    private int liking_count;
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

    public void setCurrent_user_has_liked(boolean current_user_has_liked) {
        this.current_user_has_liked = current_user_has_liked;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setLiking_count(int liking_count) {
        this.liking_count = liking_count;
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

    public boolean isCurrent_user_has_liked() {
        return current_user_has_liked;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getHtml() {
        return html;
    }

    public int getLiking_count() {
        return liking_count;
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
}

