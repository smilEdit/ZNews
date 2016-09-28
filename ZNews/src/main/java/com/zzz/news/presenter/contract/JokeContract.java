package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.JokeBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 15:52
 */

public interface JokeContract {
    interface View extends BaseView {
        void showContent(List<JokeBean.ResultBean.DataBean> list);

        void showMoreContent(List<JokeBean.ResultBean.DataBean> list, int page);
    }

    interface Presenter extends BasePresenter<View> {
        void getJokeData();

        void getMoreData();

        void getNewData();
    }
}
