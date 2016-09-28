package com.zzz.news.ui.juhe.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.JokeBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 16:29
 */

public class JokeAdapter extends CommonAdapter<JokeBean.ResultBean.DataBean> {
    public JokeAdapter(Context context, int layoutId, List<JokeBean.ResultBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, JokeBean.ResultBean.DataBean jokeBean, int position) {
        holder.setText(R.id.tv_joke, jokeBean.getContent());
    }
}
