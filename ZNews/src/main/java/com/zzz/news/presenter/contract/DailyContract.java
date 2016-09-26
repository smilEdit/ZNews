package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.DailyBeforeListBean;
import com.zzz.news.model.bean.DailyListBean;

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
