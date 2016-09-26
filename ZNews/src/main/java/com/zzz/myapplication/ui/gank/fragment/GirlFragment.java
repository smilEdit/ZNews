package com.zzz.myapplication.ui.gank.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.model.bean.GankItemBean;
import com.zzz.myapplication.presenter.GirlPresenter;
import com.zzz.myapplication.presenter.contract.GirlContract;
import com.zzz.myapplication.ui.gank.activity.GirlDetailActivity;
import com.zzz.myapplication.ui.gank.adapter.GirlAdapter;
import com.zzz.myapplication.util.ZLog;
import com.zzz.myapplication.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 9:59
 */

public class GirlFragment extends BaseFragment<GirlPresenter> implements GirlContract.View {

    @BindView(R.id.rv_girl_content)
    RecyclerView       mRvGirlContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_girl_refresh)
    SwipeRefreshLayout mSrlGirlRefresh;

    private List<GankItemBean> mList;

    private boolean isLoadingMore = false;
    private GirlAdapter mAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new GirlAdapter(mContext, mList);
        mRvGirlContent.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mRvGirlContent.setAdapter(mAdapter);
        //滑动监听
        mRvGirlContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = ((StaggeredGridLayoutManager)mRvGirlContent.getLayoutManager()).findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0],visibleItems[1]);
                if (lastItem > mAdapter.getItemCount() - 5 && !isLoadingMore && dy > 0 ) {
                    isLoadingMore = true;
                    mPresenter.getMoreGirlData();
                }
            }
        });
        mSrlGirlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGirlData();
            }
        });
        mAdapter.setOnItemClickListener(new GirlAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, GirlDetailActivity.class);
                intent.putExtra("url",mList.get(position).getUrl());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "shareView");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        mLoadingAnmi.start();
        mPresenter.getGirlData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girl;
    }

    @Override
    public void showContent(List<GankItemBean> gankItemBean) {
        ZLog.i(gankItemBean.toString());
        if (mSrlGirlRefresh.isRefreshing()) {
            mSrlGirlRefresh.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        mList.clear();
        mList.addAll(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GankItemBean> gankItemBean, int currentPage) {
        isLoadingMore = false;
        mLoadingAnmi.stop();
        mList.addAll(gankItemBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showShortToast(mContext,msg);
    }

}
