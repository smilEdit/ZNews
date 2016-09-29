package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.WeixinBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 18:05
 */

public interface WeixinContract {
    interface View extends BaseView {
        void showContent(List<WeixinBean.ResultBean.ListBean> list);

        void showMoreContent(List<WeixinBean.ResultBean.ListBean> list);

        void showNewContent(List<WeixinBean.ResultBean.ListBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getWeixinData();

        void getMoreData();

        void getNewContent();

    }
}
