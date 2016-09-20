package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.HotContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:09
 */
public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HotPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }
}
