package com.zzz.news.ui.juhe.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.TopNewsBean;
import com.zzz.news.util.ZImageLoader;

import java.util.List;



/**
 * @创建者 zlf
 * @创建时间 2016/9/28 14:47
 */

public class TopNewsAdapter extends CommonAdapter<TopNewsBean.ResultBean.DataBean> {

    private Context mContext;

    public TopNewsAdapter(Context context, int layoutId, List<TopNewsBean.ResultBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, TopNewsBean.ResultBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_topnews_author, dataBean.getAuthor_name())
                .setText(R.id.tv_topnews_title, dataBean.getTitle())
                .setText(R.id.tv_topnews_date, dataBean.getDate());
        ZImageLoader.setImg(mContext,dataBean.getThumbnail_pic_s(),(ImageView) holder.getView(R.id.iv_topnews));
    }
}
