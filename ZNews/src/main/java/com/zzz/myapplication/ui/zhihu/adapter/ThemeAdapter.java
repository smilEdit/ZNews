package com.zzz.myapplication.ui.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.myapplication.R;
import com.zzz.myapplication.model.bean.ThemeListBean;
import com.zzz.myapplication.util.ZImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/22 11:12
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private LayoutInflater                 mInflater;
    private List<ThemeListBean.OthersBean> mList;
    private Context                        mContext;

    public ThemeAdapter(Context context, List<ThemeListBean.OthersBean> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override

    public ThemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_theme,parent,false));
    }

    @Override
    public void onBindViewHolder(ThemeAdapter.ViewHolder holder, int position) {
        ZImageLoader.setImg(mContext,mList.get(position).getThumbnail(),holder.mIvThemeBg);
        holder.mTvThemeKind.setText(mList.get(position).getName());
        final int id = mList.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_theme_kind)
        TextView  mTvThemeKind;
        @BindView(R.id.iv_theme_bg)
        ImageView mIvThemeBg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //item点击回调
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }
}
