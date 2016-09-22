package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.bean.ThemeListBean;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.ThemeContract;
import com.zzz.myapplication.util.ZLog;
import com.zzz.myapplication.util.ZRx;

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
        ZLog.i("getThemeData");
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
                        mView.showError("");
                        ZLog.i(throwable.toString());
                    }
                });
    }
}
