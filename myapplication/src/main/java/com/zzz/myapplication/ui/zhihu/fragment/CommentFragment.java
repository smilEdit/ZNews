package com.zzz.myapplication.ui.zhihu.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.victor.loading.rotate.RotateLoading;
import com.zzz.myapplication.R;
import com.zzz.myapplication.base.BaseFragment;
import com.zzz.myapplication.model.bean.CommentBean;
import com.zzz.myapplication.presenter.CommentPresenter;
import com.zzz.myapplication.presenter.contract.CommentContract;
import com.zzz.myapplication.ui.zhihu.adapter.CommentAdapter;
import com.zzz.myapplication.util.ZLog;

import java.util.List;

import butterknife.BindView;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 14:58
 */

public class CommentFragment extends BaseFragment<CommentPresenter> implements CommentContract.View {
    @BindView(R.id.lv_comment_content)
    ListView      mLvCommentContent;
    @BindView(R.id.loading_anmi)
    RotateLoading mLoadingAnmi;

    List<CommentBean.CommentsBean> mList;
    private CommentAdapter mAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        Bundle bundle = getArguments();
        mPresenter.getCommentData(bundle.getInt("id"),bundle.getInt("kind"));
        mLoadingAnmi.start();
        mLvCommentContent.setVisibility(View.VISIBLE);
        mAdapter = new CommentAdapter(mContext, mList);
        mLvCommentContent.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void showContent(CommentBean commentBean) {
        ZLog.i(commentBean.toString());
        mLoadingAnmi.stop();
        mLvCommentContent.setVisibility(View.VISIBLE);
        mList = commentBean.getComments();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mLoadingAnmi.stop();
        mLvCommentContent.setVisibility(View.INVISIBLE);
    }
}
