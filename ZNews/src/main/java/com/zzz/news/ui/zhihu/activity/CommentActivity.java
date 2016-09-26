package com.zzz.news.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.zzz.news.R;
import com.zzz.news.base.SimpleActivity;
import com.zzz.news.ui.zhihu.adapter.CommentMainAdapter;
import com.zzz.news.ui.zhihu.fragment.CommentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 15:44
 */

public class CommentActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar   mToolBar;
    @BindView(R.id.tl_comment)
    TabLayout mTlComment;
    @BindView(R.id.vp_comment)
    ViewPager mVpComment;

    List<CommentFragment> fragments = new ArrayList<>();
    CommentMainAdapter mAdapter;

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        int allNum = intent.getExtras().getInt("allNum");
        int shortNum = intent.getExtras().getInt("shortNum");
        int longNum = intent.getExtras().getInt("longNum");
        int id = intent.getExtras().getInt("id");
        intent.getExtras().getInt("shortNum");
        setToolBar(mToolBar, String.format("%d条评论", allNum));
        CommentFragment shortCommentFragment = new CommentFragment();
        Bundle shortBundle = new Bundle();
        shortBundle.putInt("id", id);
        shortBundle.putInt("kind", 0);
        shortCommentFragment.setArguments(shortBundle);
        CommentFragment longCommentFragment = new CommentFragment();
        Bundle longBundle = new Bundle();
        longBundle.putInt("id", id);
        longBundle.putInt("kind", 1);
        longCommentFragment.setArguments(longBundle);
        fragments.add(shortCommentFragment);
        fragments.add(longCommentFragment);
        mAdapter = new CommentMainAdapter(getSupportFragmentManager(), fragments);
        mVpComment.setAdapter(mAdapter);
        mTlComment.addTab(mTlComment.newTab().setText(String.format("短评论(%d)", shortNum)));
        mTlComment.addTab(mTlComment.newTab().setText(String.format("长评论(%d)", longNum)));
        mTlComment.setupWithViewPager(mVpComment);
        mTlComment.getTabAt(0).setText(String.format("短评论(%d)", shortNum));
        mTlComment.getTabAt(1).setText(String.format("长评论(%d)", longNum));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }

}
