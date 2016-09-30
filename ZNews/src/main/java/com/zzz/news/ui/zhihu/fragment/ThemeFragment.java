package com.zzz.news.ui.zhihu.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.ThemeListBean;
import com.zzz.news.presenter.ThemePresenter;
import com.zzz.news.presenter.contract.ThemeContract;
import com.zzz.news.ui.zhihu.activity.ThemeActivity;
import com.zzz.news.ui.zhihu.adapter.ThemeAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:40
 */
public class ThemeFragment extends BaseFragment<ThemePresenter> implements ThemeContract.View {
    @BindView(R.id.rv_theme_content)
    RecyclerView  mRvThemeContent;
    @BindView(R.id.view_loading)
    RotateLoading mViewLoading;

    List<ThemeListBean.OthersBean> mList = new ArrayList<>();

    ThemeAdapter mAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

        mAdapter = new ThemeAdapter(mContext, mList);
        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, ThemeActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
        mRvThemeContent.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRvThemeContent.setAdapter(mAdapter);
        mPresenter.getThemeData();
        mViewLoading.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    public void showContent(ThemeListBean themeListBean) {
        mViewLoading.stop();
        mList.clear();
        mList.addAll(themeListBean.getOthers());
    }


    @Override
    public void showError(String msg) {
        mViewLoading.stop();
        ZToast.showLongToast(mContext, "数据加载失败");
    }
}
