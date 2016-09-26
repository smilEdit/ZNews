package com.zzz.myapplication.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zzz.myapplication.R;
import com.zzz.myapplication.base.SimpleFramgent;
import com.zzz.myapplication.presenter.TechPresenter;
import com.zzz.myapplication.ui.gank.adapter.GankMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 9:48
 */

public class GankMainFragment extends SimpleFramgent {
    @BindView(R.id.vp_gank_main)
    ViewPager mVpGankMain;
    @BindView(R.id.tl_gank_main)
    TabLayout mTabLayout;

    String[] tabTitle = new String[]{"福利","android","ios","前端"};

    TechFragment androidFragment;
    TechFragment iOSFragment;
    TechFragment webFragment;
    TechFragment girlFragment;

    List<Fragment> mFragments = new ArrayList<>();
    private GankMainAdapter mGankMainAdapter;

    @Override
    protected void initEventAndData() {

        androidFragment = new TechFragment();
        iOSFragment = new TechFragment();
        webFragment = new TechFragment();
        girlFragment = new TechFragment();
        Bundle androidBundle = new Bundle();
        androidBundle.putString("tech", TechPresenter.TECH_ANDROID);
        androidFragment.setArguments(androidBundle);
        Bundle iosBundle = new Bundle();
        iosBundle.putString("tech", TechPresenter.TECH_IOS);
        iOSFragment.setArguments(iosBundle);
        Bundle webBundle = new Bundle();
        webBundle.putString("tech", TechPresenter.TECH_WEB);
        webFragment.setArguments(webBundle);

        mFragments.add(androidFragment);
        mFragments.add(iOSFragment);
        mFragments.add(webFragment);
        mFragments.add(girlFragment);

        mGankMainAdapter = new GankMainAdapter(getChildFragmentManager(), mFragments);
        mVpGankMain.setAdapter(mGankMainAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mVpGankMain);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
    }
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_main;
    }

}
