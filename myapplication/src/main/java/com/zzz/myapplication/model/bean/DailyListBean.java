package com.zzz.myapplication.model.bean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 13:51
 */
public class DailyListBean {

    /**
     * date : 20160920
     * stories : [{"images":["http://pic2.zhimg.com/5f333d22c0842ec50435b226c86eb901.jpg"],"type":0,"id":8811992,"ga_prefix":"092013","title":"美剧界的奥斯卡开奖，又有一大堆好剧要补起来了"},{"images":["http://pic1.zhimg.com/9619bb453f1b388b8a716e1820e51f54.jpg"],"type":0,"id":8810213,"ga_prefix":"092012","title":"大误 · 你这么搞，死亡概率有点高啊\u2026\u2026"},{"images":["http://pic3.zhimg.com/b30b0d8053c606731c01ada4458c88f6.jpg"],"type":0,"id":8803431,"ga_prefix":"092011","title":"喝太多酒，老了记忆力会加速退化吗？"},{"images":["http://pic3.zhimg.com/335669906dfdca21413f48d85ef05cc6.jpg"],"type":0,"id":8810671,"ga_prefix":"092010","title":"国产手机怎么都开始在印度建工厂了？"},{"images":["http://pic2.zhimg.com/ce616709db87fb060d32901944bd85c1.jpg"],"type":0,"id":8809959,"ga_prefix":"092009","title":"人生魔咒「来都来了」，仔细想想其实挺对"},{"images":["http://pic4.zhimg.com/0af4f3bac8eaabe6ada215b6bdecffb3.jpg"],"type":0,"id":8808322,"ga_prefix":"092008","title":"给孩子买保险，建议考虑这三份"},{"images":["http://pic1.zhimg.com/492bacb665b08850db0d4c0a756f50d4.jpg"],"type":0,"id":8809571,"ga_prefix":"092007","title":"更新了 iOS 10，一定要试试 iMessage 的这些新功能"},{"images":["http://pic4.zhimg.com/bb8e5f8747198bce98257e5c580ce353.jpg"],"type":0,"id":8809326,"ga_prefix":"092007","title":"经常看到的观赏花，好多都是「变种花」"},{"images":["http://pic2.zhimg.com/6e33b85bdf02cc8aae3f9d874923c5b9.jpg"],"type":0,"id":8809477,"ga_prefix":"092007","title":"废弃卫星、火箭碎片，这些「垃圾」留在太空会出事吗？"},{"images":["http://pic4.zhimg.com/16c12550dd8378bad9548fcd4feaaaaf.jpg"],"type":0,"id":8810440,"ga_prefix":"092007","title":"读读日报 24 小时热门 TOP 5 · 亚马逊中国大博弈"},{"images":["http://pic4.zhimg.com/869921f1291686c78d0f4f89371e16f7.jpg"],"type":0,"id":8803882,"ga_prefix":"092006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/04065258b25ce1322b506b1bf961066e.jpg","type":0,"id":8811992,"ga_prefix":"092013","title":"美剧界的奥斯卡开奖，又有一大堆好剧要补起来了"},{"image":"http://pic4.zhimg.com/62651e072dd7365228323509fe3016bf.jpg","type":0,"id":8810671,"ga_prefix":"092010","title":"国产手机怎么都开始在印度建工厂了？"},{"image":"http://pic3.zhimg.com/07a113239ee8d2572ffafdb029a22366.jpg","type":0,"id":8809571,"ga_prefix":"092007","title":"更新了 iOS 10，一定要试试 iMessage 的这些新功能"},{"image":"http://pic3.zhimg.com/31c782edc5b51ba56404173c3e6db1ae.jpg","type":0,"id":8810440,"ga_prefix":"092007","title":"读读日报 24 小时热门 TOP 5 · 亚马逊中国大博弈"},{"image":"http://pic3.zhimg.com/c13123b89debcc605e7a348625073af2.jpg","type":0,"id":8805656,"ga_prefix":"091917","title":"知乎好问题 · 有哪些不合理的家居设计要避开？"}]
     */

    private String               date;
    /**
     * images : ["http://pic2.zhimg.com/5f333d22c0842ec50435b226c86eb901.jpg"]
     * type : 0
     * id : 8811992
     * ga_prefix : 092013
     * title : 美剧界的奥斯卡开奖，又有一大堆好剧要补起来了
     */

    private List<StoriesBean>    stories;
    /**
     * image : http://pic3.zhimg.com/04065258b25ce1322b506b1bf961066e.jpg
     * type : 0
     * id : 8811992
     * ga_prefix : 092013
     * title : 美剧界的奥斯卡开奖，又有一大堆好剧要补起来了
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int          type;
        private int          id;
        private String       ga_prefix;
        private String       title;
        private List<String> images;

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    '}';
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int    type;
        private int    id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
