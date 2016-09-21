package com.zzz.myapplication.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.component.RxBus;
import com.zzz.myapplication.model.bean.DailyBeforeListBean;
import com.zzz.myapplication.model.bean.DailyListBean;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.DailyContract;
import com.zzz.myapplication.util.ZLog;
import com.zzz.myapplication.util.ZRx;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:15
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public DailyPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDay.class)
                .flatMap(new Func1<CalendarDay, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(CalendarDay calendarDay) {
                        mView.showProgress();
                        StringBuilder date = new StringBuilder(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth());
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        date.append(month).append(day);
                        ZLog.i(date.toString());
                        return mRetrofitHelper.fetchDailyBeforeListInfo(date.toString());
                    }
                }).compose(ZRx.<DailyBeforeListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyBeforeListBean>() {
                               @Override
                               public void call(DailyBeforeListBean dailyBeforeListBean) {
                                   int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
                                   int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
                                   int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
                                   mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
                                   ZLog.i(String.format("%d年%d月%d日",year,month,day));
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mView.showError();
                            }});
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getDailyData() {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyListInfo()
                .compose(ZRx.<DailyListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        mView.showContent(dailyListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ZLog.i(throwable.toString());
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getBeforeData(String date) {
        Subscription rxSubscription = mRetrofitHelper.fetchDailyBeforeListInfo(date)
                .compose(ZRx.<DailyBeforeListBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyBeforeListBean>() {
                    @Override
                    public void call(DailyBeforeListBean dailyBeforeListBean) {
                        int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0, 4));
                        int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4, 6));
                        int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6, 8));
                        mView.showMoreContent(String.format("%d年%d月%d日", year, month, day), dailyBeforeListBean);
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
