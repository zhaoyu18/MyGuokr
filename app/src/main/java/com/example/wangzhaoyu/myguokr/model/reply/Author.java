package com.example.wangzhaoyu.myguokr.model.reply;

/**
 * @author wangzhaoyu
 */
public class Author {

    /**
     * title : 神经科学博士生
     * ukey : 9tjv75
     * is_exists : true
     * nickname : Alulull
     * is_title_authorized : false
     * gender : null
     * resource_url : http://apis.guokr.com/community/user/9tjv75.json
     * avatar : {"normal":"http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48","small":"http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24","large":"http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160"}
     * followers_count : 47
     * url : http://www.guokr.com/i/0593831345/
     * amended_reliability : 0
     */
    private String title;
    private String ukey;
    private boolean is_exists;
    private String nickname;
    private boolean is_title_authorized;
    private String gender;
    private String resource_url;
    private AvatarEntity avatar;
    private int followers_count;
    private String url;
    private String amended_reliability;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public void setIs_exists(boolean is_exists) {
        this.is_exists = is_exists;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIs_title_authorized(boolean is_title_authorized) {
        this.is_title_authorized = is_title_authorized;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public void setAvatar(AvatarEntity avatar) {
        this.avatar = avatar;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAmended_reliability(String amended_reliability) {
        this.amended_reliability = amended_reliability;
    }

    public String getTitle() {
        return title;
    }

    public String getUkey() {
        return ukey;
    }

    public boolean isIs_exists() {
        return is_exists;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isIs_title_authorized() {
        return is_title_authorized;
    }

    public String getGender() {
        return gender;
    }

    public String getResource_url() {
        return resource_url;
    }

    public AvatarEntity getAvatar() {
        return avatar;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public String getUrl() {
        return url;
    }

    public String getAmended_reliability() {
        return amended_reliability;
    }

    public class AvatarEntity {
        /**
         * normal : http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/48/h/48
         * small : http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/24/h/24
         * large : http://2.im.guokr.com/IjrCqnTg_TLn8ShsrYDuSsLmPXxcjGEqX6YwBV1cZTmgAAAAoAAAAEpQ.jpg?imageView2/1/w/160/h/160
         */
        private String normal;
        private String small;
        private String large;

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getNormal() {
            return normal;
        }

        public String getSmall() {
            return small;
        }

        public String getLarge() {
            return large;
        }
    }
}
