package com.zzz.news.ui.gank.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.app.App;
import com.zzz.news.base.SimpleActivity;
import com.zzz.news.model.db.RealmHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 13:12
 */

public class TechDetailActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar              mToolBar;
    @BindView(R.id.wv_tech_content)
    WebView              mWvTechContent;
    @BindView(R.id.loading_anmi)
    RotateLoading        mLoadingAnmi;
    @BindView(R.id.fab_tech_detail)
    FloatingActionButton mFabTechDetail;

    private String mTitle, mUrl, mId, mTech;
    private boolean     isLiked;
    private RealmHelper mRealmHelper;

    @Override
    protected void initEventAndData() {
        mRealmHelper = App.getAppComponent().realmHelper();
        Intent intent = getIntent();
        mTech = intent.getExtras().getString("tech");
        mTitle = intent.getExtras().getString("title");
        mUrl = intent.getExtras().getString("url");
        mId = intent.getExtras().getString("id");
        setToolBar(mToolBar, mTitle);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
        WebSettings settings = mWvTechContent.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWvTechContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWvTechContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mLoadingAnmi.stop();
                } else {
                    if (!mLoadingAnmi.isStart()) {
                        mLoadingAnmi.start();
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        mWvTechContent.loadUrl(mUrl);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tech_detail;
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWvTechContent.canGoBack()) {
            mWvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.fab_tech_detail)
    public void onClick() {

    }
}
