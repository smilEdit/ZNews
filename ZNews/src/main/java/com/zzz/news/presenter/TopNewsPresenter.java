package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.TopNewsBean;
import com.zzz.news.model.http.JuheHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.TopNewsContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 11:39
 */

public class TopNewsPresenter extends RxPresenter<TopNewsContract.View> implements TopNewsContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public TopNewsPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getTopNewsData() {
        Subscription rxSubscription = mRetrofitHelper.fetchTopNewsList()
                .compose(ZRx.<JuheHttpResponse<TopNewsBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<TopNewsBean.ResultBean>handleJhResult())
                .subscribe(new Action1<TopNewsBean.ResultBean>() {
                    @Override
                    public void call(TopNewsBean.ResultBean bean) {
                        mView.showContent(bean.getData());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable.toString() != null) {
                            ZLog.i(throwable.toString());
                            mView.showError(throwable.toString());
                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
