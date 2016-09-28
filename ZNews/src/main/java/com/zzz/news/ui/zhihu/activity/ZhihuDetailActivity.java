package com.zzz.news.ui.zhihu.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.base.BaseActivity;
import com.zzz.news.model.bean.DetailExtraBean;
import com.zzz.news.model.bean.ZhihuDetailBean;
import com.zzz.news.presenter.ZhihuDetailPresenter;
import com.zzz.news.presenter.contract.ZhihuDetailContract;
import com.zzz.news.util.ZHtml;
import com.zzz.news.util.ZImageLoader;
import com.zzz.news.util.ZShare;
import com.zzz.news.util.ZSnack;
import com.zzz.news.util.ZToast;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @创建者 zlf
 * @创建时间 2016/9/20 14:40
 */
public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {


    @BindView(R.id.iv_detail_bar)
    ImageView               mIvDetailBar;
    @BindView(R.id.tv_detail_bar)
    TextView                mTvDetailBar;
    @BindView(R.id.ctl_detail_title)
    CollapsingToolbarLayout mCtlDetailTitle;
    @BindView(R.id.wv_detail_centent)
    WebView                 mWvDetailCentent;
    @BindView(R.id.loading_anmi)
    RotateLoading           mLoadingAnmi;
    @BindView(R.id.nsv_detail_content)
    NestedScrollView        mNsvDetailContent;
    @BindView(R.id.tv_detail_bottom_like)
    TextView                mTvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView                mTvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView                mTvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout             mLlDetailBottom;
    @BindView(R.id.fab_detail)
    FloatingActionButton mFabDetail;

    private int mId;
    private boolean isBottomShow = true;

    private int allNum   = 0;
    private int shortNum = 0;
    private int longNum  = 0;
    private String mShareUrl;
    private int    mZan;

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mId = intent.getExtras().getInt("id");
        mPresenter.getDetailData(mId);
        mPresenter.getExtraData(mId);
        mLoadingAnmi.start();
        mWvDetailCentent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mNsvDetailContent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    mLlDetailBottom.animate().translationY(mLlDetailBottom.getHeight());
                    mFabDetail.hide(true);
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {    //上移出现
                    isBottomShow = true;
                    mLlDetailBottom.animate().translationY(0);
                    mFabDetail.show(true);
                }
            }
        });

//        mFabDetail.attachToListView();
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
        mShareUrl = zhihuDetailBean.getShare_url();
        ZImageLoader.setImg(mConext, zhihuDetailBean.getImage(), mIvDetailBar);
        mTvDetailBar.setText(zhihuDetailBean.getImage_source());
        mCtlDetailTitle.setTitle(zhihuDetailBean.getTitle());
        String htmlData = ZHtml.createHtmlData(zhihuDetailBean.getBody(), zhihuDetailBean.getCss(), zhihuDetailBean.getJs());
        mWvDetailCentent.loadData(htmlData, ZHtml.MIME_TYPE, ZHtml.ENCODING);
    }

    @Override
    public void showExtraInfo(DetailExtraBean detailExtraBean) {
        mLoadingAnmi.stop();
        mTvDetailBottomLike.setText(String.format("%d个赞", detailExtraBean.getPopularity()));
        mZan = detailExtraBean.getPopularity();
        mTvDetailBottomComment.setText(String.format("%d条评论", detailExtraBean.getComments()));
        allNum = detailExtraBean.getComments();
        shortNum = detailExtraBean.getShort_comments();
        longNum = detailExtraBean.getLong_comments();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWvDetailCentent.canGoBack()) {
            mWvDetailCentent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private int i = 0;

    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id.tv_detail_bottom_share,R.id.fab_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_bottom_like:
                i++;
                if (i < 10) {
                    mTvDetailBottomLike.setText(String.format("%d个赞", mZan++));
                } else {
                    ZSnack.showSnackShort(mWvDetailCentent, "过分了哦");
                }
                break;
            case R.id.tv_detail_bottom_comment:
                Intent intent = getIntent();
                intent.setClass(this, CommentActivity.class);
                intent.putExtra("id", mId);
                intent.putExtra("allNum", allNum);
                intent.putExtra("shortNum", shortNum);
                intent.putExtra("longNum", longNum);
                startActivity(intent);
                break;
            case R.id.tv_detail_bottom_share:
                ZShare.shareText(mConext, mShareUrl, "给你看好");
                break;
            case R.id.fab_detail:
                onBackPressedSupport();
                break;
        }
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showLongToast(mConext, msg);
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
