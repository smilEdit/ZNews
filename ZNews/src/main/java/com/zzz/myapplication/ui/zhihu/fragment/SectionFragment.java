package com.zzz.myapplication.ui.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.model.bean.SectionListBean;
import com.zzz.myapplication.presenter.SectionPresenter;
import com.zzz.myapplication.presenter.contract.SectionContract;
import com.zzz.myapplication.ui.zhihu.adapter.SectionAdapter;
import com.zzz.myapplication.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:43
 */
public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionContract.View {
    @BindView(R.id.rv_section_fragment_content)
    RecyclerView       mRvSectionFragmentContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.srl_section_fragment)
    SwipeRefreshLayout mSrlSectionFragment;

    private List<SectionListBean.DataBean> mList;
    private SectionAdapter                 mAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new SectionAdapter(mContext, R.layout.item_section, mList);
        mRvSectionFragmentContent.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRvSectionFragmentContent.setAdapter(mAdapter);
        mSrlSectionFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getSectionData();
            }
        });
        mPresenter.getSectionData();
        mLoadingAnmi.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    public void showError(String msg) {
        if (mSrlSectionFragment.isRefreshing()) {
            mSrlSectionFragment.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        ZToast.showShortToast(mContext, msg);
    }

    @Override
    public void showConent(SectionListBean sectionListBean) {
        if (mSrlSectionFragment.isRefreshing()) {
            mSrlSectionFragment.setRefreshing(false);
        } else {
            mLoadingAnmi.stop();
        }
        mList.clear();
        mList.addAll(sectionListBean.getData());
        mAdapter.notifyDataSetChanged();
    }

}
