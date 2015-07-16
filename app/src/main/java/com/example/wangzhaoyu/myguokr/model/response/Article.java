package com.example.wangzhaoyu.myguokr.model.response;

/**
 * @author wangzhaoyu
 */
public class Article {

    /**
     * result : {}
     * now : 2015-07-08T16:02:29.451020+08:00
     * ok : true
     */
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
        private String image;
        private Author author;
        private String content;
        private String date_published;
        private int replies_count;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate_published() {
            return date_published;
        }

        public void setDate_published(String date_published) {
            this.date_published = date_published;
        }

        public int getReplies_count() {
            return replies_count;
        }

        public void setReplies_count(int replies_count) {
            this.replies_count = replies_count;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
