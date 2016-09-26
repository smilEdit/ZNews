package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.CommentBean;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.CommentContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 14:59
 */

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter {

    public static final int SHORT_COMMENT = 0;
    public static final int LONG_COMMENT = 1;

    public RetrofitHelper mRetrofitHelper;

    @Inject
    public CommentPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getCommentData(int id, int commentKind) {
        if (commentKind == SHORT_COMMENT) {
            Subscription rxSubscription = mRetrofitHelper.fetchShortCommentBean(id)
                    .compose(ZRx.<CommentBean>rxSchedulerHelper())
                    .subscribe(new Action1<CommentBean>() {
                        @Override
                        public void call(CommentBean commentBean) {
                            mView.showContent(commentBean);
                            ZLog.i(commentBean.toString());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("数据加载失败");
                        }
                    });
            addSubscrebe(rxSubscription);
        } else {
            Subscription rxSubscription = mRetrofitHelper.fetchLongCommentBean(id)
                    .compose(ZRx.<CommentBean>rxSchedulerHelper())
                    .subscribe(new Action1<CommentBean>() {
                        @Override
                        public void call(CommentBean commentBean) {
                            mView.showContent(commentBean);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            mView.showError("数据加载失败");
                        }
                    });
            addSubscrebe(rxSubscription);
        }
    }
}
