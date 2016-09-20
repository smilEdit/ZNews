package com.zzz.myapplication.ui.main.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseActivity;
import com.zzz.myapplication.presenter.MainPresenter;
import com.zzz.myapplication.presenter.contract.MainContract;
import com.zzz.myapplication.ui.zhihu.fragment.ZhihuMainFragment;
import com.zzz.myapplication.util.ZLog;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.fl_main_content)
    FrameLayout    mFlMainContent;
    @BindView(R.id.nv_main)
    NavigationView mNvMain;
    @BindView(R.id.dl_main)
    DrawerLayout   mDlMain;
    @BindView(R.id.tb_main)
    Toolbar mToolBar;

    ActionBarDrawerToggle mDrawerToggle;
    ZhihuMainFragment mZhihuMainFragment;

    int currentNavigationId = 0;



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"首页");
        mZhihuMainFragment = new ZhihuMainFragment();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDlMain, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDlMain.setDrawerListener(mDrawerToggle);
        loadRootFragment(R.id.fl_main_content, mZhihuMainFragment);
        mNvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                ZLog.d(menuItem.getTitle().toString());
                menuItem.setChecked(true); // 改变item选中状态
                setTitle(menuItem.getTitle()); // 改变页面标题，标明导航状态
                currentNavigationId = menuItem.getItemId();
                mDlMain.closeDrawers(); // 关闭导航菜单
                return true;
            }
        });
    }

}
