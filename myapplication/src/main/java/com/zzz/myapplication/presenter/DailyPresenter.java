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

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:15
 */
public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private Subscription intervalSubscription;

    private static final int INTERVAL_INSTANCE = 6;

    private int topCount = 0;
    private int currentTopCount = 0;

    @Inject
    public DailyPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription rxSubscription = RxBus.getDefault().toObservable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CalendarDay, String>() {
                    @Override
                    public String call(CalendarDay calendarDay) {
                        mView.showProgress();
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth()+1);
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        ZLog.i("map"+year+month+day);
                        return date.append(year).append(month).append(day).toString();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(String date) {
                        ZLog.i("flatMap"+date.toString());
                        return mRetrofitHelper.fetchDailyBeforeListInfo(date);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DailyBeforeListBean>() {
                               @Override
                               public void call(DailyBeforeListBean dailyBeforeListBean) {
                                   ZLog.i(dailyBeforeListBean.toString());
                                   int year = Integer.valueOf(dailyBeforeListBean.getDate().substring(0,4));
                                   int month = Integer.valueOf(dailyBeforeListBean.getDate().substring(4,6));
                                   int day = Integer.valueOf(dailyBeforeListBean.getDate().substring(6,8));
                                   mView.showMoreContent(String.format("%d年%d月%d日",year,month,day),dailyBeforeListBean);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                registerEvent();
                                ZLog.i(throwable.toString());
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
                        topCount = dailyListBean.getTop_stories().size();
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
    @Override
    public void startInterval() {
        intervalSubscription = Observable.interval(INTERVAL_INSTANCE, TimeUnit.SECONDS)
                .compose(ZRx.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(currentTopCount == topCount)
                            currentTopCount = 0;
                        mView.doInterval(currentTopCount++);
                    }
                });
        addSubscrebe(intervalSubscription);
    }

    @Override
    public void stopInterval() {
        intervalSubscription.unsubscribe();
    }
}
