package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.GankItemBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:41
 */

public interface GirlContract {
    interface View extends BaseView {
        void showContent(List<GankItemBean> gankItemBean);

        void showMoreContent(List<GankItemBean> gankItemBean, int currentPage);
    }

    interface Presenter extends BasePresenter<View> {
        void getGirlData();

        void getMoreGirlData();
    }
}
