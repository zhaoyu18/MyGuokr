package com.example.wangzhaoyu.myguokr.model.reply;

/**
 * @author wangzhaoyu
 */
public class ArticleSnapShot {
    private String title;
    private String small_image;
    private String summary;
    private String resource_url;
    private String date_created;
    private String url;

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

    @Override
    public String toString() {
        return "ArticleSnapShot{" +
                "title='" + title + '\'' +
                ", small_image='" + small_image + '\'' +
                ", summary='" + summary + '\'' +
                ", resource_url='" + resource_url + '\'' +
                ", date_created='" + date_created + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                '}';
    }
}
