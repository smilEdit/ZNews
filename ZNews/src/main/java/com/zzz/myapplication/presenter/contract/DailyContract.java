package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.DailyBeforeListBean;
import com.zzz.myapplication.model.bean.DailyListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 14:58
 */
public interface DailyContract {
    interface View extends BaseView {
        void showContent(DailyListBean info);

        void showMoreContent(String date, DailyBeforeListBean info);

        void showProgress();

        void doInterval(int currentCount);
    }

    interface Presenter extends BasePresenter<View> {

        void getDailyData();

        void getBeforeData(String date);

        void startInterval();

        void stopInterval();
    }
}
