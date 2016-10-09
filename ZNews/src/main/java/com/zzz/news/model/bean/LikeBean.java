package com.zzz.news.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 11:11
 */

public class LikeBean extends RealmObject implements Serializable {
    public LikeBean() {

    }

    private String id;

    private String image;

    private String title;

    private int type;

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
