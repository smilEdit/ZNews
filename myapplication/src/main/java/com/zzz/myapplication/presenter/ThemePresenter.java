package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.ThemeContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:13
 */
public class ThemePresenter extends RxPresenter<ThemeContract.View> implements ThemeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ThemePresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

}
