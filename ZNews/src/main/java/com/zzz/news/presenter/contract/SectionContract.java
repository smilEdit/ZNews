package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.SectionListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:06
 */
public interface SectionContract {
    interface View extends BaseView {
        void showConent(SectionListBean sectionListBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getSectionData();
    }
}
