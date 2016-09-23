package com.zzz.myapplication.di.component;

import com.zzz.myapplication.app.App;
import com.zzz.myapplication.di.ContextLife;
import com.zzz.myapplication.di.module.AppModule;
import com.zzz.myapplication.model.db.RealmHelper;
import com.zzz.myapplication.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 15:41
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    App getContext();

    RetrofitHelper retrofitHelper();

    RealmHelper realmHelper();
}
