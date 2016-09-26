package com.zzz.news.ui.gank.fragment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.GankItemBean;
import com.zzz.news.presenter.TechPresenter;
import com.zzz.news.presenter.contract.TechContract;
import com.zzz.news.ui.gank.adapter.TechAdapter;
import com.zzz.news.util.ZDate;
import com.zzz.news.util.ZImageLoader;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 9:58
 */

public class TechFragment extends BaseFragment<TechPresenter> implements TechContract.View {
    @BindView(R.id.iv_tech_bar_image)
    ImageView               mIvTechBarImage;
    @BindView(R.id.tv_tech_bar_time)
    TextView                mTvTechBarTime;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout mClpToolbar;
    @BindView(R.id.rv_tech_content)
    RecyclerView            mRvTechContent;
    @BindView(R.id.loading_anmi)
    RotateLoading           mLoadingAnmi;

    TechAdapter mAdapter;
    List<GankItemBean> mList = new ArrayList<>();
    private boolean isLoadingMore;
    private String mTech;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getGirlImage();
        mTvTechBarTime.setText(ZDate.getCurrentDateString());
        mList = new ArrayList<>();
        mTech = getArguments().getString("tech");
        mAdapter = new TechAdapter(mContext, mList, mTech);
        mRvTechContent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvTechContent.setAdapter(mAdapter);
        mLoadingAnmi.start();
        mPresenter.getGankData(mTech);
        mAdapter.setOnItemClickListener(new TechAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });
        mRvTechContent.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mRvTechContent.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = mRvTechContent.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMore){
                        isLoadingMore = true;
                        mPresenter.getMoreData(mTech);
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tech;
    }

    @Override
    public void showContent(List<GankItemBean> list) {
        mLoadingAnmi.stop();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreContent(List<GankItemBean> list) {
        mLoadingAnmi.stop();
        mRvTechContent.setVisibility(View.VISIBLE);
        mList.addAll(list);
        isLoadingMore = false;
    }

    @Override
    public void showGirlImage(String url) {
        ZImageLoader.setImg(mContext,url,mIvTechBarImage);
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        ZToast.showShortToast(mContext,"数据加载失败");
    }
}
