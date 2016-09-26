package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.ThemeListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:07
 */
public interface ThemeContract {
    interface View extends BaseView {
        void showContent(ThemeListBean themeListBean);


    }

    interface Presenter extends BasePresenter<View> {
        void getThemeData();
    }
}
