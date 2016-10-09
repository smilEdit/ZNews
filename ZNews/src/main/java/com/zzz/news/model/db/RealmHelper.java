package com.zzz.news.model.db;


import android.content.Context;

import com.zzz.news.model.bean.LikeBean;
import com.zzz.news.model.bean.ReadStateBean;

import java.util.List;

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

    /**
     * 添加收藏记录
     * @param bean
     */
    public void inserLikeBean(LikeBean bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(bean);
        mRealm.commitTransaction();
    }

    /**
     * 查询收藏记录
     * @param id
     * @return
     */
    public boolean queryLikeId(String id) {
        RealmResults<LikeBean> results = mRealm.where(LikeBean.class).findAll();
        for (LikeBean result : results) {
            if (result.getId()==id) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除收藏记录
     * @param id
     */
    public void deleteLikeBean(String id) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        bean.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * 修改 收藏记录 时间戳以重新排序
     * @param id
     * @param time
     * @param isPlus
     */
    public void changeLikeTime(String id ,long time, boolean isPlus) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        if (isPlus) {
            bean.setTime(++time);
        } else {
            bean.setTime(--time);
        }
        mRealm.commitTransaction();
    }

    public List<LikeBean> getLikeList() {
        //使用findAllSort ,先findAll再result.sort无效
        RealmResults<LikeBean> results = mRealm.where(LikeBean.class).findAllSorted("time");
        return mRealm.copyFromRealm(results);
    }
}
