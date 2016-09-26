package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.DetailExtraBean;
import com.zzz.news.model.bean.ZhihuDetailBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 9:41
 */

public interface ZhihuDetailContract {
    interface View extends BaseView {
        void showContent(ZhihuDetailBean zhihuDetailBean);

        void showExtraInfo(DetailExtraBean detailExtraBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getDetailData(int id);

        void getExtraData(int id);
    }
}
