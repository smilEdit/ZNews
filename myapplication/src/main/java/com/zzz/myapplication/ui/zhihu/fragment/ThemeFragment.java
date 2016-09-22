package com.zzz.myapplication.ui.zhihu.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.model.bean.ThemeListBean;
import com.zzz.myapplication.presenter.ThemePresenter;
import com.zzz.myapplication.presenter.contract.ThemeContract;
import com.zzz.myapplication.ui.zhihu.adapter.ThemeAdapter;
import com.zzz.myapplication.util.ZLog;
import com.zzz.myapplication.util.ZSnack;
import com.zzz.myapplication.util.ZToast;

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
        ZLog.i("initInject");
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

        ZLog.i("initEventAndData");
        mAdapter = new ThemeAdapter(mContext, mList);
        mAdapter.setOnItemClickListener(new ThemeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                ZSnack.showSnackShort(mRvThemeContent,id+"");
            }
        });
        mRvThemeContent.setLayoutManager(new GridLayoutManager(mContext,2));
        mRvThemeContent.setAdapter(mAdapter);
        mPresenter.getThemeData();
        mRvThemeContent.setVisibility(View.INVISIBLE);
        mViewLoading.start();
    }

    @Override
    protected int getLayoutId() {
        ZLog.i("getLayoutId");
        return R.layout.fragment_theme;
    }

    @Override
    public void showContent(ThemeListBean themeListBean) {
        mViewLoading.stop();
        mRvThemeContent.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(themeListBean.getOthers());
        ZLog.i(mList.toString());
    }


    @Override
    public void showError(String msg) {
        mViewLoading.stop();
        mRvThemeContent.setVisibility(View.VISIBLE);
        ZToast.showLongToast(mContext, "数据加载失败");
    }
}
