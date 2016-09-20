package com.zzz.myapplication.presenter;

import com.zzz.myapplication.base.RxPresenter;
import com.zzz.myapplication.model.http.RetrofitHelper;
import com.zzz.myapplication.presenter.contract.SectionContract;

import javax.inject.Inject;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:11
 */
public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

}
