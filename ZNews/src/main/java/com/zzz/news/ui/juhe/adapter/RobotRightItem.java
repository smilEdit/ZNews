package com.zzz.news.ui.juhe.adapter;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.news.R;
import com.zzz.news.model.bean.RobotTalkBean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/29 14:18
 */

public class RobotRightItem implements ItemViewDelegate<RobotTalkBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_robot_right;
    }

    @Override
    public boolean isForViewType(RobotTalkBean item, int position) {
        return !item.isComMsg();
    }

    @Override
    public void convert(ViewHolder holder, RobotTalkBean bean, int position) {
        holder.setText(R.id.tv_robot_right, bean.getContent())
                .setImageResource(R.id.iv_robot_right, bean.getIcon());
    }
}
