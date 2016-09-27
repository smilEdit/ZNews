package com.zzz.news.ui.zhihu.activity;

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
import com.zzz.news.model.bean.SectionChildListBean;
import com.zzz.news.presenter.SectionChildPresenter;
import com.zzz.news.presenter.contract.SectionChildContract;
import com.zzz.news.ui.zhihu.adapter.SectionChildAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 10:49
 */

public class SectionActivity extends BaseActivity<SectionChildPresenter> implements SectionChildContract.View {
    @BindView(R.id.tool_bar)
    Toolbar            mToolBar;
    @BindView(R.id.rv_theme_content)
    RecyclerView       mRvThemeContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_theme_refresh)
    SwipeRefreshLayout mSrlThemeRefresh;
    private int mId;

    List<SectionChildListBean.StoriesBean> mList;
    private SectionChildAdapter mAdapter;


    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mId = intent.getExtras().getInt("id");
        setToolBar(mToolBar,intent.getStringExtra("title"));
        mAdapter = new SectionChildAdapter(mConext, R.layout.item_daily, mList);
        mLoadingAnmi.start();
        mList = new ArrayList<>();
        mRvThemeContent.setLayoutManager(new LinearLayoutManager(mConext));
        mRvThemeContent.setAdapter(mAdapter);
        mPresenter.getSectionChildData(mId);

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                ZToast.showShortToast(mConext,position+"");
//                mPresenter.intsert2db(mId);
//                mAdapter.setReadState(position,true);
//                mAdapter.notifyItemChanged(position);
//                Intent intent = new Intent();
//                intent.setClass(mConext, ZhihuDetailActivity.class);
//                intent.putExtra("id", mList.get(position).getId());
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mConext).toBundle());
            }
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mSrlThemeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSectionChildData(mId);
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
    public void showContent(SectionChildListBean sectionChildListBean) {
        if (mSrlThemeRefresh.isRefreshing()) {
            mSrlThemeRefresh.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        mList.clear();
        mList.addAll(sectionChildListBean.getStories());
        mAdapter.notifyDataSetChanged();
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
