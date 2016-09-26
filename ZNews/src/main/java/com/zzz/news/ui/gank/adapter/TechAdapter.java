package com.zzz.news.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzz.news.R;
import com.zzz.news.model.bean.GankItemBean;
import com.zzz.news.util.ZDate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 10:06
 */

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.ViewHolder> {


    private LayoutInflater     mInflater;
    private List<GankItemBean> mList;
    private String             mTech;

    public TechAdapter(Context context, List<GankItemBean> list, String tech) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
        this.mTech = tech;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_tech, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        if(mTech.equals(TechPresenter.TECH_ANDROID)) {
//            holder.ivIcon.setImageResource(R.mipmap.ic_android);
//        } else if(mTech.equals(TechPresenter.TECH_IOS)) {
//            holder.ivIcon.setImageResource(R.mipmap.ic_ios);
//        } else if(mTech.equals(TechPresenter.TECH_WEB)) {
//            holder.ivIcon.setImageResource(R.mipmap.ic_web);
//        }
        holder.mTvTechTitle.setText(mList.get(position).getDesc());
        holder.mTvTechAuthor.setText(mList.get(position).getWho());
        String date = mList.get(position).getPublishedAt();
        int idx = date.indexOf(".");
        date = date.substring(0,idx).replace("T"," ");
        holder.mTvTechDate.setText(ZDate.formatDateTime(date,true));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null) {
                    CardView cv = (CardView) view.findViewById(R.id.card);
                    mOnItemClickListener.onItemClick(holder.getAdapterPosition(),cv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tech_title)
        TextView mTvTechTitle;
        @BindView(R.id.tv_tech_author)
        TextView         mTvTechAuthor;
        @BindView(R.id.tv_tech_date)
        TextView         mTvTechDate;
        @BindView(R.id.card)
        CardView mCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //item回调
    public interface OnItemClickListener{
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    private OnItemClickListener mOnItemClickListener;
}
