package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.GirlContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:55
 */

public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter{
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public GirlPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }
}
