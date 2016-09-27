package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.SectionChildListBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 17:12
 */

public interface SectionChildContract {
    interface View extends BaseView {
        void showContent(SectionChildListBean sectionChildListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getSectionChildData(int id);

        void intsert2db(int id);
    }
}
