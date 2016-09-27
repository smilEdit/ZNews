package com.zzz.news.ui.main.fragment;

import com.zzz.news.R;
import com.zzz.news.base.SimpleFramgent;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 17:47
 */

public class MeFragment extends SimpleFramgent {
    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }
}
