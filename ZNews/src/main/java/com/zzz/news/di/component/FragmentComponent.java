package com.zzz.news.di.component;

import android.app.Activity;

import com.zzz.news.di.FragmentScope;
import com.zzz.news.di.module.FragmentModule;
import com.zzz.news.ui.gank.fragment.GankMainFragment;
import com.zzz.news.ui.gank.fragment.GirlFragment;
import com.zzz.news.ui.gank.fragment.TechFragment;
import com.zzz.news.ui.juhe.fragment.JokeFragment;
import com.zzz.news.ui.juhe.fragment.JuHeMainFragment;
import com.zzz.news.ui.juhe.fragment.LishiFragment;
import com.zzz.news.ui.juhe.fragment.TopNewsFragment;
import com.zzz.news.ui.juhe.fragment.WeixinFragment;
import com.zzz.news.ui.main.fragment.LikeFragment;
import com.zzz.news.ui.main.fragment.MeFragment;
import com.zzz.news.ui.main.fragment.PersonFragment;
import com.zzz.news.ui.main.fragment.SettingFragment;
import com.zzz.news.ui.zhihu.fragment.CommentFragment;
import com.zzz.news.ui.zhihu.fragment.DailyFragment;
import com.zzz.news.ui.zhihu.fragment.HotFragment;
import com.zzz.news.ui.zhihu.fragment.SectionFragment;
import com.zzz.news.ui.zhihu.fragment.ThemeFragment;
import com.zzz.news.ui.zhihu.fragment.ZhihuMainFragment;

import dagger.Component;

/**
 * @创建者 zlf
 * @创建时间 2016/9/19 14:11
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

//    RetrofitHelper getRetrofitHelper();

    Activity getActivity();

    //main fragment

    void inject(MeFragment meFragment);

    void inject(PersonFragment personFragment);

    void inject(SettingFragment settingFragment);

    void inject(LikeFragment likeFragment);

    //知乎 Fragment

    void inject(ZhihuMainFragment zhihuMainFragment);

    void inject(DailyFragment dailyFragment);

    void inject(ThemeFragment themeFragment);

    void inject(SectionFragment sectionFragment);

    void inject(HotFragment hotFragment);

    void inject(CommentFragment commentFragment);

    //Gankio Fragment

    void inject(GankMainFragment gankMainFragment);

    void inject(TechFragment techFragment);

    void inject(GirlFragment girlFragment);

    //聚合 fragment

    void inject(TopNewsFragment topNewsFragment);

    void inject(JuHeMainFragment juHeMainFragment);

    void inject(JokeFragment jokeFragment);

    void inject(WeixinFragment weixinFragment);

    void inject(LishiFragment lishiFragment);
}
