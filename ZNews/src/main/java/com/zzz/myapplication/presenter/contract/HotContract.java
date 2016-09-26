package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.HotListBean;

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
