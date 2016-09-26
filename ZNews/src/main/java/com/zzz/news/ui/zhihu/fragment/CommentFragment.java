package com.zzz.news.ui.zhihu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.news.R;
import com.zzz.news.base.BaseFragment;
import com.zzz.news.model.bean.CommentBean;
import com.zzz.news.presenter.CommentPresenter;
import com.zzz.news.presenter.contract.CommentContract;
import com.zzz.news.ui.zhihu.adapter.CommentAdapter;
import com.zzz.news.util.ZSnack;
import com.zzz.news.widegt.FunGameRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 14:58
 */

public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {
    @BindView(R.id.lv_comment_content)
    ListView           mLvCommentContent;
    @BindView(R.id.loading_anmi)
    RotateLoading      mLoadingAnmi;
    @BindView(R.id.refresh_fun_game)
    FunGameRefreshView mFunGameRefreshView;

    List<CommentBean.CommentsBean> mList;
    private CommentAdapter mAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mFunGameRefreshView.setLoadingText("loading...");
        mFunGameRefreshView.setGameOverText("再来一次");
        mFunGameRefreshView.setLoadingFinishedText("通关有福利");
        mFunGameRefreshView.setTopMaskText("发现一个小游戏");
        mFunGameRefreshView.setBottomMaskText("上下滑动控制游戏");
        Bundle bundle = getArguments();
        mPresenter.getCommentData(bundle.getInt("id"), bundle.getInt("kind"));
        mLoadingAnmi.start();
        mLvCommentContent.setVisibility(View.VISIBLE);
        mList = new ArrayList<>();
        mAdapter = new CommentAdapter(mContext, mList);
        mLvCommentContent.setAdapter(mAdapter);
        mFunGameRefreshView.setOnRefreshListener(new FunGameRefreshView.FunGameRefreshListener() {
            @Override
            public void onRefreshing() {
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ZSnack.showSnackShort(mFunGameRefreshView, "解除封印");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void showContent(CommentBean commentBean) {
        mLoadingAnmi.stop();
        mLvCommentContent.setVisibility(View.VISIBLE);
        mList.clear();
        mList.addAll(commentBean.getComments());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        mLvCommentContent.setVisibility(View.INVISIBLE);
    }
}
