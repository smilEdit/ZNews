package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.ThemeChildListBean;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.ThemeChildContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 9:14
 */

public class ThemeChildPresenter extends RxPresenter<ThemeChildContract.View> implements ThemeChildContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    private RealmHelper mRealmHelper;

    @Inject
    public ThemeChildPresenter(RetrofitHelper retrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = retrofitHelper;
        this.mRealmHelper = realmHelper;
    }

    @Override
    public void getThemeChildData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchThemeChildList(id)
                .compose(ZRx.<ThemeChildListBean>rxSchedulerHelper())
                .map(new Func1<ThemeChildListBean, ThemeChildListBean>() {
                    @Override
                    public ThemeChildListBean call(ThemeChildListBean themeChildListBean) {
                        List<ThemeChildListBean.StoriesBean> list = themeChildListBean.getStories();
                        for (ThemeChildListBean.StoriesBean bean : list) {
                            bean.setReadState(mRealmHelper.queryNewsId(bean.getId()));
                        }
                        return themeChildListBean;
                    }
                })
                .subscribe(new Action1<ThemeChildListBean>() {
                    @Override
                    public void call(ThemeChildListBean themeChildListBean) {
                        mView.showContent(themeChildListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                        ZLog.i(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void insertRead2Db(int id) {
        mRealmHelper.inserNewsId(id);
    }
}
