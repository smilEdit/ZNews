package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.RobotBean;
import com.zzz.news.model.http.JuHeHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.RobotContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 13:35
 */

public class RobotPresenter extends RxPresenter<RobotContract.View> implements RobotContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public RobotPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getRobotData(String question) {
        Subscription rxSubscription = mRetrofitHelper.fetchRobotAnswer(question)
                .compose(ZRx.<JuHeHttpResponse<RobotBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<RobotBean.ResultBean>handleJhResult())
                .subscribe(new Action1<RobotBean.ResultBean>() {
                    @Override
                    public void call(RobotBean.ResultBean resultBean) {
                        ZLog.i(resultBean.toString());
                        mView.showAnswer(resultBean.getText());
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
