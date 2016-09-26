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

    String[] tabTitle = new String[]{"前端","android","ios","福利"};

    TechFragment androidFragment;
    TechFragment iOSFragment;
    TechFragment webFragment;
    GirlFragment girlFragment;

    List<Fragment> mFragments = new ArrayList<>();
    private GankMainAdapter mGankMainAdapter;

    @Override
    protected void initEventAndData() {

        androidFragment = new TechFragment();
        iOSFragment = new TechFragment();
        webFragment = new TechFragment();
        girlFragment = new GirlFragment();
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

        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setupWithViewPager(mVpGankMain);
        for (int i = 0; i < 4; i++) {
            mTabLayout.getTabAt(i).setText(tabTitle[i]);
        }
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
