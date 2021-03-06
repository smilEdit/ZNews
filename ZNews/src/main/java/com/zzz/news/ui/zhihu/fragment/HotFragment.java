package com.zzz.news.ui.zhihu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.HotListBean;
import com.zzz.news.presenter.HotPresenter;
import com.zzz.news.presenter.contract.HotContract;
import com.zzz.news.ui.zhihu.activity.ZhihuDetailActivity;
import com.zzz.news.ui.zhihu.adapter.HotAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:43
 */
public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View {
    @BindView(R.id.rv_hot_content)
    RecyclerView       mRvHotContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_hot_refresh)
    SwipeRefreshLayout mSrlHotRefresh;

    List<HotListBean.RecentBean> mList;
    private HotAdapter mAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mLoadingAnmi.start();
        mAdapter = new HotAdapter(mContext, R.layout.item_daily, mList);
        mRvHotContent.setVisibility(RecyclerView.INVISIBLE);
        mRvHotContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvHotContent.setAdapter(mAdapter);
        mPresenter.getHotData();
        mSrlHotRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getHotData();
            }
        });
        mSrlHotRefresh.setColorSchemeColors(Color.BLUE);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id", mList.get(position).getNews_id());
                mContext.startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showShortToast(mContext, msg);
    }

    @Override
    public void showContent(HotListBean hotListBean) {
        if (mSrlHotRefresh.isRefreshing()) {
            mSrlHotRefresh.setRefreshing(false);
        }
        mLoadingAnmi.stop();
        mRvHotContent.setVisibility(RecyclerView.VISIBLE);
        mList.clear();
        mList.addAll(hotListBean.getRecent());
        mAdapter.notifyDataSetChanged();
    }
}
