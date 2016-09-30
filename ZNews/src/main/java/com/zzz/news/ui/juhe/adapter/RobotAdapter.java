package com.zzz.news.ui.juhe.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 13:54
 */

public class RobotAdapter extends CommonAdapter<String> {


    public RobotAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
//        holder.setText();
    }
}
