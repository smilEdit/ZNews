package com.zzz.myapplication.di.module;

import android.app.Activity;

import com.zzz.myapplication.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 14:08
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
