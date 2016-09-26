package com.zzz.news.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zzz.news.ui.zhihu.fragment.CommentFragment;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 14:54
 */

public class CommentMainAdapter extends FragmentPagerAdapter{

    private List<CommentFragment> fragments;

    public CommentMainAdapter(FragmentManager fm,List<CommentFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
