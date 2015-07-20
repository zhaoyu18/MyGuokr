package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class Post {
    /**
     * topic : null
     * summary : 嗷嗷~各位guokrer好哇！欢迎来到2015围海造填仲夏版的活动现场！今夜，属于智者！\
     * \
     * \
     * 今晚，我们的活动现场分为两个阶段。第一阶段是抢楼阶段，从18：00开始。第二阶段是抢楼+答题双线同行阶段，从21：00开始。\
     * \
     * （回复满50楼，给出第一个提示词条，以此类推，共15个词条为止）\
     * \
     * —————...
     * is_digest : false
     * is_virgin : false
     * group_id : 335
     * replies_count : 4388
     * date_created : 2015-07-18T17:59:34.057670+08:00
     * ukey_author : hd3e2j
     * url : http://www.guokr.com/post/691260/
     * id : 691260
     * date_last_replied : 2015-07-20T11:19:20.189531+08:00
     * author : {"title":"有机化学博士，法学学士","ukey":"hd3e2j","is_exists":true,"nickname":"馒头老妖","is_title_authorized":true,"gender":"male","resource_url":"http://apis.guokr.com/community/user/hd3e2j.json","avatar":{"normal":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/PRt0-C0VzM_Zj99b_nsCJjHWVA5An5BdQTvaOvfFsr-gAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"},"followers_count":26191,"url":"http://www.guokr.com/i/1049918203/","amended_reliability":"0"}
     * title : 【围海造填】2015年仲夏活动~活动结束，答案公布
     * is_replyable : true
     * resource_url : http://apis.guokr.com/group/post/691260.json
     * group : {"icon":{"normal":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/71NYAqshvSPPBSlqxQ035lBKZ1tkKpoDUF4BMxV3bXagAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"},"members_count":397044,"posts_count":1391,"is_membership_applied":false,"introduction_summary":"小组简介\\\nGeek爱分享致力于打造一个精华文章分享社区。用户就是\u201c网络信息的过滤器\u201d，每个用户在阅读完一篇帖子后自会有他的评价，我们支持和鼓励用户将他们心目中的优质文章分享到这里来，让其他用户更快更高效的阅读到一篇真正优秀的文章，我们同时欢迎用户在这里发布原创文章。\\\n\\\n\\\n组务信息\\\n【用户公约】【新手...","url":"http://www.guokr.com/group/335/","is_application_required":false,"id":335,"is_indexable":true,"level":"active","is_publicly_readable":true,"is_member":false,"name":"Geek爱分享","resource_url":"/fakeurl"}
     * is_stick : false
     */
    private String topic;
    private String summary;
    private boolean is_digest;
    private boolean is_virgin;
    private int group_id;
    private int replies_count;
    private String date_created;
    private String ukey_author;
    private String url;
    private int id;
    private String date_last_replied;
    private Author author;
    private String title;
    private boolean is_replyable;
    private String resource_url;
    private Group group;
    private boolean is_stick;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setIs_digest(boolean is_digest) {
        this.is_digest = is_digest;
    }

    public void setIs_virgin(boolean is_virgin) {
        this.is_virgin = is_virgin;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setUkey_author(String ukey_author) {
        this.ukey_author = ukey_author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_last_replied(String date_last_replied) {
        this.date_last_replied = date_last_replied;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIs_replyable(boolean is_replyable) {
        this.is_replyable = is_replyable;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setIs_stick(boolean is_stick) {
        this.is_stick = is_stick;
    }

    public String getTopic() {
        return topic;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isIs_digest() {
        return is_digest;
    }

    public boolean isIs_virgin() {
        return is_virgin;
    }

    public int getGroup_id() {
        return group_id;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getUkey_author() {
        return ukey_author;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getDate_last_replied() {
        return date_last_replied;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIs_replyable() {
        return is_replyable;
    }

    public String getResource_url() {
        return resource_url;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isIs_stick() {
        return is_stick;
    }
}
