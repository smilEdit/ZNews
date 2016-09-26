package com.zzz.news.ui.main.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;
import com.zzz.news.R;
import com.zzz.news.base.BaseActivity;
import com.zzz.news.presenter.MainPresenter;
import com.zzz.news.presenter.contract.MainContract;
import com.zzz.news.ui.gank.fragment.GankMainFragment;
import com.zzz.news.ui.main.fragment.AuthorFragment;
import com.zzz.news.ui.zhihu.fragment.ZhihuMainFragment;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, OnMenuItemClickListener, OnMenuItemLongClickListener {

    //
    //    @BindView(R.id.fl_main_content)
    //    FrameLayout    mFlMainContent;
    //    @BindView(R.id.nv_main)
    //    NavigationView mNvMain;
    //    @BindView(R.id.dl_main)
    //    DrawerLayout   mDlMain;
    @BindView(R.id.tb_main)
    Toolbar mToolBar;

    //    ActionBarDrawerToggle mDrawerToggle;
    ZhihuMainFragment mZhihuMainFragment;

    private ContextMenuDialogFragment mMenuDialogFragment;

    private static final String ITEM_ZHIHU = "知乎日报";
    private static final String ITEM_GANK  = "干货集中营";
    private static final String ITEM_ME = "Me";

    private FragmentManager  fragmentManager;

    private GankMainFragment mGankMainFragment;
    private AuthorFragment mAuthorFragment;
    private SupportFragment mCurrentFragment;

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
        setToolBar(mToolBar, ITEM_ZHIHU);
        fragmentManager = getSupportFragmentManager();
        mZhihuMainFragment = new ZhihuMainFragment();
        mGankMainFragment = new GankMainFragment();
        mAuthorFragment = new AuthorFragment();
        mCurrentFragment = mZhihuMainFragment;
        loadMultipleRootFragment(R.id.fl_main_content, 0, mZhihuMainFragment, mGankMainFragment);
        initMenuFragment();
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.detail_bottom_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onMenuItemClick(View view, int position) {
        switch (position) {
            case 1:
                if (mCurrentFragment == mZhihuMainFragment) {
                    return;
                }
                showHideFragment(mZhihuMainFragment, mCurrentFragment);
                setToolBar(mToolBar,ITEM_ZHIHU);
                mCurrentFragment = mZhihuMainFragment;
                break;
            case 2:
                if (mCurrentFragment == mGankMainFragment) {
                    return;
                }
                showHideFragment(mGankMainFragment, mCurrentFragment);
                setToolBar(mToolBar,ITEM_GANK);
                mCurrentFragment = mGankMainFragment;
                break;
            case 5:
                if (mCurrentFragment == mAuthorFragment) {
                    return;
                }
                showHideFragment(mAuthorFragment, mCurrentFragment);
                setToolBar(mToolBar,ITEM_ME);
                mCurrentFragment = mAuthorFragment;
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View view, int position) {
        ZToast.showShortToast(mConext, "longClick" + position);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setDividerColor(R.color.md_deep_purple_600_color_code);
        close.setResource(R.mipmap.close);

        MenuObject zhihu = new MenuObject("知乎");
        zhihu.setDividerColor(R.color.md_light_blue_600_color_code);
        zhihu.setResource(R.mipmap.zhihu);

        MenuObject meizhi = new MenuObject("妹纸");
        meizhi.setDividerColor(R.color.md_yellow_600_color_code);
        meizhi.setResource(R.mipmap.meizhi);

        MenuObject wechat = new MenuObject("微信");
        wechat.setDividerColor(R.color.md_green_600_color_code);
        wechat.setResource(R.mipmap.wechat);

        MenuObject fav = new MenuObject("收藏");
        fav.setDividerColor(R.color.md_red_600_color_code);
        fav.setResource(R.mipmap.fav);

        MenuObject me = new MenuObject("作者");
        me.setDividerColor(R.color.md_teal_600_color_code);
        me.setResource(R.mipmap.about);

        MenuObject settings = new MenuObject("设置");
        settings.setResource(R.mipmap.setting);

        menuObjects.add(close);
        menuObjects.add(zhihu);
        menuObjects.add(meizhi);
        menuObjects.add(wechat);
        menuObjects.add(fav);
        menuObjects.add(me);
        menuObjects.add(settings);
        return menuObjects;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
