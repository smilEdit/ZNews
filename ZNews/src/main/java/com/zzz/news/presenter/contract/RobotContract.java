package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 13:32
 */

public interface RobotContract {
    interface View extends BaseView {
        void showAnswer(String answer);
    }

    interface Presenter extends BasePresenter<View> {
        void getRobotData(String question);
    }
}
