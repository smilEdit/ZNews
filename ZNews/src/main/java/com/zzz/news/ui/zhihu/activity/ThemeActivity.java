package com.zzz.news.ui.zhihu.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zzz.news.R;
import com.zzz.news.base.BaseActivity;
import com.zzz.news.model.bean.ThemeChildListBean;
import com.zzz.news.presenter.ThemeChildPresenter;
import com.zzz.news.presenter.contract.ThemeChildContract;
import com.zzz.news.ui.zhihu.adapter.ThemeChildAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 9:22
 */

public class ThemeActivity extends BaseActivity<ThemeChildPresenter> implements ThemeChildContract.View {
    @BindView(R.id.tool_bar)
    Toolbar            mToolBar;
    @BindView(R.id.rv_theme_content)
    RecyclerView       mRvThemeContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_theme_refresh)
    SwipeRefreshLayout mSrlThemeRefresh;

    List<ThemeChildListBean.StoriesBean> mList;
    private ThemeChildAdapter mAdapter;
    private int mId;

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mId = intent.getExtras().getInt("id");
        mLoadingAnmi.start();
        mList = new ArrayList<>();
        mAdapter = new ThemeChildAdapter(mConext, R.layout.item_daily, mList);
        mRvThemeContent.setLayoutManager(new LinearLayoutManager(mConext));
        mRvThemeContent.setAdapter(mAdapter);
        mPresenter.getThemeChildData(mId);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.insertRead2Db(mId);
                mAdapter.setReadState(position,true);
                mAdapter.notifyItemChanged(position);
                Intent intent = new Intent();
                intent.setClass(mConext, ZhihuDetailActivity.class);
                intent.putExtra("id", mList.get(position).getId());
//                if (shareView != null) {
//                    mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext, shareView, "shareView").toBundle());
//                } else {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mConext).toBundle());
//                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mSrlThemeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getThemeChildData(mId);
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_theme;
    }

    @Override
    public void showContent(ThemeChildListBean themeChildListBean) {
        if (mSrlThemeRefresh.isRefreshing()) {
            mSrlThemeRefresh.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        setToolBar(mToolBar,themeChildListBean.getName());
        mList.clear();
        mList.addAll(themeChildListBean.getStories());
        mAdapter.notifyDataSetChanged();
//        mAdapter.setTopInfo();
    }

    @Override
    public void showError(String msg) {
        if (mSrlThemeRefresh.isRefreshing()) {
            mSrlThemeRefresh.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        ZToast.showShortToast(mConext,msg);
    }
}
