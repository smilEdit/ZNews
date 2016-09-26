package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 13:25
 */
public interface MainContract{
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
