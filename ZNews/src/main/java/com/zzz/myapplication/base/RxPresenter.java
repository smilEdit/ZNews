package com.zzz.myapplication.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 13:28
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T                     mView;
    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
