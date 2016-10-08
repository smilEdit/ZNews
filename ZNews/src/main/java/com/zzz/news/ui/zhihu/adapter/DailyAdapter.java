package com.zzz.news.ui.zhihu.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzz.news.R;
import com.zzz.news.model.bean.DailyBeforeListBean;
import com.zzz.news.model.bean.DailyListBean;
import com.zzz.news.util.ZImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 16:11
 */
public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DailyListBean.StoriesBean>    mList;
    private List<DailyListBean.TopStoriesBean> mTopList;

    private LayoutInflater mInflater;
    private boolean isBefore = false;
    private String  mTitle   = "今日新闻";
    private Context         mContext;
    private TopPagerAdapter mAdapter;
    private ViewPager       mTopViewPager;


    public enum ITEM_TYPE {
        ITEM_TOP,       //滚动栏
        ITEM_DATE,      //日期
        ITEM_CONTENT    //内容
    }

    public DailyAdapter(Context context, List<DailyListBean.StoriesBean> list) {
        this.mList = list;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TOP.ordinal()) {
            if (mTopList != null) {
                mAdapter = new TopPagerAdapter(mContext, mTopList);
            }
            return new TopViewHolder(mInflater.inflate(R.layout.item_top, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_DATE.ordinal()) {
            return new DateViewHolder(mInflater.inflate(R.layout.item_date, parent, false));
        }
        return new ContentViewHolder(mInflater.inflate(R.layout.item_daily, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ContentViewHolder) {
            final int contentPosition;
            if (isBefore) {
                contentPosition = position - 1;
            } else {
                contentPosition = position - 2;
            }
            ((ContentViewHolder) holder).title.setText(mList.get(contentPosition).getTitle());
            if (mList.get(contentPosition).getReadState()) {
                ((ContentViewHolder) holder).title.setTextColor(ContextCompat.getColor(mContext, R.color.md_grey_500_color_code));
            } else {
                ((ContentViewHolder) holder).title.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_color_code));
            }
            ZImageLoader.setImg(mContext, mList.get(contentPosition).getImages().get(0), ((ContentViewHolder) holder).image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        ImageView iv = (ImageView) view.findViewById(R.id.iv_daily_item_image);
                        onItemClickListener.onItemClick(contentPosition, iv);
                    }
                }
            });
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).tvDate.setText(mTitle);
        } else {
            ((TopViewHolder) holder).vpTop.setAdapter(mAdapter);
            mTopViewPager = ((TopViewHolder) holder).vpTop;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!isBefore) {
            if (position == 0) {
                return ITEM_TYPE.ITEM_TOP.ordinal();
            } else if (position == 1) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        } else {
            if (position == 0) {
                return ITEM_TYPE.ITEM_DATE.ordinal();
            } else {
                return ITEM_TYPE.ITEM_CONTENT.ordinal();
            }
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_daily_item_title)
        TextView  title;
        @BindView(R.id.iv_daily_item_image)
        ImageView image;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_daily_date)
        TextView tvDate;

        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vp_top)
        ViewPager    vpTop;
        @BindView(R.id.ll_point_container)
        LinearLayout llContainer;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addDailyDate(DailyListBean info) {
        mTitle = "今日新闻";
        mList = info.getStories();
        mTopList = info.getTop_stories();
        isBefore = false;
        notifyDataSetChanged();
    }

    public void addDailyBeforeDate(DailyBeforeListBean info) {
        mTitle = info.getDate();
        mList = info.getStories();
        isBefore = false;
        notifyDataSetChanged();
    }

    public boolean getIsBefore() {
        return isBefore;
    }

    public void setReadState(int position, boolean readState) {
        mList.get(position).setReadState(readState);
    }

    public void changeTopPager(int currentCount) {
        if (!isBefore && mTopViewPager != null) {
            mTopViewPager.setCurrentItem(currentCount);
        }
    }

    //item点击回调
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
