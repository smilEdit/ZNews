package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.DemoBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 14:22
 */
public interface  DemoContract {
    interface View extends BaseView {
        void showContent(DemoBean bean);

//        void showMoreConent(String date.);

        void showProgerss();

        void doInterval(int currentCount);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
