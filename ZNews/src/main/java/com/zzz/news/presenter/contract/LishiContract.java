package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.LishiBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/30 9:37
 */

public interface LishiContract {
    interface View extends BaseView {
        void showContent(List<LishiBean.ResultBean> lishiInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getLishiData(int month,int day);
    }
}
