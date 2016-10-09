package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.presenter.contract.LikeContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 11:16
 */

public class LikePresenter extends RxPresenter<LikeContract.View> implements LikeContract.Presenter {

    private RealmHelper mRealmHelper;

    @Inject
    public LikePresenter(RealmHelper realmHelper) {
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void getLikeData() {
        mView.showContent(mRealmHelper.getLikeList());
    }

    @Override
    public void deleteLikeData(String id) {
        mRealmHelper.deleteLikeBean(id);
    }

    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        mRealmHelper.changeLikeTime(id,time,isPlus);
    }
}
