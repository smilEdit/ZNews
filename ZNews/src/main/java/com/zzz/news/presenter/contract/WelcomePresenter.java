package com.zzz.news.presenter.contract;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.GankItemBean;
import com.zzz.news.model.http.HttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 15:18
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    public static final String RES = "1080*1600";

    public static final int COUNT_DOWN_TIME = 2048;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WelcomePresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getWelcomeData() {
        Subscription rxSubscription = mRetrofitHelper.fetchFuliImage(1)
                .compose(ZRx.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> list) {
                        mView.showContent(list.get(0).getUrl());
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ZLog.w(throwable.toString());
                        mView.showError("");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(ZRx.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jump2Main();
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
