package com.zzz.news.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.zzz.news.component.CrashHandler;
import com.zzz.news.di.component.AppComponent;
import com.zzz.news.di.component.DaggerAppComponent;
import com.zzz.news.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 15:26
 */
public class App extends Application {

    private static App           instance;
    private        Set<Activity> allActivities;

    public static int   SCREEN_WIDTH  = -1;
    public static int   SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE    = -1.0F;
    public static int   DIMEN_DPI     = -1;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        //初始化错误收集
        CrashHandler.init(new CrashHandler(getApplicationContext()));

        //初始化过度绘制检测
        //        BlockCanary.install(this, new AppBlockCanaryContext()).start();

        //初始化内存泄漏检测
        LeakCanary.install(this);

    }

    public void addAcitivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static AppComponent getAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
}
