package com.zzz.news.model.http;

import com.zzz.news.model.bean.CommentBean;
import com.zzz.news.model.bean.DailyBeforeListBean;
import com.zzz.news.model.bean.DailyListBean;
import com.zzz.news.model.bean.DetailExtraBean;
import com.zzz.news.model.bean.HotListBean;
import com.zzz.news.model.bean.SectionChildListBean;
import com.zzz.news.model.bean.SectionListBean;
import com.zzz.news.model.bean.ThemeChildListBean;
import com.zzz.news.model.bean.ThemeListBean;
import com.zzz.news.model.bean.ZhihuDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:27
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
    @GET("news/before/{date}")
    Observable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 专题日报
     */
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id")int id);

    /**
     * 欢迎界面
     */
//    @GET("start-image/{res}")
//    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 长评论
     */
    @GET("story/{id}/long-comments")
    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

    /**
     * 短评论
     */
    @GET("story/{id}/short-comments")
    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);

    /**
     * 热门日报
     */
    @GET("news/hot")
    Observable<HotListBean> getHotList();

    /**
     * 专栏
     */
    @GET("sections")
    Observable<SectionListBean> getSectionList();

    /**
     * 专栏详情
     */
    @GET("section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id")int id);

    /**
     * 主题详情
     */
    @GET("theme/{id}")
    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);
}
