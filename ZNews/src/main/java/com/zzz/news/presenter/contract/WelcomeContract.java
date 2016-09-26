package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.WelcomeBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 15:15
 */

public interface WelcomeContract  {
    interface View extends BaseView {
        void showContent(WelcomeBean welcomeBean);

        void jump2Main();
    }

    interface Presenter extends BasePresenter<View> {
        void getWelcomeData();
    }
}
