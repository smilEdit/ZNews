package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.WeixinBean;
import com.zzz.news.model.http.JuheHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.WeixinContract;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 18:08
 */

public class WeixinPresenter extends RxPresenter<WeixinContract.View> implements WeixinContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WeixinPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getWeixinData() {
        Subscription rxSubscription = mRetrofitHelper.fetchWeixinList()
                .compose(ZRx.<JuheHttpResponse<WeixinBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<WeixinBean.ResultBean>handleJhResult())
                .subscribe(new Action1<WeixinBean.ResultBean>() {
                    @Override
                    public void call(WeixinBean.ResultBean resultBean) {
                        mView.showContent(resultBean.getList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
