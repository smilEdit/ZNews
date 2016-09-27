package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.ThemeListBean;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.ThemeContract;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:13
 */
public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getThemeData() {
        mRetrofitHelper.fetchThemeListBean()
                .compose(ZRx.<ThemeListBean>rxSchedulerHelper())
                .subscribe(new Action1<ThemeListBean>() {
                    @Override
                    public void call(ThemeListBean themeListBean) {
                        mView.showContent(themeListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
    }
}
