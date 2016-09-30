package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.LishiBean;
import com.zzz.news.model.http.JuheHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.LishiContract;
import com.zzz.news.util.ZRx;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/30 9:39
 */

public class LishiPresenter extends RxPresenter<LishiContract.View> implements LishiContract.Presenter{

    RetrofitHelper mRetrofitHelper;

    @Inject
    public LishiPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getLishiData(int month, int day) {
        Subscription rxSubscription = mRetrofitHelper.fetchLishiInfo(month,day)
                .compose(ZRx.<JuheHttpResponse<List<LishiBean.ResultBean>>>rxSchedulerHelper())
                .compose(ZRx.<List<LishiBean.ResultBean>>handleJhResult())
                .subscribe(new Action1<List<LishiBean.ResultBean>>() {
                    @Override
                    public void call(List<LishiBean.ResultBean> resultBean) {
                        mView.showContent(resultBean);
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
