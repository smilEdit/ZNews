package com.zzz.myapplication.ui.zhihu.fragment;

import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.presenter.ThemePresenter;
import com.zzz.myapplication.presenter.contract.ThemeContract;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:40
 */
public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View{
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }
}
