package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.bean.GankItemBean;
import com.zzz.myapplication.model.http.HttpResponse;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.TechContract;
import com.zzz.myapplication.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:55
 */

public class TechPresenter extends RxPresenter<TechContract.View>implements TechContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    public static final String TECH_ANDROID = "Android";
    public static final String TECH_IOS = "iOS";
    public static final String TECH_WEB = "前端";
    private static final int NUM_OF_PAGE = 10;

    private int mCurrentPage = 1;

    @Inject
    public TechPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getGankData(String tech) {
        mCurrentPage = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech, NUM_OF_PAGE, mCurrentPage)
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
                        mView.showError("数据加载失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreData(String tech) {
        Subscription rxSubscription = mRetrofitHelper.fetchTechList(tech,NUM_OF_PAGE,mCurrentPage++)
                .compose(ZRx.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> list) {
                        mView.showMoreContent(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载更多数据失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getGirlImage() {
        Subscription rxSubscription = mRetrofitHelper.fetchFuliImage(1)
                .compose(ZRx.<HttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<GankItemBean>>handleResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> list) {
                        mView.showGirlImage(list.get(0).getUrl());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载封面失败");
                    }
                });
        addSubscrebe(rxSubscription);
    }


}
