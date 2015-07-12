package com.example.wangzhaoyu.myguokr.model.reply;

import java.util.List;

/**
 * @author wangzhaoyu
 */
public class User {

    /**
     * result : {"activities_count":0,"questions_count":0,"baskets_count":0,"posts_count":0,"blog_url":"","blog_title":null,"date_created":"2015-03-18T22:12:26.883303+08:00","answers_count":0,"city":null,"title":"","ukey":"unxy19","area":null,"is_title_authorized":false,"gender":null,"followers_count":0,"amended_reliability":0,"followings_count":0,"nickname":"zhaoyu87","avatar":{"normal":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"},"url":"http://www.guokr.com/i/1854200205/","badge_counts":{"total":4,"copper":4,"silver":0,"gold":0},"answer_supports_count":0,"resource_url":"http://apis.guokr.com/community/user/unxy19.json","taggings":[],"introduction":"","blogs_count":0}
     * now : 2015-07-12T19:00:50.523392+08:00
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
        /**
         * activities_count : 0
         * questions_count : 0
         * baskets_count : 0
         * posts_count : 0
         * blog_url :
         * blog_title : null
         * date_created : 2015-03-18T22:12:26.883303+08:00
         * answers_count : 0
         * city : null
         * title :
         * ukey : unxy19
         * area : null
         * is_title_authorized : false
         * gender : null
         * followers_count : 0
         * amended_reliability : 0
         * followings_count : 0
         * nickname : zhaoyu87
         * avatar : {"normal":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24","large":"http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160"}
         * url : http://www.guokr.com/i/1854200205/
         * badge_counts : {"total":4,"copper":4,"silver":0,"gold":0}
         * answer_supports_count : 0
         * resource_url : http://apis.guokr.com/community/user/unxy19.json
         * taggings : []
         * introduction :
         * blogs_count : 0
         */
        private int activities_count;
        private int questions_count;
        private int baskets_count;
        private int posts_count;
        private String blog_url;
        private String blog_title;
        private String date_created;
        private int answers_count;
        private String city;
        private String title;
        private String ukey;
        private String area;
        private boolean is_title_authorized;
        private String gender;
        private int followers_count;
        private int amended_reliability;
        private int followings_count;
        private String nickname;
        private AvatarEntity avatar;
        private String url;
        private Badge_countsEntity badge_counts;
        private int answer_supports_count;
        private String resource_url;
        private List<?> taggings;
        private String introduction;
        private int blogs_count;

        public void setActivities_count(int activities_count) {
            this.activities_count = activities_count;
        }

        public void setQuestions_count(int questions_count) {
            this.questions_count = questions_count;
        }

        public void setBaskets_count(int baskets_count) {
            this.baskets_count = baskets_count;
        }

        public void setPosts_count(int posts_count) {
            this.posts_count = posts_count;
        }

        public void setBlog_url(String blog_url) {
            this.blog_url = blog_url;
        }

        public void setBlog_title(String blog_title) {
            this.blog_title = blog_title;
        }

        public void setDate_created(String date_created) {
            this.date_created = date_created;
        }

        public void setAnswers_count(int answers_count) {
            this.answers_count = answers_count;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUkey(String ukey) {
            this.ukey = ukey;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setIs_title_authorized(boolean is_title_authorized) {
            this.is_title_authorized = is_title_authorized;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public void setAmended_reliability(int amended_reliability) {
            this.amended_reliability = amended_reliability;
        }

        public void setFollowings_count(int followings_count) {
            this.followings_count = followings_count;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setAvatar(AvatarEntity avatar) {
            this.avatar = avatar;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setBadge_counts(Badge_countsEntity badge_counts) {
            this.badge_counts = badge_counts;
        }

        public void setAnswer_supports_count(int answer_supports_count) {
            this.answer_supports_count = answer_supports_count;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public void setTaggings(List<?> taggings) {
            this.taggings = taggings;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setBlogs_count(int blogs_count) {
            this.blogs_count = blogs_count;
        }

        public int getActivities_count() {
            return activities_count;
        }

        public int getQuestions_count() {
            return questions_count;
        }

        public int getBaskets_count() {
            return baskets_count;
        }

        public int getPosts_count() {
            return posts_count;
        }

        public String getBlog_url() {
            return blog_url;
        }

        public String getBlog_title() {
            return blog_title;
        }

        public String getDate_created() {
            return date_created;
        }

        public int getAnswers_count() {
            return answers_count;
        }

        public String getCity() {
            return city;
        }

        public String getTitle() {
            return title;
        }

        public String getUkey() {
            return ukey;
        }

        public String getArea() {
            return area;
        }

        public boolean isIs_title_authorized() {
            return is_title_authorized;
        }

        public String getGender() {
            return gender;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public int getAmended_reliability() {
            return amended_reliability;
        }

        public int getFollowings_count() {
            return followings_count;
        }

        public String getNickname() {
            return nickname;
        }

        public AvatarEntity getAvatar() {
            return avatar;
        }

        public String getUrl() {
            return url;
        }

        public Badge_countsEntity getBadge_counts() {
            return badge_counts;
        }

        public int getAnswer_supports_count() {
            return answer_supports_count;
        }

        public String getResource_url() {
            return resource_url;
        }

        public List<?> getTaggings() {
            return taggings;
        }

        public String getIntroduction() {
            return introduction;
        }

        public int getBlogs_count() {
            return blogs_count;
        }

        public class AvatarEntity {
            /**
             * normal : http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48
             * small : http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24
             * large : http://1.im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160
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

        public class Badge_countsEntity {
            /**
             * total : 4
             * copper : 4
             * silver : 0
             * gold : 0
             */
            private int total;
            private int copper;
            private int silver;
            private int gold;

            public void setTotal(int total) {
                this.total = total;
            }

            public void setCopper(int copper) {
                this.copper = copper;
            }

            public void setSilver(int silver) {
                this.silver = silver;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }

            public int getTotal() {
                return total;
            }

            public int getCopper() {
                return copper;
            }

            public int getSilver() {
                return silver;
            }

            public int getGold() {
                return gold;
            }
        }
    }
}
