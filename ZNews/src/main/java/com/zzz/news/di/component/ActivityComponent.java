package com.zzz.news.di.component;

import android.app.Activity;

import com.zzz.news.di.ActivityScope;
import com.zzz.news.di.module.ActivityModule;
import com.zzz.news.ui.main.activity.MainActivity;
import com.zzz.news.ui.zhihu.activity.WelcomeActivity;
import com.zzz.news.ui.zhihu.activity.ZhihuDetailActivity;

import dagger.Component;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 13:55
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

//    RetrofitHelper getRetrofitHelper();

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(ZhihuDetailActivity zhihuDetailActivity);

    void inject(WelcomeActivity welcomeActivity);
}

