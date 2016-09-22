package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.bean.DetailExtraBean;
import com.zzz.myapplication.model.bean.ZhihuDetailBean;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.ZhihuDetailContract;
import com.zzz.myapplication.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 9:43
 */

public class ZhihuDetailPresenter extends RxPresenter<ZhihuDetailContract.View> implements ZhihuDetailContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ZhihuDetailPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getDetailData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchDetailInfo(id)
                .compose(ZRx.<ZhihuDetailBean>rxSchedulerHelper())
                .subscribe(new Action1<ZhihuDetailBean>() {
                    @Override
                    public void call(ZhihuDetailBean zhihuDetailBean) {
                        mView.showContent(zhihuDetailBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getExtraData(int id) {
        Subscription rxSubscription = mRetrofitHelper.fetchDetailExtraBean(id)
                .compose(ZRx.<DetailExtraBean>rxSchedulerHelper())
                .subscribe(new Action1<DetailExtraBean>() {
                    @Override
                    public void call(DetailExtraBean detailExtraBean) {
                        mView.showExtraInfo(detailExtraBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
