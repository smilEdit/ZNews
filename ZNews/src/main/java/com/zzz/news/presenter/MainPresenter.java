package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.MainContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 13:25
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void detachView() {

    }
}
