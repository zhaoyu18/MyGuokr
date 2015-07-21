package com.example.wangzhaoyu.myguokr.model.response;

import java.util.ArrayList;

/**
 * @author wangzhaoyu
 */
public class GroupPostComments {

    /**
     * total : 4388
     * limit : 1
     * result : [{"id":6588691,"content":"沙发\\\n\\\n","author":{"title":"","ukey":"wiqygn","is_exists":true,"nickname":"笨猫不笨","is_title_authorized":false,"gender":null,"resource_url":"http://apis.guokr.com/community/user/wiqygn.json","avatar":{"normal":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://1.im.guokr.com/xRo0OmqQ-PjodM75XXK_Th_J73_PAs-qJiQkbmlAHPmgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":9,"url":"http://www.guokr.com/i/1966408439/","amended_reliability":"0"},"level":1,"post":{"id":691260,"title":"【围海造填】2015年仲夏活动~活动结束，答案公布","resource_url":"http://apis.guokr.com/group/post/691260.json","url":"http://www.guokr.com/post/691260/"},"current_user_has_liked":false,"date_created":"2015-07-18T18:00:05.989451+08:00","html":"<p>沙发<\/p>","resource_url":"http://apis.guokr.com/group/post_reply/6588691.json","likings_count":1,"url":"http://www.guokr.com/post/reply/6588691/"}]
     * now : 2015-07-21T09:17:35.589301+08:00
     * ok : true
     * offset : 0
     */
    private int total;
    private int limit;
    private ArrayList<GroupPostComment> result;
    private String now;
    private boolean ok;
    private int offset;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setResult(ArrayList<GroupPostComment> result) {
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

    public ArrayList<GroupPostComment> getResult() {
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
