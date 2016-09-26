package com.zzz.news.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;
import com.zzz.news.app.App;
import com.zzz.news.di.component.DaggerFragmentComponent;
import com.zzz.news.di.component.FragmentComponent;
import com.zzz.news.di.module.FragmentModule;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 11:52
 */

//无mvp框架的简单BaseFragment
public abstract class SimpleFramgent extends SupportFragment{
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;

    public static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);
            }
            fragmentTransaction.commit();
        }
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.getBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(),null);
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    protected abstract void initEventAndData();

    protected abstract void initInject();

    protected abstract int getLayoutId();
}
