package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.GankItemBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:42
 */

public interface TechContract {
    interface View extends BaseView {
        void showContent(List<GankItemBean> list);

        void showMoreContent(List<GankItemBean> list);

        void showGirlImage(String url);
    }

    interface Presenter extends BasePresenter<View> {
        void getGankData(String tech);

        void getMoreData(String tech);

        void getGirlImage();
    }
}
