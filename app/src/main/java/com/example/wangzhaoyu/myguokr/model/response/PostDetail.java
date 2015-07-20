package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class PostDetail {

    private ResultEntity result;
    private String now;
    private boolean ok;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getNow() {
        return now;
    }

    public boolean isOk() {
        return ok;
    }

    public class ResultEntity {

//        private String topic;
        private String summary;
        private boolean is_digest;
        private boolean is_virgin;
        private int group_id;
        private int replies_count;
        private String date_created;
        private String ukey_author;
        private String url;
        private int id;
        private String content;
        private String date_last_replied;
        private Author author;
        private String title;
        private int recommends_count;
        private String html;
        private boolean is_replyable;
        private String resource_url;
        private Group group;
        private boolean is_stick;

//        public void setTopic(String topic) {
//            this.topic = topic;
//        }

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

        public void setContent(String content) {
            this.content = content;
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

        public void setRecommends_count(int recommends_count) {
            this.recommends_count = recommends_count;
        }

        public void setHtml(String html) {
            this.html = html;
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

//        public String getTopic() {
//            return topic;
//        }

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

        public String getContent() {
            return content;
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

        public int getRecommends_count() {
            return recommends_count;
        }

        public String getHtml() {
            return html;
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
}
