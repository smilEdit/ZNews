package com.zzz.news.ui.juhe.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.LishiBean;
import com.zzz.news.presenter.LishiPresenter;
import com.zzz.news.presenter.contract.LishiContract;
import com.zzz.news.ui.juhe.adapter.LishiAdapter;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/30 9:43
 */

public class LishiFragment extends BaseFragment<LishiPresenter> implements LishiContract.View {
    @BindView(R.id.et_lishi_month)
    EditText     mEtLishiMonth;
    @BindView(R.id.et_lishi_day)
    EditText     mEtLishiDay;
    @BindView(R.id.btn_lishi_query)
    Button       mBtnLishiQuery;
    @BindView(R.id.rv_lishi)
    RecyclerView mRvLishi;

    private List<LishiBean.ResultBean> mList;
    private LishiAdapter               mAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();
        mAdapter = new LishiAdapter(mContext, R.layout.item_lishi, mList);
        mRvLishi.setLayoutManager(new LinearLayoutManager(mContext));
        mRvLishi.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lishi;
    }

    @Override
    public void showContent(List<LishiBean.ResultBean> lishiInfo) {
        mList.clear();
        mList.addAll(lishiInfo);
        mList.notify();
    }

    @Override
    public void showError(String msg) {
        ZToast.showShortToast(mContext, msg);
    }

    @OnClick(R.id.btn_lishi_query)
    public void onClick() {
        String month = mEtLishiMonth.getText().toString();
        String day = mEtLishiDay.getText().toString();
        if (!TextUtils.isEmpty(month) && !TextUtils.isEmpty(day)) {
            mPresenter.getLishiData(Integer.valueOf(month),Integer.valueOf(day));
        }
    }
}
