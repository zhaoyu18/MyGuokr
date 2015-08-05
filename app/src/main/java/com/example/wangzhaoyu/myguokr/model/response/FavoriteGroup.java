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
    private List<ResultEntity> result;
    private String now;
    private boolean ok;

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public String getNow() {
        return now;
    }

    public boolean isOk() {
        return ok;
    }

    public static class ResultEntity {
        /**
         * icon : {"normal":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"}
         * members_count : 43579
         * posts_count : 470
         * is_membership_applied : false
         * introduction_summary : 这里是2013年度最佳科幻游戏《坎巴拉太空计划》小组，欢迎在这里讨论任何游戏/航天相关的内容！\
         \
         相关小组：\
         航天探索：http://www.guokr.com/group/134/
         * url : http://www.guokr.com/group/528/
         * is_application_required : false
         * id : 528
         * is_indexable : true
         * level : common
         * is_publicly_readable : true
         * is_member : true
         * name : 坎巴拉太空计划
         * resource_url : /fakeurl
         */
        private IconEntity icon;
        private int members_count;
        private int posts_count;
        private boolean is_membership_applied;
        private String introduction_summary;
        private String url;
        private boolean is_application_required;
        private int id;
        private boolean is_indexable;
        private String level;
        private boolean is_publicly_readable;
        private boolean is_member;
        private String name;
        private String resource_url;

        public void setIcon(IconEntity icon) {
            this.icon = icon;
        }

        public void setMembers_count(int members_count) {
            this.members_count = members_count;
        }

        public void setPosts_count(int posts_count) {
            this.posts_count = posts_count;
        }

        public void setIs_membership_applied(boolean is_membership_applied) {
            this.is_membership_applied = is_membership_applied;
        }

        public void setIntroduction_summary(String introduction_summary) {
            this.introduction_summary = introduction_summary;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setIs_application_required(boolean is_application_required) {
            this.is_application_required = is_application_required;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIs_indexable(boolean is_indexable) {
            this.is_indexable = is_indexable;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setIs_publicly_readable(boolean is_publicly_readable) {
            this.is_publicly_readable = is_publicly_readable;
        }

        public void setIs_member(boolean is_member) {
            this.is_member = is_member;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public IconEntity getIcon() {
            return icon;
        }

        public int getMembers_count() {
            return members_count;
        }

        public int getPosts_count() {
            return posts_count;
        }

        public boolean isIs_membership_applied() {
            return is_membership_applied;
        }

        public String getIntroduction_summary() {
            return introduction_summary;
        }

        public String getUrl() {
            return url;
        }

        public boolean isIs_application_required() {
            return is_application_required;
        }

        public int getId() {
            return id;
        }

        public boolean isIs_indexable() {
            return is_indexable;
        }

        public String getLevel() {
            return level;
        }

        public boolean isIs_publicly_readable() {
            return is_publicly_readable;
        }

        public boolean isIs_member() {
            return is_member;
        }

        public String getName() {
            return name;
        }

        public String getResource_url() {
            return resource_url;
        }

        public static class IconEntity {
            /**
             * normal : http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48
             * small : http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24
             * large : http://3.im.guokr.com/SyjgoQgUwhiMS93HNpyrz9k01cJ1wkYYTAT1wUiOCYSgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160
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
}
