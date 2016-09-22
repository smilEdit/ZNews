package com.zzz.myapplication.ui.main.activity;

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
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseActivity;
import com.zzz.myapplication.presenter.MainPresenter;
import com.zzz.myapplication.presenter.contract.MainContract;
import com.zzz.myapplication.ui.zhihu.fragment.ZhihuMainFragment;
import com.zzz.myapplication.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;




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

    private FragmentManager fragmentManager;

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
        setToolBar(mToolBar,"知乎");
        fragmentManager = getSupportFragmentManager();
        mZhihuMainFragment = new ZhihuMainFragment();

//        mDrawerToggle = new ActionBarDrawerToggle(this, mDlMain, mToolBar, R.string.drawer_open, R.string.drawer_close);
//        mDrawerToggle.syncState();
//        mDlMain.setDrawerListener(mDrawerToggle);
        loadRootFragment(R.id.fl_main_content, mZhihuMainFragment);
//        mNvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                ZLog.d(menuItem.getTitle().toString());
//                menuItem.setChecked(true); // 改变item选中状态
//                setTitle(menuItem.getTitle()); // 改变页面标题，标明导航状态
//                currentNavigationId = menuItem.getItemId();
//                mDlMain.closeDrawers(); // 关闭导航菜单
//                return true;
//            }
//        });
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
        ZToast.showShortToast(mConext,"onClick"+position);
    }

    @Override
    public void onMenuItemLongClick(View view, int position) {
        ZToast.showShortToast(mConext,"longClick"+position);
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
        close.setResource(R.mipmap.close);

        MenuObject zhihu = new MenuObject("知乎");
        zhihu.setResource(R.mipmap.zhihu);

        MenuObject meizhi = new MenuObject("妹纸");
        meizhi.setResource(R.mipmap.meizhi);

        MenuObject wechat = new MenuObject("微信");
        wechat.setResource(R.mipmap.wechat);

        MenuObject fav = new MenuObject("My Favorite");
        fav.setResource(R.mipmap.fav);

        MenuObject settings = new MenuObject("settings");
        settings.setResource(R.mipmap.setting);

        MenuObject me = new MenuObject("About Me");
        me.setResource(R.mipmap.about);

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
