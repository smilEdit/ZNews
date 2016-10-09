package com.zzz.news.ui.main.fragment;

import android.support.v7.widget.RecyclerView;

import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.LikeBean;
import com.zzz.news.presenter.LikePresenter;
import com.zzz.news.presenter.contract.LikeContract;
import com.zzz.news.util.ZToast;
import com.zzz.news.widegt.RvItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 11:30
 */

public class LikeFragment extends BaseFragment<LikePresenter> implements LikeContract.View {
    @BindView(R.id.rv_like_content)
    RecyclerView mRvLikeContent;

    List<LikeBean> mList;
    RvItemTouchHelper mRvItemTouchHelper;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mList = new ArrayList<>();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    public void showContent(List<LikeBean> list) {

    }

    @Override
    public void showError(String msg) {
        ZToast.showShortToast(mContext,msg);
    }
}
