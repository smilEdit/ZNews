package com.zzz.myapplication.ui.zhihu.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseActivity;
import com.zzz.myapplication.model.bean.DetailExtraBean;
import com.zzz.myapplication.model.bean.ZhihuDetailBean;
import com.zzz.myapplication.presenter.ZhihuDetailPresenter;
import com.zzz.myapplication.presenter.contract.ZhihuDetailContract;
import com.zzz.myapplication.util.ZHtml;
import com.zzz.myapplication.util.ZImageLoader;
import com.zzz.myapplication.util.ZSnack;
import com.zzz.myapplication.util.ZToast;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * @创建者 zlf
 * @创建时间 2016/9/20 14:40
 */
public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {


    @BindView(R.id.iv_detail_bar)
    ImageView        mIvDetailBar;
    @BindView(R.id.tv_detail_bar)
    TextView         mTvDetailBar;
    @BindView(R.id.ctl_detail_title)
    CollapsingToolbarLayout mCtlDetailTitle;
    @BindView(R.id.wv_detail_centent)
    WebView          mWvDetailCentent;
    @BindView(R.id.loading_anmi)
    RotateLoading    mLoadingAnmi;
    @BindView(R.id.nsv_detail_content)
    NestedScrollView mNsvDetailContent;
    @BindView(R.id.tv_detail_bottom_like)
    TextView         mTvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView         mTvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView         mTvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    LinearLayout     mLlDetailBottom;
    private int mId;
    private boolean isBottomShow = true;


    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mId = intent.getExtras().getInt("id");
        mPresenter.getDetailData(mId);
        mPresenter.getExtraData(mId);
        mLoadingAnmi.start();
        mWvDetailCentent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mNsvDetailContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    mLlDetailBottom.animate().translationY(mLlDetailBottom.getHeight());
                } else if(scrollY - oldScrollY < 0 && !isBottomShow){    //上移出现
                    isBottomShow = true;
                    mLlDetailBottom.animate().translationY(0);
                }
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    public void showContent(ZhihuDetailBean zhihuDetailBean) {
        mLoadingAnmi.stop();
        ZImageLoader.setImg(mConext,zhihuDetailBean.getImage(),mIvDetailBar);
        mTvDetailBar.setText(zhihuDetailBean.getImage_source());
        mCtlDetailTitle.setTitle(zhihuDetailBean.getTitle());
        String htmlData = ZHtml.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), zhihuDetailBean.getJs());
        mWvDetailCentent.loadData(htmlData,ZHtml.MIME_TYPE,ZHtml.ENCODING);
    }

    @Override
    public void showExtraInfo(DetailExtraBean detailExtraBean) {
        mLoadingAnmi.stop();
        mTvDetailBottomLike.setText(String.format("%d赞",detailExtraBean.getPopularity()));
        mTvDetailBottomComment.setText(String.format("%d条评论",detailExtraBean.getComments()));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWvDetailCentent.canGoBack()) {
            mWvDetailCentent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id.tv_detail_bottom_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_bottom_like:
                ZSnack.showSnackShort(mWvDetailCentent,"赞");
                break;
            case R.id.tv_detail_bottom_comment:
                ZSnack.showSnackShort(mWvDetailCentent,"评论");
                break;
            case R.id.tv_detail_bottom_share:
                ZSnack.showSnackShort(mWvDetailCentent,"分享");
                break;
        }
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showLongToast(mConext,"获取数据失败");
    }
}
