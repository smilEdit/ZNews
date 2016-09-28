package com.zzz.news.ui.juhe.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zzz.news.R;
import com.zzz.news.base.SimpleFramgent;
import com.zzz.news.ui.zhihu.adapter.ZhihuMainAdapter;
import com.zzz.news.ui.zhihu.fragment.HotFragment;
import com.zzz.news.ui.zhihu.fragment.SectionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 13:32
 */

public class JuHeMainFragment extends SimpleFramgent {
    @BindView(R.id.vp_juhe_main)
    ViewPager mVpJuheMain;
    @BindView(R.id.tl_juhe_main)
    TabLayout mTljuheMain;

    String[] tabTitle = new String[]{"头条", "笑话", "微信精选", "未知"};

    List<Fragment> mFragments = new ArrayList<Fragment>();
    private ZhihuMainAdapter mAdapter;

    @Override
    protected void initEventAndData() {

        mFragments.add(new TopNewsFragment());
        mFragments.add(new JokeFragment());
        mFragments.add(new SectionFragment());
        mFragments.add(new HotFragment());

        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), mFragments);
        mVpJuheMain.setAdapter(mAdapter);

        for (int i = 0; i < 4; i++) {
            mTljuheMain.addTab(mTljuheMain.newTab().setText(tabTitle[i]));
        }
        mTljuheMain.setupWithViewPager(mVpJuheMain);
        for (int i = 0; i < 4; i++) {
            mTljuheMain.getTabAt(i).setText(tabTitle[i]);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_juhe_main;
    }

}
