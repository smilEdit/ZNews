package com.zzz.myapplication.presenter.contract;

import com.zzz.myapplication.base.BasePresenter;
import com.zzz.myapplication.base.BaseView;
import com.zzz.myapplication.model.bean.CommentBean;

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
