package com.zzz.news.model.http;

import com.zzz.news.model.bean.JokeBean;
import com.zzz.news.model.bean.TopNewsBean;
import com.zzz.news.model.bean.WeixinBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 17:22
 */

public interface JuHeApis {


    //头条
    //http://v.juhe.cn/toutiao/index?type=top&key=bd8e0a7114136f806abe023bfd16d4d6

    //笑话
    //http://japi.juhe.cn/joke/content/list.from?key=189df93e385f308c4ca8c71c4bc7fbae&page=2&pagesize=10&sort=asc&time=1418745237

    //微信精选
    //http://v.juhe.cn/weixin/query?key=您申请的KEY

    String HOST = "http://v.juhe.cn/";

    String HOST_JOKE = "http://japi.juhe.cn/";

    @GET("toutiao/index?type=top&key=bd8e0a7114136f806abe023bfd16d4d6")
    Observable<JuheHttpResponse<TopNewsBean.ResultBean>> getTopNewsList();


    @GET("joke/content/list.from")
    Observable<JuheHttpResponse<JokeBean.ResultBean>> getJokeList(@Query("key")String key,@Query("page")int page,@Query("pagesize")int pagesize,@Query("sort")String sort,@Query("time")String time);

    @GET("weixin/query")
    Observable<JuheHttpResponse<WeixinBean.ResultBean>> getWeixinList(@Query("key") String key);
}
