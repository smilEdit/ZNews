package com.zzz.news.di.component;

import com.zzz.news.app.App;
import com.zzz.news.di.ContextLife;
import com.zzz.news.di.module.AppModule;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.model.http.RetrofitHelper;

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
