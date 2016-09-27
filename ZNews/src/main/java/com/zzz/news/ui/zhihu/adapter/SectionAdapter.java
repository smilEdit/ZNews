package com.zzz.news.ui.zhihu.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.SectionListBean;
import com.zzz.news.util.ZImageLoader;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 17:31
 */

public class SectionAdapter extends CommonAdapter<SectionListBean.DataBean> {

//    private List<SectionListBean.DataBean> mList;

    private Context mContext;

    public SectionAdapter(Context context, int layoutId, List<SectionListBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
//        this.mList = datas;
    }

    @Override
    protected void convert(ViewHolder holder, SectionListBean.DataBean dataBean, int position) {

//        ViewGroup.LayoutParams lp = holder.ivBg.getLayoutParams();
//        lp.width = (App.SCREEN_WIDTH - ZSystem.dp2px(mContext,12)) / 2;
//        lp.height = ZSystem.dp2px(mContext,120);
//        holder.setText(R.id.tv_section_content, mList.get(position).getDescription())
//                .setText(R.id.tv_section_kind, mList.get(position).getName());
                holder.setText(R.id.tv_section_content, dataBean.getDescription())
                        .setText(R.id.tv_section_kind, dataBean.getName());

        ZImageLoader.setImg(mContext,dataBean.getThumbnail(),(ImageView)holder.getView(R.id.iv_section_content));
    }
}
