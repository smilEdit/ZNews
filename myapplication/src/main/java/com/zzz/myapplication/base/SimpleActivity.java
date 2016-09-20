package com.zzz.myapplication.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzz.myapplication.app.App;
import com.zzz.myapplication.di.component.ActivityComponent;
import com.zzz.myapplication.di.component.DaggerActivityComponent;
import com.zzz.myapplication.di.module.ActivityModule;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 13:20
 */

//无mvp框架的简单BaseActivity
public abstract class SimpleActivity extends SupportActivity {
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addAcitivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    //    @Override
    //    protected void onResume() {
    //        super.onResume();
    //        MobclickAgent.onResume(this);
    //    }

    //    @Override
    //    protected void onPause() {
    //        super.onPause();
    //        MobclickAgent.onPause(this);
    //    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }

    protected abstract void initEventAndData();

    protected abstract int getLayout();
}
