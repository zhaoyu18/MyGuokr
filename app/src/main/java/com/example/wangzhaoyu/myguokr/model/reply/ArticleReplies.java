package com.example.wangzhaoyu.myguokr.model.reply;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhaoyu
 */
public class ArticleReplies {

    /**
     * total : 58
     * limit : 1
     * result : [{"id":2939947,"content":"好\\\n\\\n","author":{"title":"","ukey":"cjgle2","is_exists":true,"nickname":"机动要塞","is_title_authorized":false,"gender":null,"resource_url":"http://apis.guokr.com/community/user/cjgle2.json","avatar":{"normal":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/1iD5DjegHZwR4jlDYA4BSO7rvteq8rB4KWK_-fZSr3SgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":2,"url":"http://www.guokr.com/i/0758281034/","amended_reliability":"0"},"current_user_has_liked":false,"date_created":"2015-07-08T10:46:40.683878+08:00","html":"<p>好<\/p>","liking_count":0,"resource_url":"http://apis.guokr.com/minisite/article_reply/2939947.json","likings_count":0,"url":"http://www.guokr.com/article/reply/2939947/"}]
     * now : 2015-07-13T20:05:57.593151+08:00
     * ok : true
     * offset : 0
     */
    private int total;
    private int limit;
    private ArrayList<ArticleReply> result;
    private String now;
    private boolean ok;
    private int offset;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setResult(ArrayList<ArticleReply> result) {
        this.result = result;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public int getLimit() {
        return limit;
    }

    public ArrayList<ArticleReply> getResult() {
        return result;
    }

    public String getNow() {
        return now;
    }

    public boolean isOk() {
        return ok;
    }

    public int getOffset() {
        return offset;
    }


}
