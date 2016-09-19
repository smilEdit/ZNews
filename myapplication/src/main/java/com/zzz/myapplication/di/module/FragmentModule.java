package com.zzz.myapplication.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zzz.myapplication.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 14:06
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }
}
