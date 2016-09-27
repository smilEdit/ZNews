package com.zzz.news.ui.zhihu.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.ThemeChildListBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/27 9:32
 */

public class ThemeChildAdapter extends CommonAdapter<ThemeChildListBean.StoriesBean> {

    private Context mContext;
    private List<ThemeChildListBean.StoriesBean> mList;

    public ThemeChildAdapter(Context context, int layoutId, List<ThemeChildListBean.StoriesBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    protected void convert(ViewHolder holder, ThemeChildListBean.StoriesBean storiesBean, int position) {
        holder.setText(R.id.tv_daily_item_title, mList.get(position).getTitle());
//        if (mList.get(position).getImages().get(0) != null && mList.get(position).getImages().size() > 0) {
//            ZImageLoader.setImg(mContext, mList.get(position).getImages().get(0), (ImageView) holder.getView(R.id.iv_daily_item_image));
//        }
    }

    public void setReadState(int position, boolean b) {
        mList.get(position).setReadState(b);
    }
}
