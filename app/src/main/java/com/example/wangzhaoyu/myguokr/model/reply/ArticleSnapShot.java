package com.example.wangzhaoyu.myguokr.model.reply;

import java.io.Serializable;

/**
 * @author wangzhaoyu
 */
public class ArticleSnapShot implements Serializable {
    private String title;
    private String small_image;
    private String summary;
    private String resource_url;
    private String date_created;
    private String url;
    private ImageInfo image_info;
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ImageInfo getImage_info() {
        return image_info;
    }

    public void setImage_info(ImageInfo image_info) {
        this.image_info = image_info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public class ImageInfo implements Serializable {

        /**
         * height : 240
         * width : 330
         * url : http://2.im.guokr.com/aeJJQ7X2zvAkkQ0j_MUE1ThdTjv96z4QE5uESvFRkFVKAQAA8AAAAEpQ.jpg
         */
        private int height;
        private int width;
        private String url;

        public void setHeight(int height) {
            this.height = height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public String getUrl() {
            return url;
        }
    }
}
