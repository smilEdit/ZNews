package com.zzz.news.ui.zhihu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zzz.news.R;
import com.zzz.news.base.SimpleFramgent;
import com.zzz.news.ui.zhihu.adapter.ZhihuMainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 11:51
 */
public class ZhihuMainFragment extends SimpleFramgent {

    @BindView(R.id.tl_zhihu_main)
    TabLayout mTlZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mVpZhihuMain;

    String[] tabTitle = new String[]{"日报", "主题", "专栏", "热门"};

    List<Fragment> mFragments = new ArrayList<Fragment>();
    private ZhihuMainAdapter mAdapter;


    @Override
    protected void initEventAndData() {

        mFragments.add(new DailyFragment());
        mFragments.add(new ThemeFragment());
        mFragments.add(new SectionFragment());
        mFragments.add(new HotFragment());

        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), mFragments);
        mVpZhihuMain.setAdapter(mAdapter);

        for (int i = 0; i < 4; i++) {
            mTlZhihuMain.addTab(mTlZhihuMain.newTab().setText(tabTitle[i]));
        }
        mTlZhihuMain.setupWithViewPager(mVpZhihuMain);
        for (int i = 0; i < 4; i++) {
            mTlZhihuMain.getTabAt(i).setText(tabTitle[i]);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

}
