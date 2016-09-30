package com.zzz.news.ui.main.fragment;


import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.zzz.news.R;
import com.zzz.news.base.SimpleFramgent;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.OnClick;
import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.contants.Side;
import tyrantgit.explosionfield.ExplosionField;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 17:47
 */

public class MeFragment extends SimpleFramgent {

    @BindView(R.id.ts_me)
    TextSurface             mTextSurface;
    @BindView(R.id.nsv_scroller)
    NestedScrollView        mNsvScroller;
    @BindView(R.id.fab_me_me)
    FloatingActionButton    mFabMeMe;
    @BindView(R.id.ctl_me_title)
    CollapsingToolbarLayout mCtlMeTitle;

    @Override
    protected void initEventAndData() {
        mCtlMeTitle.setTitle("About Me");
        mNsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0) {  //下移
                    mCtlMeTitle.setTitle("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
                } else if (scrollY - oldScrollY < 0) {    //上移
                    mCtlMeTitle.setTitle("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
                }
            }
        });
        Text zText  = TextBuilder
                .create("like qiqi")
//                .setSize(18)
//                .setAlpha(0)
//                .setColor(Color.BLUE)
//                .setPosition(Align.SURFACE_CENTER)
                .build();
        mTextSurface.play(
                new Sequential(
                        Slide.showFrom(Side.TOP, zText, 500),
                        Delay.duration(500),
                        Alpha.hide(zText, 1500)
                )
        );
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @OnClick(R.id.fab_me_me)
    public void onClick(View view) {
        ExplosionField ef = new ExplosionField(mContext);
        ef.explode(mFabMeMe);
    }
}
