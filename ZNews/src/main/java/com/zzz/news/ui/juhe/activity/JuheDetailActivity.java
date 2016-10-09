package com.zzz.news.ui.juhe.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.melnykov.fab.FloatingActionButton;
import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.base.SimpleActivity;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 17:22
 */

public class JuheDetailActivity extends SimpleActivity {
    @BindView(R.id.tool_bar)
    Toolbar       mToolBar;
    @BindView(R.id.wv_tech_content)
    WebView       mWvTechContent;
    @BindView(R.id.loading_anmi)
    RotateLoading mLoadingAnmi;
    @BindView(R.id.fab_like)
    FloatingActionButton mFabLike;

    private boolean isLiked;

    private String mTitle, mUrl;
    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mTitle = intent.getExtras().getString("title");
        mUrl = intent.getExtras().getString("url");
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

}
