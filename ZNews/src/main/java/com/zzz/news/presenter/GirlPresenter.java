package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.GankItemBean;
import com.zzz.news.model.http.HttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.GirlContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:55
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    public static final int NUM_OF_PAGE = 20;

    private int mCurrentPage = 1;

    @Inject
    public GirlPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void getGirlData() {
        mCurrentPage = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchGirlList(NUM_OF_PAGE, mCurrentPage)
                .compose(ZRx.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> list) {
                        mView.showContent(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据获取失败");
                        ZLog.e(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreGirlData() {
        Subscription rxSubscription = mRetrofitHelper.fetchGirlList(NUM_OF_PAGE, ++mCurrentPage)
                .compose(ZRx.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> list) {
                        mView.showMoreContent(list, mCurrentPage);
                        ZLog.i(list.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载更多数据失败");
                        ZLog.e(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
