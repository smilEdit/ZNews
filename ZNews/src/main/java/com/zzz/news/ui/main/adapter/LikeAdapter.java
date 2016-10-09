package com.zzz.news.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.news.R;
import com.zzz.news.model.bean.LikeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/10/9 11:39
 */

public class LikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context        mContext;
    private List<LikeBean> mList;
    private LayoutInflater mInflater;

    public static final int TYPE_TV = 0;
    public static final int TYPE_IV = 1;

    public LikeAdapter(Context context, List<LikeBean> likeBeen) {
        this.mContext = context;
        this.mList = likeBeen;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
//        if (mList.get(position).getType()== Constants.) {
//        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class IvViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_iv_image)
        ImageView mIvIvImage;

        public IvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TvViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tv_from)
        TextView  mTvTvFrom;
        @BindView(R.id.tv_tv_title)
        TextView  mTvTvTitle;
        @BindView(R.id.iv_tv_image)
        ImageView mIvTvImage;

        public TvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
