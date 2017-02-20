package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.JokeBean;
import com.zzz.news.model.http.JuHeHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.JokeContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 15:53
 */

public class JokePresenter extends RxPresenter<JokeContract.View> implements JokeContract.Presenter{

    private int mPage = 1;
    private int mPageSize = 10;
    private String mSort = "asc";
    private String mTime = "1418745237";

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public JokePresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getJokeData() {
        Subscription rxSubscription = mRetrofitHelper.fetchJokeBean(++mPage,mPageSize,mSort,mTime)
                .compose(ZRx.<JuHeHttpResponse<JokeBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<JokeBean.ResultBean>handleJhResult())
                .subscribe(new Action1<JokeBean.ResultBean>() {
                    @Override
                    public void call(JokeBean.ResultBean list) {
                        mView.showContent(list.getData());
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
    public void getMoreData() {
        mPageSize = mPageSize + 10;
        Subscription rxSubscription = mRetrofitHelper.fetchJokeBean(mPage,mPageSize,mSort,mTime)
                .compose(ZRx.<JuHeHttpResponse<JokeBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<JokeBean.ResultBean>handleJhResult())
                .subscribe(new Action1<JokeBean.ResultBean>() {
                    @Override
                    public void call(JokeBean.ResultBean list) {
                        mView.showContent(list.getData());
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
    public void getNewData() {
//        Subscription rxSubscription = mRetrofitHelper.fetchJokeBean(mPage,)
    }
}
