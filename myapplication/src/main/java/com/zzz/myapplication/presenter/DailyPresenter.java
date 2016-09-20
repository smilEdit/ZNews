package com.zzz.myapplication.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.component.RxBus;
import com.zzz.myapplication.model.bean.DailyBeforeListBean;
import com.zzz.myapplication.model.bean.DailyListBean;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.DailyContract;
import com.zzz.myapplication.util.ZDate;
import com.zzz.myapplication.util.ZLog;
import com.zzz.myapplication.util.ZRx;

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
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay() + 1);
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                }).filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if(s.equals(ZDate.getTomorrowDate())) {
                            getDailyData();
                            return false;
                        }
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<DailyBeforeListBean>>() {
                    @Override
                    public Observable<DailyBeforeListBean> call(String date) {
                        return mRetrofitHelper.fetchDailyBeforeListInfo(date);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())    //为了使用Realm和显示结果切到主线程
                .subscribe(new Action1<DailyBeforeListBean>() {
                               @Override
                               public void call(DailyBeforeListBean dailyBeforeListBean) {
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
                        ZLog.d(throwable.toString());
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
