package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.LikeBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 11:08
 */

public interface LikeContract {
    interface View extends BaseView {
        void showContent(List<LikeBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getLikeData();

        void deleteLikeData(String id);

        void changeLikeTime(String id,long time,boolean isPlus);
    }
}
