package com.zzz.myapplication.model.bean;

import io.realm.RealmObject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 10:33
 */

public class ReadStateBean extends RealmObject{
    int id;

    public ReadStateBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
