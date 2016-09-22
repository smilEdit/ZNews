package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.DetailExtraBean;
import com.zzz.myapplication.model.bean.ZhihuDetailBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 9:41
 */

public interface ZhihuDetailContract {
    interface View extends BaseView {
        void showContent(ZhihuDetailBean zhihuDetailBean);

        void showExtraInfo(DetailExtraBean detailExtraBean);

        void showError();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetailData(int id);

        void getExtraData(int id);
    }
}
