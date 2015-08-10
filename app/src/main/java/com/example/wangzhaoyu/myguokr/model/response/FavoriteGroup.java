package com.example.wangzhaoyu.myguokr.model.response;

import java.util.List;

/**
 * @author wangzhaoyu
 */
public class FavoriteGroup {

    /**
     * result : [{"icon":{"normal":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"},"members_count":43579,"posts_count":470,"is_membership_applied":false,"introduction_summary":"这里是2013年度最佳科幻游戏《坎巴拉太空计划》小组，欢迎在这里讨论任何游戏/航天相关的内容！\\\n\\\n相关小组：\\\n航天探索：http://www.guokr.com/group/134/","url":"http://www.guokr.com/group/528/","is_application_required":false,"id":528,"is_indexable":true,"level":"common","is_publicly_readable":true,"is_member":true,"name":"坎巴拉太空计划","resource_url":"/fakeurl"}]
     * now : 2015-08-04T16:57:08.146173+08:00
     * ok : true
     */
    private List<Group> result;
    private String now;
    private boolean ok;

    public void setResult(List<Group> result) {
        this.result = result;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Group> getResult() {
        return result;
    }

    public String getNow() {
        return now;
    }

    public boolean isOk() {
        return ok;
    }
}
