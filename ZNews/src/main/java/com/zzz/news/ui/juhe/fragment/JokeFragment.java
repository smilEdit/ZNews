package com.zzz.news.ui.juhe.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.victor.loading.rotate.RotateLoading;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.JokeBean;
import com.zzz.news.presenter.JokePresenter;
import com.zzz.news.presenter.contract.JokeContract;
import com.zzz.news.ui.juhe.adapter.JokeAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 16:18
 */

public class JokeFragment extends BaseFragment<JokePresenter> implements JokeContract.View {
    @BindView(R.id.rv_topnews_content)
    RecyclerView       mRvTopnewsContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_topnews_refresh)
    SwipeRefreshLayout mSrlTopnewsRefresh;

    private List<JokeBean.ResultBean.DataBean> mList;
    private JokeAdapter mAdapter;
    private LoadMoreWrapper<Object> mMoreWrapper;

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mLoadingAnmi.start();
        mAdapter = new JokeAdapter(mContext, R.layout.item_joke, mList);
        mRvTopnewsContent.setLayoutManager(new LinearLayoutManager(mContext));
        mSrlTopnewsRefresh.setColorSchemeColors(Color.BLUE);
        mSrlTopnewsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getJokeData();
            }
        });
        mMoreWrapper = new LoadMoreWrapper<>(mAdapter);
        mMoreWrapper.setLoadMoreView(R.layout.foot);
        mMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getMoreData();
            }
        });
        mRvTopnewsContent.setAdapter(mMoreWrapper);
        mPresenter.getJokeData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topnews;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showContent(List<JokeBean.ResultBean.DataBean> list) {
        if (mSrlTopnewsRefresh.isRefreshing()) {
            mSrlTopnewsRefresh.setRefreshing(false);
        }
        mLoadingAnmi.stop();
        mList.clear();
        mList.addAll(list);
        mMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<JokeBean.ResultBean.DataBean> list, int page) {
        mList.addAll(list);
        mMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        ZToast.showShortToast(mContext, msg);
    }
}
