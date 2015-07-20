package com.example.wangzhaoyu.myguokr.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhaoyu
 */
public class GroupPosts {

    /**
     * total : 2186
     * limit : 1
     * result : [{"topic":null,"summary":"嗷嗷~各位guokrer好哇！欢迎来到2015围海造填仲夏版的活动现场！今夜，属于智者！\\\n\\\n\\\n今晚，我们的活动现场分为两个阶段。第一阶段是抢楼阶段，从18：00开始。第二阶段是抢楼+答题双线同行阶段，从21：00开始。\\\n\\\n（回复满50楼，给出第一个提示词条，以此类推，共15个词条为止）\\\n\\\n\u2014\u2014\u2014\u2014\u2014...","is_digest":false,"is_virgin":false,"group_id":335,"replies_count":4388,"date_created":"2015-07-18T17:59:34.057670+08:00","ukey_author":"hd3e2j","url":"http://www.guokr.com/post/691260/","id":691260,"date_last_replied":"2015-07-20T11:19:20.189531+08:00","author":{"title":"有机化学博士，法学学士","ukey":"hd3e2j","is_exists":true,"nickname":"馒头老妖","is_title_authorized":true,"gender":"male","resource_url":"http://apis.guokr.com/community/user/hd3e2j.json","avatar":{"normal":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":26191,"url":"http://www.guokr.com/i/1049918203/","amended_reliability":"0"},"title":"【围海造填】2015年仲夏活动~活动结束，答案公布","is_replyable":true,"resource_url":"http://apis.guokr.com/group/post/691260.json","group":{"icon":{"normal":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"},"members_count":397044,"posts_count":1391,"is_membership_applied":false,"introduction_summary":"小组简介\\\nGeek爱分享致力于打造一个精华文章分享社区。用户就是\u201c网络信息的过滤器\u201d，每个用户在阅读完一篇帖子后自会有他的评价，我们支持和鼓励用户将他们心目中的优质文章分享到这里来，让其他用户更快更高效的阅读到一篇真正优秀的文章，我们同时欢迎用户在这里发布原创文章。\\\n\\\n\\\n组务信息\\\n【用户公约】【新手...","url":"http://www.guokr.com/group/335/","is_application_required":false,"id":335,"is_indexable":true,"level":"active","is_publicly_readable":true,"is_member":false,"name":"Geek爱分享","resource_url":"/fakeurl"},"is_stick":false}]
     * now : 2015-07-20T12:30:29.316615+08:00
     * ok : true
     * offset : 0
     */
    private int total;
    private int limit;
    private ArrayList<Post> result;
    private String now;
    private boolean ok;
    private int offset;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setResult(ArrayList<Post> result) {
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

    public ArrayList<Post> getResult() {
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
