package com.zzz.news.ui.gank.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zzz.news.R;
import com.zzz.news.base.SimpleActivity;
import com.zzz.news.util.ZImageLoader;
import com.zzz.news.util.ZShare;
import com.zzz.news.util.ZSnack;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;


/**
 * @创建者 zlf
 * @创建时间 2016/9/26 14:15
 */

public class GirlDetailActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar   mToolBar;
    @BindView(R.id.pv_girl_detail)
    PhotoView mPvGirlDetail;
    private String mUrl;

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"妹纸");
        Intent intent = getIntent();
        mUrl = intent.getExtras().getString("url");
        if (mUrl != null) {
            ZImageLoader.setImg(mContext, mUrl,mPvGirlDetail);
        }
//        intent.getExtras().getString("id");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_girl_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girl_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_action_save:

                ZSnack.showSnackShort(mPvGirlDetail,"保存");
                break;
            case R.id.menu_action_share:
//                ZShare.shareImage(mContext, ZSystem.saveBitmapToFile(mContext,mUrl,new Bitmap(),),"妹纸");
                ZShare.shareText(mContext,mUrl,"妹纸");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }
}
