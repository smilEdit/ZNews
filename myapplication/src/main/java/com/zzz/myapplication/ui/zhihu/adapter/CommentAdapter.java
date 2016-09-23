package com.zzz.myapplication.ui.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.myapplication.R;
import com.zzz.myapplication.model.bean.CommentBean;
import com.zzz.myapplication.util.ZDate;
import com.zzz.myapplication.util.ZImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/23 15:16
 */

public class CommentAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<CommentBean.CommentsBean> mList;

    private Context mContext;

    public CommentAdapter(Context context, List<CommentBean.CommentsBean> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(convertView);
            convertView = mInflater.inflate(R.layout.item_comment, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommentBean.CommentsBean info = mList.get(position);
        ZImageLoader.setAvatar(mContext,info.getAvatar(),holder.mIvCommentFace);
        holder.mTvCommentLike.setText(info.getLikes());
        holder.mTvCommentTime.setText(ZDate.formatTime2String(info.getTime()));
        holder.mTvCommentDetail.setText(info.getContent());
        holder.mTvCommentTitle.setText(info.getAuthor());
        return convertView;
    }



    public static class ViewHolder {
        @BindView(R.id.iv_comment_face)
        ImageView mIvCommentFace;
        @BindView(R.id.tv_comment_title)
        TextView mTvCommentTitle;
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;
        @BindView(R.id.tv_comment_reply)
        TextView mTvCommentReply;
        @BindView(R.id.tv_comment_time)
        TextView mTvCommentTime;
        @BindView(R.id.tv_comment_detail)
        TextView mTvCommentDetail;
        @BindView(R.id.tv_comment_like)
        TextView mTvCommentLike;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
