package com.zzz.myapplication.ui.zhihu.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.model.bean.DailyBeforeListBean;
import com.zzz.myapplication.model.bean.DailyListBean;
import com.zzz.myapplication.presenter.DailyPresenter;
import com.zzz.myapplication.presenter.contract.DailyContract;
import com.zzz.myapplication.ui.zhihu.activity.CalendarActivity;
import com.zzz.myapplication.ui.zhihu.activity.ZhihuDetailActivity;
import com.zzz.myapplication.ui.zhihu.adapter.DailyAdapter;
import com.zzz.myapplication.util.ZCircularAnim;
import com.zzz.myapplication.util.ZDate;
import com.zzz.myapplication.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 14:56
 */
public class DailyFragment extends BaseFragment<DailyPresenter> implements DailyContract.View {
    @BindView(R.id.rl_daily_loading)
    RotateLoading        mRlDailyLoading;
    @BindView(R.id.rv_daily_list)
    RecyclerView         mRvDailyList;
    @BindView(R.id.fab_daily_calender)
    FloatingActionButton mFabDailyCalender;
    @BindView(R.id.srl_daily_refresh)
    SwipeRefreshLayout   mSrlDailyRefresh;

    DailyAdapter mAdapter;
    String       mCurrentDate;

    List<DailyListBean.StoriesBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initEventAndData() {
        mCurrentDate = ZDate.getCurrentDate();
        mAdapter = new DailyAdapter(mContext, mList);
        mAdapter.setOnItemClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(mContext, ZhihuDetailActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
        mSrlDailyRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRvDailyList.setVisibility(View.INVISIBLE);
                mRlDailyLoading.start();
                if (mCurrentDate.equals("今日新闻")) {
                    mPresenter.getDailyData();
                } else {
                    mPresenter.getBeforeData(mCurrentDate);
                }
            }
        });
        mRvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        mRvDailyList.setAdapter(mAdapter);
        mRvDailyList.setVisibility(View.INVISIBLE);
        mRlDailyLoading.start();
        mPresenter.getDailyData();
    }

    @Override
    public void showContent(DailyListBean info) {
        mRlDailyLoading.stop();
        mRvDailyList.setVisibility(View.VISIBLE);
        mAdapter.addDailyDate(info);
        mPresenter.startInterval();
    }

    @Override
    public void showMoreContent(String date, DailyBeforeListBean info) {
        mSrlDailyRefresh.setRefreshing(false);
        mCurrentDate = String.valueOf(Integer.valueOf(info.getDate() + 1));
        mRlDailyLoading.stop();
        mRvDailyList.setVisibility(View.VISIBLE);
        mAdapter.addDailyBeforeDate(info);
    }

    @Override
    public void showError(String msg) {
        mRlDailyLoading.stop();
        ZToast.showShortToast(mContext,"数据加载失败");
    }

    @Override
    public void showProgress() {
        mRlDailyLoading.start();
        mRvDailyList.setVisibility(View.INVISIBLE);

    }

    @Override
    public void doInterval(int currentCount) {
        mAdapter.changeTopPager(currentCount);
    }

    @OnClick(R.id.fab_daily_calender)
    void startCalendar() {
        Intent intent = new Intent();
        intent.setClass(mContext, CalendarActivity.class);
        ZCircularAnim.startActivity(mActivity,intent,mFabDailyCalender,R.color.colorPrimary);
    }
}
