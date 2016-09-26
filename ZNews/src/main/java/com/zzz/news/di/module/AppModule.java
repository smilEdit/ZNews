package com.zzz.news.di.module;

import com.zzz.news.app.App;
import com.zzz.news.di.ContextLife;
import com.zzz.news.model.db.RealmHelper;
import com.zzz.news.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 15:44
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    @ContextLife
    App provideApplicationContext() {
        return mApp;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return new RetrofitHelper();
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper(mApp);
    }

}
