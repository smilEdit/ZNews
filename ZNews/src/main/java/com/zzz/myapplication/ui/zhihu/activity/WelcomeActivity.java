package com.zzz.myapplication.ui.zhihu.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseActivity;
import com.zzz.myapplication.model.bean.WelcomeBean;
import com.zzz.myapplication.presenter.contract.WelcomeContract;
import com.zzz.myapplication.presenter.contract.WelcomePresenter;
import com.zzz.myapplication.ui.main.activity.MainActivity;
import com.zzz.myapplication.util.ZImageLoader;
import com.zzz.myapplication.util.ZLog;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 15:37
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView mIvWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView  mTvWelcomeAuthor;

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        ZImageLoader.setImg(this,welcomeBean.getImg(),mIvWelcomeBg);
        mIvWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        mTvWelcomeAuthor.setText(welcomeBean.getText());
    }

    @Override
    public void jump2Main() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void showError(String msg) {
        //默认图片
        ZLog.w("erroooooooooooooooooor");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.clear(mIvWelcomeBg);
    }
}
