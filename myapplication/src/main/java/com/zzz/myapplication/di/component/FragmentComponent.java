package com.zzz.myapplication.di.component;

import android.app.Activity;

import com.zzz.myapplication.di.FragmentScope;
import com.zzz.myapplication.di.module.FragmentModule;
import com.zzz.myapplication.model.http.RetrofitHelper;

import dagger.Component;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 14:11
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    RetrofitHelper getRetrofitHelper();

    Activity getActivity();

}
