package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.TopNewsBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 11:35
 */

public interface TopNewsContract {
    interface View extends BaseView {
        void showContent(List<TopNewsBean.ResultBean.DataBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getTopNewsData();
    }
}
