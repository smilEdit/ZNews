package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.HotListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:05
 */
public interface HotContract {
    interface View extends BaseView {

        void showContent(HotListBean hotListBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getHotData();

        void insert2db(int id);
    }
}
