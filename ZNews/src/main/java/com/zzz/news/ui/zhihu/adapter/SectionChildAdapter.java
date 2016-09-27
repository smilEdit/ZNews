package com.zzz.news.ui.zhihu.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.SectionChildListBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 10:58
 */

public class SectionChildAdapter extends CommonAdapter<SectionChildListBean.StoriesBean> {

    private Context mContext;
    private List<SectionChildListBean.StoriesBean> mList;

    public SectionChildAdapter(Context context, int layoutId, List<SectionChildListBean.StoriesBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    protected void convert(ViewHolder holder, SectionChildListBean.StoriesBean storiesBean, int position) {
        holder.setText(R.id.tv_daily_item_title,storiesBean.getTitle());
//        ZImageLoader.setImg(mContext,mList.get(position).getImages().get(0),(ImageView) holder.getView(R.id.iv_daily_item_image));
    }

    public void setReadState(int position, boolean b) {
        mList.get(position).setReadState(b);
    }
}
