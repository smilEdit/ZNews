package com.zzz.news.ui.juhe.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.TopNewsBean;
import com.zzz.news.presenter.TopNewsPresenter;
import com.zzz.news.presenter.contract.TopNewsContract;
import com.zzz.news.ui.juhe.adapter.TopNewsAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 11:44
 */

public class TopNewsFragment extends BaseFragment<TopNewsPresenter> implements TopNewsContract.View {
    @BindView(R.id.rv_topnews_content)
    RecyclerView       mRvTopnewsContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_topnews_refresh)
    SwipeRefreshLayout mSrlTopnewsRefresh;

    private List<TopNewsBean.ResultBean.DataBean> mList;
    private TopNewsAdapter mAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mLoadingAnmi.start();
        mAdapter = new TopNewsAdapter(mContext, R.layout.item_topnews, mList);
        mRvTopnewsContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvTopnewsContent.setAdapter(mAdapter);
        mPresenter.getTopNewsData();
        mSrlTopnewsRefresh.setColorSchemeColors(Color.BLUE);
        mSrlTopnewsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getTopNewsData();
            }
        });
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ZToast.showShortToast(mContext, mList.get(position).getUrl());
//                Intent intent = new Intent();
//                intent.setClass(mContext, JuheDetailActivity.class);
//                intent.putExtra("url", mList.get(position).getUrl());
//                mContext.startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topnews;
    }


    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showShortToast(mContext, msg);
    }

    @Override
    public void showContent(List<TopNewsBean.ResultBean.DataBean> list) {
        if (mSrlTopnewsRefresh.isRefreshing()) {
            mSrlTopnewsRefresh.setRefreshing(false);
        }
        mLoadingAnmi.stop();
        mRvTopnewsContent.setVisibility(RecyclerView.VISIBLE);
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
}
