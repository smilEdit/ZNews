package com.zzz.news.model.http;

import com.zzz.news.model.bean.GankItemBean;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

import retrofit2.http.GET;

/**
 * @创建者 zlf
 * @创建时间 2016/9/25 20:28
 */

public interface GankApis {
    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<HttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech,@Path("num")int num,@Path("page")int page);
    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<HttpResponse<List<GankItemBean>>> getFuliList(@Path("num") int num);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<HttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);
}






























