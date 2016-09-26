package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.ThemeListBean;

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
