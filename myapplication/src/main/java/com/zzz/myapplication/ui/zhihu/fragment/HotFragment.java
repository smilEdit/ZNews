package com.zzz.myapplication.ui.zhihu.fragment;

import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.presenter.HotPresenter;
import com.zzz.myapplication.presenter.contract.HotContract;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:43
 */
public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View {
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }
}
