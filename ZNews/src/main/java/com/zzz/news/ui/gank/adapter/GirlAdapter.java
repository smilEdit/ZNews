package com.zzz.news.ui.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zzz.news.R;
import com.zzz.news.app.App;
import com.zzz.news.model.bean.GankItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 13:53
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    private Context        mContext;

    List<GankItemBean> mList;

    public GirlAdapter(Context context, List<GankItemBean> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mList.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.mIvItemGirl.getLayoutParams();
            layoutParams.height = mList.get(holder.getAdapterPosition()).getHeight();
        }
        Glide.with(mContext).load(mList.get(position).getUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>(App.SCREEN_WIDTH / 2, App.SCREEN_WIDTH / 2) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                            if (mList.get(holder.getAdapterPosition()).getHeight() <= 0) {
                                int width = resource.getWidth();
                                int height = resource.getHeight();
                                int realHeight = (App.SCREEN_WIDTH / 2) * height / width;
                                mList.get(holder.getAdapterPosition()).setHeight(realHeight);
                                ViewGroup.LayoutParams lp = holder.mIvItemGirl.getLayoutParams();
                                lp.height = realHeight;
                            }
                            holder.mIvItemGirl.setImageBitmap(resource);
                        }
                    }
                });
        holder.mIvItemGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    ImageView iv = (ImageView) v.findViewById(R.id.iv_item_girl);
                    mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(),iv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_girl)
        ImageView mIvItemGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) App.SCREEN_WIDTH / (float) mList.get(position).getHeight() * 10f);
    }

    //item 回调
    public interface OnItemClickListener {
        void onItemClickListener(int position,View view);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
