package com.zzz.news.ui.juhe.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.LishiBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/30 9:55
 */

public class LishiAdapter extends CommonAdapter<LishiBean.ResultBean> {
    public LishiAdapter(Context context, int layoutId, List<LishiBean.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LishiBean.ResultBean resultBean, int position) {
        holder.setText(R.id.tv_lishi_title, resultBean.getTitle())
                .setText(R.id.tv_lishi_des, resultBean.getDes())
                .setText(R.id.tv_lishi_lunar, resultBean.getLunar())
                .setText(R.id.tv_lishi_year, resultBean.getYear() + "");
    }
}
