package com.zzz.news.model.db;


import android.content.Context;

import com.zzz.news.model.bean.ReadStateBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * @创建者 zlf
 * @创建时间 2016/9/18 14:26
 */
public class RealmHelper {
    private Realm mRealm;

    public RealmHelper(Context context) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(context)
                .name("myOtherRealm.realm")
                .build());

    }

    /**
     * 添加阅读记录
     * @param id
     */
    public void inserNewsId(int id) {
        mRealm.beginTransaction();
        ReadStateBean bean = mRealm.createObject(ReadStateBean.class);
        bean.setId(id);
        mRealm.commitTransaction();
    }

    /**
     * 查询阅读记录
     * @param id
     * @return
     */
    public boolean queryNewsId(int id) {
        RealmResults<ReadStateBean> results = mRealm.where(ReadStateBean.class).findAll();
        for (ReadStateBean item: results) {
            if (item.getId()==id) {
                return true;
            }
        }
        return false;
    }
}
