package com.zzz.news.ui.juhe.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.WeixinBean;
import com.zzz.news.util.ZImageLoader;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 10:39
 */

public class WeixinAdapter extends CommonAdapter<WeixinBean.ResultBean.ListBean> {
    private Context mContext;

    public WeixinAdapter(Context context, int layoutId, List<WeixinBean.ResultBean.ListBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, WeixinBean.ResultBean.ListBean dataBean, int position) {
        holder.setText(R.id.tv_topnews_author, dataBean.getSource())
                .setText(R.id.tv_topnews_title, dataBean.getTitle());
        ZImageLoader.setImg(mContext,dataBean.getFirstImg(),(ImageView) holder.getView(R.id.iv_topnews));
    }
}
