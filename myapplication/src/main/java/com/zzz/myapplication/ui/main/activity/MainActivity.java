package com.zzz.myapplication.ui.main.activity;

import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseActivity;
import com.zzz.myapplication.presenter.MainPresenter;
import com.zzz.myapplication.presenter.contract.MainContract;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{



    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
