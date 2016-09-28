package com.zzz.news.ui.zhihu.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.HotListBean;
import com.zzz.news.util.ZImageLoader;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 15:53
 */

public class HotAdapter extends CommonAdapter<HotListBean.RecentBean> {

    private Context mContext;
    private List<HotListBean.RecentBean> mList;

    public HotAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    protected void convert(ViewHolder holder, HotListBean.RecentBean recentBean, int position) {
        holder.setText(R.id.tv_daily_item_title, mList.get(position).getTitle());
        if (mList.get(position).getReadState()) {
            holder.setTextColor(R.id.tv_daily_item_title, R.color.md_blue_grey_500_color_code);
        } else {
            holder.setTextColor(R.id.tv_daily_item_title, R.color.black_bg);
        }
        ZImageLoader.setImg(mContext,mList.get(position).getThumbnail(),(ImageView)holder.getView(R.id.iv_daily_item_image));
    }
}
