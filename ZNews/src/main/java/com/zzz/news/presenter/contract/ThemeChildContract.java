package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.ThemeChildListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 9:06
 */

public interface ThemeChildContract {

    interface View extends BaseView {
        void showContent(ThemeChildListBean themeChildListBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getThemeChildData(int id);

        void insertRead2Db(int id);
    }
}
