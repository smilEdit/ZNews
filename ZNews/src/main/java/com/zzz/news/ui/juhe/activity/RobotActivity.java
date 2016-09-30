package com.zzz.news.ui.juhe.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zzz.news.R;
import com.zzz.news.base.BaseActivity;
import com.zzz.news.model.bean.RobotTalkBean;
import com.zzz.news.presenter.RobotPresenter;
import com.zzz.news.presenter.contract.RobotContract;
import com.zzz.news.ui.juhe.adapter.RobotLeftItem;
import com.zzz.news.ui.juhe.adapter.RobotRightItem;
import com.zzz.news.util.ZDate;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZSnack;
import com.zzz.news.util.ZToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 13:45
 */

public class RobotActivity extends BaseActivity<RobotPresenter> implements RobotContract.View {
    @BindView(R.id.tool_bar)
    Toolbar      mToolBar;
    @BindView(R.id.rv_robot)
    RecyclerView mRvRobot;
    @BindView(R.id.et_robot)
    EditText     mEtRobot;
    @BindView(R.id.bu_robot)
    Button       mBuRobot;

    private String mQuestion;


    private List<RobotTalkBean>                 mList;
    private MultiItemTypeAdapter<RobotTalkBean> mAdapter;

    @Override
    protected void initEventAndData() {
        ZLog.i("init");
        mList = new ArrayList<>();
        setToolBar(mToolBar, "图灵测试");
        mAdapter = new MultiItemTypeAdapter<>(this, mList);
        mAdapter.addItemViewDelegate(new RobotLeftItem());
        mAdapter.addItemViewDelegate(new RobotRightItem());
        mRvRobot.setLayoutManager(new LinearLayoutManager(mContext));
        mRvRobot.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ZToast.showShortToast(mContext,"消息时间"+mList.get(position).getCreateDate());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                ZToast.showShortToast(mContext,"居然还知道长点击哦");
                return true;
            }
        });
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_robot;
    }

    @Override
    public void showAnswer(String answer) {
        mList.add(new RobotTalkBean(ZDate.getCurrentDate(), true, answer, R.mipmap.anroid));
        mAdapter.notifyDataSetChanged();
        mRvRobot.smoothScrollToPosition(mList.size());
    }

    @Override
    public void showError(String msg) {
        ZToast.showShortToast(mContext, msg);
    }


    @OnClick(R.id.bu_robot)
    public void onClick() {
        mQuestion = mEtRobot.getText().toString();
        if (TextUtils.isEmpty(mQuestion)) {
            ZSnack.showSnackShort(mRvRobot, "请输入内容");
        } else {
            mEtRobot.setText("");
            mList.add(new RobotTalkBean(ZDate.getCurrentDate(), false, mQuestion, R.mipmap.github));
            mAdapter.notifyDataSetChanged();
            mPresenter.getRobotData(mQuestion);
            mRvRobot.smoothScrollToPosition(mList.size());
        }
    }
}
