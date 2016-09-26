package com.zzz.news.presenter.contract;

import com.zzz.news.base.BasePresenter;
import com.zzz.news.base.BaseView;
import com.zzz.news.model.bean.CommentBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 14:21
 */

public interface CommentContract {
    interface View extends BaseView {
        void showContent(CommentBean commentBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getCommentData(int id, int commentKind);
    }
}
